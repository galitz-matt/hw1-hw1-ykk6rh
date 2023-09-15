package test.java.edu.virginia.sde.hw1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static main.java.edu.virginia.sde.hw1.MainTools.getFileExtension;

public class GetFileExtensionTest {

    @Test
    public void testCSVFile() {
        assertEquals("csv", getFileExtension("test.csv"));
    }

    @Test
    public void testPathWithBackslash() {
        assertEquals("csv", getFileExtension("\\test.csv"));
    }

    @Test
    public void testPathWithManyBackslashes() {
        assertEquals("csv", getFileExtension("C:\\Users\\Me\\Documents\\test_resources\\test.csv"));
    }

    @Test
    public void testBadPath() {
        assertThrows(RuntimeException.class, () -> getFileExtension("test"));
    }

    @Test
    public void testPathWithLeadingPeriod() {
        assertEquals("csv", getFileExtension(".\\test.csv"));
    }
}
