package Split;

import junit.framework.TestCase;

import java.io.File;

public class SplitTest extends TestCase {
    private final Split split1 = new Split("ad", "а", 4, true);
    private final Split split2 = new Split("ad1", "а", 6, false);
    private final Split split3 = new Split("ad", "а", 4, false);

    public void testNumberLines() throws Exception {
        split1.numberLines("ad","а", 4, true);
        File file = new File("а1");
        File file2 = new File("а2");
        File file3 = new File("а3");
        assertTrue(file.exists());
        assertTrue(file2.exists());
        assertFalse(file3.exists());
    }

    public void testNumberSymbol() throws Exception {
        split2.numberSymbol("ad1","а", 6, false);
        File file = new File("аa");
        File file2 = new File("аb");
        File file3 = new File("аc");
        File file4 = new File("аd");
        File file5 = new File("аe");
        assertTrue(file.exists());
        assertTrue(file2.exists());
        assertTrue(file3.exists());
        assertTrue(file4.exists());
        assertFalse(file5.exists());
    }

    public void testNumberOutputFiles() throws Exception {
        split3.numberOutputFiles("ad","а", 4, true);
        File file = new File("а1");
        File file2 = new File("а2");
        File file3 = new File("а3");
        File file4 = new File("а4");
        File file5 = new File("а5");
        assertTrue(file.exists());
        assertTrue(file2.exists());
        assertTrue(file3.exists());
        assertTrue(file4.exists());
        assertFalse(file5.exists());
    }
}