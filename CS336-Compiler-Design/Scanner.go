package main

import (
	"fmt"
	"strings"
	"unicode"
)

type TokenType int

const (
	TOK_INT    TokenType = iota
	TOK_IDENT
	TOK_EOF

	TOK_PLUS   // +
	TOK_MINUS  // -
	TOK_STAR   // *
	TOK_ASSIGN // =
	TOK_WALRUS // :=

	TOK_LPAREN    // (
	TOK_RPAREN    // )
	TOK_SEMICOLON // ;
	TOK_COMMA     // ,

	TOK_INT_KW // int
)

var tokenNames = map[TokenType]string{
	TOK_INT:       "INT",
	TOK_IDENT:     "IDENT",
	TOK_EOF:       "EOF",
	TOK_PLUS:      "+",
	TOK_MINUS:     "-",
	TOK_STAR:      "*",
	TOK_ASSIGN:    "=",
	TOK_WALRUS:    ":=",
	TOK_LPAREN:    "(",
	TOK_RPAREN:    ")",
	TOK_SEMICOLON: ";",
	TOK_COMMA:     ",",
	TOK_INT_KW:    "int",
}

func (t TokenType) String() string {
	if s, ok := tokenNames[t]; ok {
		return s
	}
	return fmt.Sprintf("TOKEN(%d)", int(t))
}

type Token struct {
	Type    TokenType
	Value   string
	Line    int
	Col     int
}

func (t Token) String() string {
	return fmt.Sprintf("Token(%s, %q, %d:%d)", t.Type, t.Value, t.Line, t.Col)
}

type Lexer struct {
	src  []rune
	pos  int
	line int
	col  int
}

func NewLexer(src string) *Lexer {
	return &Lexer{src: []rune(src), pos: 0, line: 1, col: 1}
}

func (l *Lexer) peek() rune {
	if l.pos >= len(l.src) {
		return 0
	}
	return l.src[l.pos]
}

func (l *Lexer) advance() rune {
	ch := l.src[l.pos]
	l.pos++
	if ch == '\n' {
		l.line++
		l.col = 1
	} else {
		l.col++
	}
	return ch
}

func (l *Lexer) skipWhitespaceAndComments() {
	for l.pos < len(l.src) {
		ch := l.peek()
		if unicode.IsSpace(ch) {
			l.advance()
			continue
		}
		if ch == '/' && l.pos+1 < len(l.src) && l.src[l.pos+1] == '/' {
			for l.pos < len(l.src) && l.peek() != '\n' {
				l.advance()
			}
			continue
		}
		break
	}
}

func (l *Lexer) readNumber() Token {
	line, col := l.line, l.col
	var sb strings.Builder
	for l.pos < len(l.src) && unicode.IsDigit(l.peek()) {
		sb.WriteRune(l.advance())
	}
	return Token{Type: TOK_INT, Value: sb.String(), Line: line, Col: col}
}

func (l *Lexer) readIdent() Token {
	line, col := l.line, l.col
	var sb strings.Builder
	for l.pos < len(l.src) && (unicode.IsLetter(l.peek()) || l.peek() == '_' || unicode.IsDigit(l.peek())) {
		sb.WriteRune(l.advance())
	}
	val := sb.String()
	tt := TOK_IDENT
	if val == "int" {
		tt = TOK_INT_KW
	}
	return Token{Type: tt, Value: val, Line: line, Col: col}
}

func (l *Lexer) Tokenize() ([]Token, error) {
	var tokens []Token
	for {
		l.skipWhitespaceAndComments()
		if l.pos >= len(l.src) {
			tokens = append(tokens, Token{Type: TOK_EOF, Line: l.line, Col: l.col})
			break
		}

		line, col := l.line, l.col
		ch := l.peek()

		switch {
		case unicode.IsDigit(ch):
			tokens = append(tokens, l.readNumber())

		case unicode.IsLetter(ch) || ch == '_':
			tokens = append(tokens, l.readIdent())

		case ch == '+':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_PLUS, Value: "+", Line: line, Col: col})

		case ch == '-':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_MINUS, Value: "-", Line: line, Col: col})

		case ch == '*':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_STAR, Value: "*", Line: line, Col: col})

		case ch == '(':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_LPAREN, Value: "(", Line: line, Col: col})

		case ch == ')':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_RPAREN, Value: ")", Line: line, Col: col})

		case ch == ';':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_SEMICOLON, Value: ";", Line: line, Col: col})

		case ch == ',':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_COMMA, Value: ",", Line: line, Col: col})

		case ch == ':':
			l.advance()
			if l.pos < len(l.src) && l.peek() == '=' {
				l.advance()
				tokens = append(tokens, Token{Type: TOK_WALRUS, Value: ":=", Line: line, Col: col})
			} else {
				return nil, fmt.Errorf("%d:%d: unexpected character ':'", line, col)
			}

		case ch == '=':
			l.advance()
			tokens = append(tokens, Token{Type: TOK_ASSIGN, Value: "=", Line: line, Col: col})

		default:
			return nil, fmt.Errorf("%d:%d: unexpected character %q", line, col, ch)
		}
	}
	return tokens, nil
}