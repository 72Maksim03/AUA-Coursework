package main

import (
	"flag"
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
	"strings"
)

const usage = `calc - a tiny expression compiler

Usage:
  calc [options] <source.calc>

Options:
  -o <file>     Output binary (default: a.out)
  -S            Emit assembly only, don't assemble/link
  -v            Verbose: print tokens, AST, and assembly
  -run          Run the compiled binary and print the exit code
  -h            Show this help

Source file format:
  int i, j, k;          // variable declaration
  i = 0;                // simple assignment (=)
  j = i + 5;
  k := j - 2;           // declare-and-assign (:=)
  k := i + 2 - (i * 2) + k;

Operators: + - * and parentheses ( )
The exit code of the compiled binary equals the result of the last assignment.
`

func main() {
	outFile := flag.String("o", "a.out", "output binary")
	emitAsm := flag.Bool("S", false, "emit assembly only")
	verbose := flag.Bool("v", false, "verbose output")
	run := flag.Bool("run", false, "run the binary and print exit code")
	flag.Usage = func() { fmt.Fprint(os.Stderr, usage) }
	flag.Parse()

	if flag.NArg() != 1 {
		flag.Usage()
		os.Exit(1)
	}

	srcPath := flag.Arg(0)
	src, err := os.ReadFile(srcPath)
	if err != nil {
		fatalf("cannot read %s: %v", srcPath, err)
	}

	lexer := NewLexer(string(src))
	tokens, err := lexer.Tokenize()
	if err != nil {
		fatalf("lex error: %v", err)
	}
	if *verbose {
		fmt.Println("=== TOKENS ===")
		for _, t := range tokens {
			fmt.Println(" ", t)
		}
	}

	parser := NewParser(tokens)
	prog, err := parser.ParseProgram()
	if err != nil {
		fatalf("parse error: %v", err)
	}
	if *verbose {
		fmt.Println("\n=== AST ===")
		fmt.Print(prog)
	}

	cg := NewCodeGen()
	asm, err := cg.Generate(prog)
	if err != nil {
		fatalf("codegen error: %v", err)
	}
	if *verbose || *emitAsm {
		fmt.Println("\n=== ASSEMBLY ===")
		fmt.Print(asm)
	}
	if *emitAsm {
		base := strings.TrimSuffix(srcPath, filepath.Ext(srcPath))
		asmPath := base + ".s"
		if err := os.WriteFile(asmPath, []byte(asm), 0644); err != nil {
			fatalf("cannot write %s: %v", asmPath, err)
		}
		fmt.Printf("Assembly written to %s\n", asmPath)
		return
	}

	tmpDir, err := os.MkdirTemp("", "calc-*")
	if err != nil {
		fatalf("cannot create temp dir: %v", err)
	}
	defer os.RemoveAll(tmpDir)

	asmPath := filepath.Join(tmpDir, "out.s")
	objPath := filepath.Join(tmpDir, "out.o")

	if err := os.WriteFile(asmPath, []byte(asm), 0644); err != nil {
		fatalf("cannot write asm: %v", err)
	}

	nasmCmd := exec.Command("as", "--64", asmPath, "-o", objPath)
	nasmCmd.Stdout = os.Stdout
	nasmCmd.Stderr = os.Stderr
	if err := nasmCmd.Run(); err != nil {
		fatalf("as failed: %v", err)
	}

	ldCmd := exec.Command("ld", objPath, "-o", *outFile)
	ldCmd.Stdout = os.Stdout
	ldCmd.Stderr = os.Stderr
	if err := ldCmd.Run(); err != nil {
		fatalf("ld failed: %v", err)
	}

	fmt.Printf("Binary written to %s\n", *outFile)

	if *run {
		absOut, _ := filepath.Abs(*outFile)
		cmd := exec.Command(absOut)
		cmd.Stdout = os.Stdout
		cmd.Stderr = os.Stderr
		_ = cmd.Run()
		code := cmd.ProcessState.ExitCode()
		fmt.Printf("Exit code (result): %d\n", code)
	}
}

func fatalf(format string, args ...interface{}) {
	fmt.Fprintf(os.Stderr, "calc: "+format+"\n", args...)
	os.Exit(1)
}