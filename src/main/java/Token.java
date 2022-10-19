import java.util.Objects;

class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return type + " " + lexeme + " " + literal + " " + line;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Token otherToken)) return false;

        return Objects.equals(this.type, otherToken.type) &&
                Objects.equals(this.lexeme, otherToken.lexeme) &&
                Objects.equals(this.literal, otherToken.literal) &&
                this.line == otherToken.line;

    }
}
