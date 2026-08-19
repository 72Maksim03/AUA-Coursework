package main

import "fmt"

type Node interface {
	nodeType() string
	String() string
}

type NumberLit struct {
	Value int64
}

func (n *NumberLit) nodeType() string { return "NumberLit" }
func (n *NumberLit) String() string   { return fmt.Sprintf("%d", n.Value) }

type Ident struct {
	Name string
}

func (n *Ident) nodeType() string { return "Ident" }
func (n *Ident) String() string   { return n.Name }

type BinaryExpr struct {
	Op    string
	Left  Node
	Right Node
}

func (n *BinaryExpr) nodeType() string { return "BinaryExpr" }
func (n *BinaryExpr) String() string {
	return fmt.Sprintf("(%s %s %s)", n.Left, n.Op, n.Right)
}

type UnaryExpr struct {
	Op      string // "-"
	Operand Node
}

func (n *UnaryExpr) nodeType() string { return "UnaryExpr" }
func (n *UnaryExpr) String() string   { return fmt.Sprintf("(%s%s)", n.Op, n.Operand) }

type DeclStmt struct {
	Names []string
}

func (n *DeclStmt) nodeType() string { return "DeclStmt" }
func (n *DeclStmt) String() string   { return fmt.Sprintf("int %v;", n.Names) }

type AssignStmt struct {
	Name  string
	Expr  Node
	IsNew bool
}

func (n *AssignStmt) nodeType() string { return "AssignStmt" }
func (n *AssignStmt) String() string {
	op := "="
	if n.IsNew {
		op = ":="
	}
	return fmt.Sprintf("%s %s %s;", n.Name, op, n.Expr)
}

type Program struct {
	Stmts []Node
}

func (n *Program) nodeType() string { return "Program" }
func (n *Program) String() string {
	s := ""
	for _, st := range n.Stmts {
		s += st.String() + "\n"
	}
	return s
}