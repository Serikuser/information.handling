package by.siarhei.information.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InterpreterRunner {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String expression = "(7^5|1&2<<(2|5>>2&71))|1200";
        Client interpreter = new Client(expression);
        int test  = interpreter.calculate();
        int testss = (7^5|1&2<<(2|5>>2&71))|1200;
        logger.info(testss);
        logger.info(test);
    }
}
