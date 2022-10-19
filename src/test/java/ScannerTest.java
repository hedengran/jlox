import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScannerTest {

    @Test
    void shouldTokenizeNumbers() {
        String n100 = "100";
        String n10_1 = "10.1";
        String n0 = "0";
        String n99999999999999999999 = "99999999999999999999";
        String n0_111111111111 = "0.111111111111";
        String n42 = "42";

        String source = String.join(" ", List.of(n100, n10_1, n0, n99999999999999999999, n0_111111111111, n42));

        List<Token> tokens = new Scanner(source).scanTokens();

        int line = 1;

        Assertions.assertEquals(
                tokens,
                List.of(
                        new Token(TokenType.NUMBER, n100, 100d, line),
                        new Token(TokenType.NUMBER, n10_1, 10.1d, line),
                        new Token(TokenType.NUMBER, n0, 0d, line),
                        new Token(TokenType.NUMBER, n99999999999999999999, 99999999999999999999d, line),
                        new Token(TokenType.NUMBER, n0_111111111111, 0.111111111111d, line),
                        new Token(TokenType.NUMBER, n42, 42d, line),
                        new Token(TokenType.EOF, "", null, line)
                )
        );
    }

}