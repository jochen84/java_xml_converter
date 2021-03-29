package org.example;

import org.example.exception.NoFileToConvertException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TheFileReaderTest {

    private TheFileReader reader;
    private List<String> lines;
    private File file;

    @BeforeEach
    void setUp() {
        reader = new TheFileReader();
        file = new File("src/test/java/org/example/testfile/text.txt");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readTextFile_Return_First_Line_Text() {
        lines = reader.readTextFile(file);
        String actual = lines.get(0);
        assertEquals(actual, "Testfile");
    }

    @Test
    void readTextFile_Is_3_lines(){
        lines = reader.readTextFile(file);
        int actual = lines.size();
        assertEquals(actual, 3);
    }

    @Test
    void readTextFile_bad_file_throws_NoFileToConvertException(){
        File noFile = new File("nofile.txt");
        assertThrows(NoFileToConvertException.class, () -> reader.readTextFile(noFile));
    }

}