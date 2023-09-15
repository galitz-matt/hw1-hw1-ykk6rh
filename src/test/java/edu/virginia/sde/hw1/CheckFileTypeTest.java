package test.java.edu.virginia.sde.hw1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static main.java.edu.virginia.sde.hw1.MainTools.checkFileType;

public class CheckFileTypeTest {

    @Test
    public void testCSVFile() {
        assertEquals("csv", checkFileType("test.csv"));
    }

    @Test
    public void testPathWithBackslash() {
        assertEquals("csv", checkFileType("\\test.csv"));
    }

    @Test
    public void testPathWithSeveralBackslashes() {
        assertEquals("csv", checkFileType("C:\\Users\\Me\\Documents\\test_resources\\test.csv"));
    }

    @Test
    public void testBadPath() {
        assertThrows(RuntimeException.class, () -> checkFileType("test"));
    }
}
