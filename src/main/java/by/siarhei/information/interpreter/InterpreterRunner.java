package by.siarhei.information.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InterpreterRunner {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String expression = "98&";
        Client interpreter = new Client(expression);
        logger.info(interpreter.calculate());
    }
}
