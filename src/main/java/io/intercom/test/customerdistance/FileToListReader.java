package io.intercom.test.customerdistance;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Reads lines from text file
 *
 * @author nailgun
 * @since 11.08.15
 */
public class FileToListReader {

    public List<String> read(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            throw new NullPointerException();
        }
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(this.getClass().getResourceAsStream(fileName));
        ArrayList<String> ret = new ArrayList<>();
        while (s.hasNextLine()){
            ret.add(s.nextLine());
        }
        s.close();
        return ret;
    }

}
