package test;

import main.KonvertierungJFrame;

import static org.junit.jupiter.api.Assertions.*;

class KonvertierungJFrameTest {

    @org.junit.jupiter.api.Test
    void makeAsLongAs() {
        assertEquals("Hallo     ", KonvertierungJFrame.makeAsLongAs(10, "Hallo"));
    }

    @org.junit.jupiter.api.Test
    void nCharacters() {
        assertEquals("cccc", KonvertierungJFrame.nCharacters(4,'c'));
        assertEquals("          ", KonvertierungJFrame.nCharacters(10,' '));
    }
}