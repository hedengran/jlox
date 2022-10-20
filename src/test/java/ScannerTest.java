import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScannerTest {

    @Test
    void shouldTokenizeNumbers() {
        String source = """
                42
                1337.420
                .17
                123.
                """;

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.NUMBER, "42", 42d, 1),
                        new Token(TokenType.NUMBER, "1337.420", 1337.420d, 2),
                        new Token(TokenType.DOT, ".", null, 3),
                        new Token(TokenType.NUMBER, "17", 17d, 3),
                        new Token(TokenType.NUMBER, "123", 123d, 4),
                        new Token(TokenType.DOT, ".", null, 4),
                        new Token(TokenType.EOF, "", null, 5)
                ),
                tokens
        );
    }


    @Test
    void shouldTokenizeIdentifiers() {
        String source = """
                abc _abc abc_123
                _123 _ abcedfghijklmnopqrstuvwxyz0123456789ABCEDFGHIJKLMNOPQRSTUVWXYZ
                """;

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.IDENTIFIER, "abc", null, 1),
                        new Token(TokenType.IDENTIFIER, "_abc", null, 1),
                        new Token(TokenType.IDENTIFIER, "abc_123", null, 1),
                        new Token(TokenType.IDENTIFIER, "_123", null, 2),
                        new Token(TokenType.IDENTIFIER, "_", null, 2),
                        new Token(TokenType.IDENTIFIER, "abcedfghijklmnopqrstuvwxyz0123456789ABCEDFGHIJKLMNOPQRSTUVWXYZ", null, 2),
                        new Token(TokenType.EOF, "", null, 3)
                ),
                tokens
        );
    }

    @Test
    void shouldTokenizeSymbols() {
        String source = """
                (){},.-+;*/!!====>>=<<=
                """;

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.LEFT_PAREN, "(", null, 1),
                        new Token(TokenType.RIGHT_PAREN, ")", null, 1),
                        new Token(TokenType.LEFT_BRACE, "{", null, 1),
                        new Token(TokenType.RIGHT_BRACE, "}", null, 1),
                        new Token(TokenType.COMMA, ",", null, 1),
                        new Token(TokenType.DOT, ".", null, 1),
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Token(TokenType.SEMICOLON, ";", null, 1),
                        new Token(TokenType.STAR, "*", null, 1),
                        new Token(TokenType.SLASH, "/", null, 1),

                        // One or two character tokens.
                        new Token(TokenType.BANG, "!", null, 1),
                        new Token(TokenType.BANG_EQUAL, "!=", null, 1),
                        new Token(TokenType.EQUAL_EQUAL, "==", null, 1),
                        new Token(TokenType.EQUAL, "=", null, 1),
                        new Token(TokenType.GREATER, ">", null, 1),
                        new Token(TokenType.GREATER_EQUAL, ">=", null, 1),
                        new Token(TokenType.LESS, "<", null, 1),
                        new Token(TokenType.LESS_EQUAL, "<=", null, 1),
                        new Token(TokenType.EOF, "", null, 2)
                ),
                tokens
        );
    }

    @Test
    void shouldTokenizeStrings() {
        String source = """
                "string"
                ""
                """;

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.STRING, "\"string\"", "string", 1),
                        new Token(TokenType.STRING, "\"\"", "", 2),
                        new Token(TokenType.EOF, "", null, 3)
                ),
                tokens
        );
    }
    @Test

    void shouldTokenizeKeywords() {
        String source = "and class else false fun for if nil or print return super this true var while";

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.AND,"and", null, 1),
                        new Token(TokenType.CLASS,"class", null, 1),
                        new Token(TokenType.ELSE,"else", null, 1),
                        new Token(TokenType.FALSE,"false", null, 1),
                        new Token(TokenType.FUN,"fun", null, 1),
                        new Token(TokenType.FOR,"for", null, 1),
                        new Token(TokenType.IF,"if", null, 1),
                        new Token(TokenType.NIL,"nil", null, 1),
                        new Token(TokenType.OR,"or", null, 1),
                        new Token(TokenType.PRINT,"print", null, 1),
                        new Token(TokenType.RETURN,"return", null, 1),
                        new Token(TokenType.SUPER,"super", null, 1),
                        new Token(TokenType.THIS,"this", null, 1),
                        new Token(TokenType.TRUE,"true", null, 1),
                        new Token(TokenType.VAR,"var", null, 1),
                        new Token(TokenType.WHILE,"while", null, 1),
                        new Token(TokenType.EOF, "", null, 1)
                ),
                tokens
        );
    }


    @Test
    void shouldIgnoreComments() {
        String source = """
                // this is a comment
                123 // // this is also a comment
                //* this is not multiline comment
                // okay last comment I promise
                """;

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.NUMBER, "123", 123d, 2),
                        new Token(TokenType.EOF, "", null, 5)
                ),
                tokens
        );
    }

    @Test
    void shouldTokenizeWhitespace() {
        String source = """
                spaces          tabs			newlines
                
                
                end
                """;

        List<Token> tokens = new Scanner(source).scanTokens();

        Assertions.assertEquals(
                List.of(
                        new Token(TokenType.IDENTIFIER, "spaces", null, 1),
                        new Token(TokenType.IDENTIFIER, "tabs", null, 1),
                        new Token(TokenType.IDENTIFIER, "newlines", null, 1),
                        new Token(TokenType.IDENTIFIER, "end", null, 4),
                        new Token(TokenType.EOF, "", null, 5)
                ),
                tokens
        );
    }
}