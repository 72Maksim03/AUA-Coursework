package main

import (
	"fmt"
	"strings"
)

type CodeGen struct {
	sb       strings.Builder
	vars     map[string]bool
	lastVar  string
	labelCnt int
}

func NewCodeGen() *CodeGen {
	return &CodeGen{vars: make(map[string]bool)}
}

func (g *CodeGen) emit(format string, args ...interface{}) {
	fmt.Fprintf(&g.sb, format+"\n", args...)
}

func (g *CodeGen) Generate(prog *Program) (string, error) {
	for _, stmt := range prog.Stmts {
		switch s := stmt.(type) {
		case *DeclStmt:
			for _, name := range s.Names {
				if g.vars[name] {
					return "", fmt.Errorf("variable %q declared twice", name)
				}
				g.vars[name] = true
			}
		case *AssignStmt:
			if s.IsNew {
				g.vars[s.Name] = true
			}
		}
	}

	g.emit(".bss")
	for name := range g.vars {
		g.emit("%s:", name)
		g.emit("    .quad 0")
	}

	g.emit("")
	g.emit(".text")
	g.emit("    .globl _start")
	g.emit("")
	g.emit("_start:")

	for _, stmt := range prog.Stmts {
		switch s := stmt.(type) {
		case *DeclStmt:

		case *AssignStmt:
			if !s.IsNew && !g.vars[s.Name] {
				return "", fmt.Errorf("assignment to undeclared variable %q", s.Name)
			}
			if err := g.genExpr(s.Expr); err != nil {
				return "", err
			}
			g.emit("    popq  %%rax")
			g.emit("    movq  %%rax, %s(%%rip)", s.Name)
			g.lastVar = s.Name

		default:
			return "", fmt.Errorf("unknown statement type %T", stmt)
		}
	}

	g.emit("    # exit(lastVar)")
	if g.lastVar != "" {
		g.emit("    movq  %s(%%rip), %%rdi", g.lastVar)
	} else {
		g.emit("    xorq  %%rdi, %%rdi")
	}
	g.emit("    movq  $60, %%rax   # sys_exit")
	g.emit("    syscall")

	return g.sb.String(), nil
}

func (g *CodeGen) genExpr(node Node) error {
	switch n := node.(type) {

	case *NumberLit:
		g.emit("    movq  $%d, %%rax", n.Value)
		g.emit("    pushq %%rax")

	case *Ident:
		if !g.vars[n.Name] {
			return fmt.Errorf("use of undeclared variable %q", n.Name)
		}
		g.emit("    movq  %s(%%rip), %%rax", n.Name)
		g.emit("    pushq %%rax")

	case *UnaryExpr:
		if err := g.genExpr(n.Operand); err != nil {
			return err
		}
		g.emit("    popq  %%rax")
		g.emit("    negq  %%rax")
		g.emit("    pushq %%rax")

	case *BinaryExpr:
		if err := g.genExpr(n.Left); err != nil {
			return err
		}
		if err := g.genExpr(n.Right); err != nil {
			return err
		}
		g.emit("    popq  %%rbx          # right operand")
		g.emit("    popq  %%rax          # left operand")
		switch n.Op {
		case "+":
			g.emit("    addq  %%rbx, %%rax")
		case "-":
			g.emit("    subq  %%rbx, %%rax")
		case "*":
			g.emit("    imulq %%rbx, %%rax")
		default:
			return fmt.Errorf("unknown binary operator %q", n.Op)
		}
		g.emit("    pushq %%rax")

	default:
		return fmt.Errorf("unknown expression node %T", node)
	}
	return nil
}