package by.siarhei.information.parser;

import by.siarhei.information.parser.api.AbstractParser;

import java.util.List;
import java.util.stream.Collectors;

public class ParseWordToLetters  {

   public List<Character> parse(String str)
    {
        return str.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
    }
}
