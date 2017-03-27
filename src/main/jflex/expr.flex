package edu.ecu.cs.seng6245.imp.parser;

import beaver.Symbol;
import beaver.Scanner;

import edu.ecu.cs.seng6245.imp.parser.ExpressionParser.Terminals;

%%

%class ExpressionScanner
%extends Scanner
%public
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
    return newToken(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column
%{
    private Symbol newToken(short id)
    {
        return new Symbol(id, yyline + 1, yycolumn + 1, yylength());
    }

    private Symbol newToken(short id, Object value)
    {
        return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
    }
%}
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

Number = [:digit:] [:digit:]*

Identifier = [:jletter:][:jletterdigit:]*

StringLiteral = [\"] [^\"]* [\"]

%%

{WhiteSpace}+    { /* ignore */ }

<YYINITIAL> {
    {Number}     { return newToken(Terminals.NUMBER, new Integer(yytext())); }

    "subsetOf"   { return newToken(Terminals.SUBSET); }
    "foreach"    { return newToken(Terminals.FOREACH);}
    "while"      { return newToken(Terminals.WHILE);  }
    "print"      { return newToken(Terminals.PRINT);  }
    "begin"      { return newToken(Terminals.BEGIN);  }
    "notin"      { return newToken(Terminals.NOTIN);  }
    "using"      { return newToken(Terminals.USING);  }
    "false"      { return newToken(Terminals.FALSE);  }
    "true"       { return newToken(Terminals.TRUE);   }
    "then"       { return newToken(Terminals.THEN);   }
    "else"       { return newToken(Terminals.ELSE);   }
    "head"       { return newToken(Terminals.HEAD);   }
    "tail"       { return newToken(Terminals.TAIL);   }
    "end"        { return newToken(Terminals.END);    }
    "for"        { return newToken(Terminals.FOR);    }
    "to"         { return newToken(Terminals.TO);     }
    "if"         { return newToken(Terminals.IF);     }
    "do"         { return newToken(Terminals.DO);     }
    "in"         { return newToken(Terminals.IN);     }

    "<="         { return newToken(Terminals.LTEQ);   }
    ">="         { return newToken(Terminals.GTEQ);   }
    "=="         { return newToken(Terminals.EQ);     }
    "!="         { return newToken(Terminals.NEQ);    }
    "<"          { return newToken(Terminals.LT);     }
    ">"          { return newToken(Terminals.GT);     }
	"("          { return newToken(Terminals.LPAREN); }
	")"          { return newToken(Terminals.RPAREN); }
	"*"          { return newToken(Terminals.MULT);   }
	"/"          { return newToken(Terminals.DIV);    }
	"+"          { return newToken(Terminals.PLUS);   }
	"-"          { return newToken(Terminals.MINUS);  }
	";"          { return newToken(Terminals.SEMI);   }
	"="          { return newToken(Terminals.ASSIGN); }
	"{"          { return newToken(Terminals.LCURLY); }
	"}"          { return newToken(Terminals.RCURLY); }
	"["          { return newToken(Terminals.LSQUARE);}
	"]"          { return newToken(Terminals.RSQUARE);}
	"#"          { return newToken(Terminals.HASH);   }
	"!"          { return newToken(Terminals.BANG);   }
	","          { return newToken(Terminals.COMMA);  }
    "&"          { return newToken(Terminals.AND);    }
    "|"          { return newToken(Terminals.OR);     }

    {Identifier} { return newToken(Terminals.IDENTIFIER, new String(yytext())); }

    {StringLiteral} { 
        String withQuotes = new String(yytext());
        String withoutQuotes = withQuotes.substring(1,withQuotes.length()-1);
        return newToken(Terminals.STRING, withoutQuotes);
    }


}

.|\n             { throw new Scanner.Exception("unexpected character '" + yytext() + "'"); }
