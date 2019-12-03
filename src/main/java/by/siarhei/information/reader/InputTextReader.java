package by.siarhei.information.reader;

import by.siarhei.information.exception.InvalidInputFilePathException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputTextReader {
    private static final String DEFAULT_INPUT_FILE = "input/input.txt";
    private static final Logger logger = LogManager.getLogger();

    public String readData() throws InvalidInputFilePathException {
        logger.info("The input was obtained from the default source");
        return readData(DEFAULT_INPUT_FILE);
    }

    public String readData(String fileFolder) throws InvalidInputFilePathException {
        Path path = Paths.get(fileFolder);
        String content = "";
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            throw new InvalidInputFilePathException("File cannot be opened or does not exists", e);
        }
        return content;
    }
}
