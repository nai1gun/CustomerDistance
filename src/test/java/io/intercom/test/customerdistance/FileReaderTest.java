package io.intercom.test.customerdistance;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * @author nailgun
 * @since 11.08.15
 */
public class FileReaderTest {

    @Test
    public void fileReaderTest() throws FileNotFoundException {
        FileToListReader cfr = new FileToListReader();
        List<String> testResult = cfr.read("testcustomers.txt");
        Assert.assertNotNull(testResult);
        Assert.assertEquals(testResult, Arrays.asList("test1", "test2"));
    }

    @Test(expected = FileNotFoundException.class)
    public void fileNotExistTest() throws FileNotFoundException {
        FileToListReader cfr = new FileToListReader();
        cfr.read("testcustomersNotExist.txt");
    }

    @Test(expected = NullPointerException.class)
    public void nullNameTest() throws FileNotFoundException  {
        FileToListReader cfr = new FileToListReader();
        cfr.read(null);
    }

}
