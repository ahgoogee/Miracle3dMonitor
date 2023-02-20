package miracle3d.base.util.symboltreeparser;


import java.util.ArrayList;
import java.util.List;

public class SymbolTreeParser {
    public SymbolTreeParser() {
    }

    public SymbolNode parse(String input) throws IllegalSymbolInputException {
        if (input != null && input.length() != 0) {
            if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')') {
                return parseReal(input);
            } else {
                throw new IllegalSymbolInputException("Input not embedded in braces: " + input);
            }
        } else {
            throw new IllegalSymbolInputException("Empty string");
        }
    }

    private static SymbolNode parseReal(String string) throws IllegalSymbolInputException {
        char[] input = string.toCharArray();
        List<Object> tmpchildren = new ArrayList(5);
        int index = 0;
        int level = 0;

        int startIndex;
        for(startIndex = 0; index < input.length && level >= 0; ++index) {
            switch (input[index]) {
                case ' ':
                    if (level == 0) {
                        if (index > startIndex) {
                            tmpchildren.add(string.substring(startIndex, index));
                        }

                        startIndex = index + 1;
                    }
                    break;
                case '(':
                    if (level == 0) {
                        if (index > startIndex) {
                            tmpchildren.add(string.substring(startIndex, index));
                        }

                        startIndex = index + 1;
                    }

                    ++level;
                    break;
                case ')':
                    --level;
                    if (level == 0) {
                        tmpchildren.add(parseReal(string.substring(startIndex, index)));
                        startIndex = index + 1;
                    }
            }
        }

        if (index > startIndex) {
            tmpchildren.add(string.substring(startIndex, index));
        }

        if (level != 0) {
            IllegalSymbolInputException up = new IllegalSymbolInputException("Missing brackets in input: " + string);
            throw up;
        } else {
            return new SymbolNode(tmpchildren);
        }
    }
}
