package main

import (
	"fmt"
	"strconv"
)

type Parser struct {
	tokens []Token
	pos    int
}

func NewParser(tokens []Token) *Parser {
	return &Parser{tokens: tokens}
}

func (p *Parser) peek() Token {
	if p.pos >= len(p.tokens) {
		return Token{Type: TOK_EOF}
	}
	return p.tokens[p.pos]
}

func (p *Parser) advance() Token {
	t := p.tokens[p.pos]
	p.pos++
	return t
}

func (p *Parser) expect(tt TokenType) (Token, error) {
	t := p.peek()
	if t.Type != tt {
		return Token{}, fmt.Errorf("%d:%d: expected %s, got %s (%q)",
			t.Line, t.Col, tt, t.Type, t.Value)
	}
	return p.advance(), nil
}

func (p *Parser) ParseProgram() (*Program, error) {
	prog := &Program{}
	for p.peek().Type != TOK_EOF {
		stmt, err := p.parseStmt()
		if err != nil {
			return nil, err
		}
		prog.Stmts = append(prog.Stmts, stmt)
	}
	return prog, nil
}

func (p *Parser) parseStmt() (Node, error) {
	t := p.peek()
	switch t.Type {
	case TOK_INT_KW:
		return p.parseDeclStmt()
	case TOK_IDENT:
		return p.parseAssignStmt()
	default:
		return nil, fmt.Errorf("%d:%d: unexpected token %s (%q) at start of statement",
			t.Line, t.Col, t.Type, t.Value)
	}
}

func (p *Parser) parseDeclStmt() (*DeclStmt, error) {
	p.advance() // consume "int"
	var names []string
	id, err := p.expect(TOK_IDENT)
	if err != nil {
		return nil, err
	}
	names = append(names, id.Value)
	for p.peek().Type == TOK_COMMA {
		p.advance() // consume ","
		id, err = p.expect(TOK_IDENT)
		if err != nil {
			return nil, err
		}
		names = append(names, id.Value)
	}
	if _, err := p.expect(TOK_SEMICOLON); err != nil {
		return nil, err
	}
	return &DeclStmt{Names: names}, nil
}

func (p *Parser) parseAssignStmt() (*AssignStmt, error) {
	id := p.advance() // consume IDENT
	t := p.peek()
	var isNew bool
	switch t.Type {
	case TOK_ASSIGN:
		p.advance()
		isNew = false
	case TOK_WALRUS:
		p.advance()
		isNew = true
	default:
		return nil, fmt.Errorf("%d:%d: expected = or :=, got %s (%q)",
			t.Line, t.Col, t.Type, t.Value)
	}
	expr, err := p.parseExpr()
	if err != nil {
		return nil, err
	}
	if _, err := p.expect(TOK_SEMICOLON); err != nil {
		return nil, err
	}
	return &AssignStmt{Name: id.Value, Expr: expr, IsNew: isNew}, nil
}

func (p *Parser) parseExpr() (Node, error) {
	left, err := p.parseTerm()
	if err != nil {
		return nil, err
	}
	for p.peek().Type == TOK_PLUS || p.peek().Type == TOK_MINUS {
		op := p.advance()
		right, err := p.parseTerm()
		if err != nil {
			return nil, err
		}
		left = &BinaryExpr{Op: op.Value, Left: left, Right: right}
	}
	return left, nil
}

func (p *Parser) parseTerm() (Node, error) {
	left, err := p.parseUnary()
	if err != nil {
		return nil, err
	}
	for p.peek().Type == TOK_STAR {
		op := p.advance()
		right, err := p.parseUnary()
		if err != nil {
			return nil, err
		}
		left = &BinaryExpr{Op: op.Value, Left: left, Right: right}
	}
	return left, nil
}

func (p *Parser) parseUnary() (Node, error) {
	if p.peek().Type == TOK_MINUS {
		p.advance()
		operand, err := p.parseUnary()
		if err != nil {
			return nil, err
		}
		return &UnaryExpr{Op: "-", Operand: operand}, nil
	}
	return p.parsePrimary()
}

func (p *Parser) parsePrimary() (Node, error) {
	t := p.peek()
	switch t.Type {
	case TOK_INT:
		p.advance()
		v, err := strconv.ParseInt(t.Value, 10, 64)
		if err != nil {
			return nil, fmt.Errorf("%d:%d: invalid integer %q", t.Line, t.Col, t.Value)
		}
		return &NumberLit{Value: v}, nil

	case TOK_IDENT:
		p.advance()
		return &Ident{Name: t.Value}, nil

	case TOK_LPAREN:
		p.advance() // consume "("
		expr, err := p.parseExpr()
		if err != nil {
			return nil, err
		}
		if _, err := p.expect(TOK_RPAREN); err != nil {
			return nil, err
		}
		return expr, nil

	default:
		return nil, fmt.Errorf("%d:%d: unexpected token %s (%q) in expression",
			t.Line, t.Col, t.Type, t.Value)
	}
}