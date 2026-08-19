# calc — A tiny expression compiler in Go

Compiles a simple expression language to a native x86-64 Linux binary.
The result of the last assignment is returned as the binary's exit code.

## Requirements

- `go` (≥ 1.18) — `sudo apt install golang`
- `as` and `ld` (binutils) — `sudo apt install binutils`

## Build

```bash
go build -o calc .
```

## Run

```bash
./calc -run -o myprogram program.calc
```

Or compile and check manually:

```bash
./calc -o myprogram program.calc
./myprogram
echo $?
```

## Options

```
-o <file>   Output binary (default: a.out)
-S          Emit assembly (.s) only, don't assemble or link
-v          Verbose: print tokens, AST, and assembly
-run        Compile, run, and print the exit code
```

## Example program

```
int i, j, k;
i = 0;
j = i + 5;
k = j - 2;
k := i + 2 - (i * 2) + k;
```

```bash
./calc -run -o demo example.calc
# Exit code (result): 5
```