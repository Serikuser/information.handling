package by.siarhei.information.reader;

import by.siarhei.information.exception.InvalidInputFilePathException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InputTextReaderTest {

    private String expectedText;
    private InputTextReader reader;

    @BeforeClass
    private void setUp() {
        reader = new InputTextReader();
        expectedText = "   It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\r\n" +
                " typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in\r\n" +
                " “Динамо” (Рига) the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets\r\n" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software\r\n" +
                " like Aldus Faclon9 PageMaker including versions of Lorem Ipsum.\r\n" +
                "   It is a long a!=b established fact that a reader, will be distracted by the readable\r\n" +
                " content of a page when looking at its layout. The point of using\r\n" +
                " (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\r\n" +
                " distribution ob.toString(a?b:c) of letters, as opposed to using (Content here), content here', making it look\r\n" +
                " like readable English.\r\n" +
                "   It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\r\n" +
                " page when looking at its layout.\r\n" +
                "   Bye. Бандерлоги.";
    }

    @AfterClass
    private void setDown() {
        reader = null;
        expectedText = null;
    }

    @Test
    private void readFileFromPathTest() throws InvalidInputFilePathException {
        String actualText = reader.readData("input/input.txt");
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    private void readFileFromDefaultPathTest() throws InvalidInputFilePathException {
        String actualText = reader.readData();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(expectedExceptions = InvalidInputFilePathException.class)
    private void readFileFromWrongPathTest() throws InvalidInputFilePathException {
        String actualText = reader.readData("wrong path");
    }
}
