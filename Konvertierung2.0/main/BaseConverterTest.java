package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseConverterTest {

    private BaseConverter baseConverter;

    @BeforeEach
    void setUp() {
        baseConverter = new BaseConverter();
    }

    @Test
    void convert() {
        assertEquals("0", baseConverter.convert("0", 2, 10).stripTrailingZeros().toPlainString());
        assertEquals("1", baseConverter.convert("1", 2, 10).stripTrailingZeros().toPlainString());
        assertEquals("3", baseConverter.convert("11", 2, 10).stripTrailingZeros().toPlainString());
    }
}