package by.siarhei.information.parser;


import by.siarhei.information.parser.api.AbstractParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTokenToBitExpression extends AbstractParser {
    private static final String REGEX_BIT_EXPRESSION= "[\\d>|(&)~^]";

    public boolean isBitExpressionInToken(String token) {
        Pattern pattern = Pattern.compile(REGEX_BIT_EXPRESSION);
        Matcher matcher = pattern.matcher(token);
        return matcher.find();
    }

    @Override
    public List<String> parse(String str) {
        return null;
    }
}
