package com.mon.pdlconverter;


import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum TokenType {
    START,
    END,
    IDENTIFIER,
    NUMBER,
    OPERATOR,
    DECLARATOR,
    READ,
    COMMA,
    LEFT_PAREN,
    RIGHT_PAREN,
    PRINT,
    DQUETS,
    ASSIGN,
    AS,
    STRING,
    INTEGER,
    FLOAT,
    BOOLEAN,
    ADD,
    DOT,
    CONDITION,
    IF,
    THEN,
    ENDIF,
    UNKNOWN


}

public class Lexical_Analyser {

    private String inputString;
    private int currentPosition;

    static String inputrole = "(?i)read";
    static String assignrole = "(?i)assign";

    public Lexical_Analyser(String inputString) {
        this.inputString = inputString;
        this.currentPosition = 0;
    }

    public Lexical_Analyser() {

    }

    public Token getNextToken() {
        if (currentPosition >= inputString.length()) {
            return null;
        }

        // Define regular expressions for the tokens
        Pattern whitespacePattern = Pattern.compile("\\s+");
        Pattern numberPattern = Pattern.compile("\\d+");
        Pattern identifierPattern = Pattern.compile("[a-zA-Z]+");
        Pattern operatorPattern = Pattern.compile("[+\\-*/=]+");

        Pattern declaratorPattern = Pattern.compile(":=");//declarator
        Pattern startPattern = Pattern.compile("(?i)start");
        Pattern endPattern = Pattern.compile("(?i)end");

        Pattern readPattern = Pattern.compile(inputrole);
        Pattern commaPattern = Pattern.compile(",");

        Pattern left_parnPattern = Pattern.compile("\\(");
        Pattern right_parnPattern = Pattern.compile("\\)");
        Pattern printPattern = Pattern.compile("(?i)print");
        Pattern dquetsPattern = Pattern.compile("\"");


        // assign variables
        Pattern assignPattern = Pattern.compile(assignrole);
        Pattern asPattern = Pattern.compile("(?i)as");

        Pattern stringPattern = Pattern.compile("(?i)str(ing)?");
        Pattern intPattern = Pattern.compile("(?i)int(eger)?");
        Pattern floatPattern = Pattern.compile("(?i)float");
        Pattern boolPattern = Pattern.compile("(?i)bool(ean)?");
        Pattern addPattern = Pattern.compile("(?i)add?");
        Pattern conditionpattern = Pattern.compile("[<>]");

        Pattern dotpattern = Pattern.compile("\\.");
        Pattern ifpattern = Pattern.compile("(?i)if");
        Pattern thenpattern = Pattern.compile("(?i)then?");
        Pattern endifpattern = Pattern.compile("(?i)end if?");


        // Find the next token in the input string
        Matcher whitespaceMatcher = whitespacePattern.matcher(inputString.substring(currentPosition));
        Matcher numberMatcher = numberPattern.matcher(inputString.substring(currentPosition));
        Matcher identifierMatcher = identifierPattern.matcher(inputString.substring(currentPosition));
        Matcher operatorMatcher = operatorPattern.matcher(inputString.substring(currentPosition));
        Matcher declaratorMatcher = declaratorPattern.matcher(inputString.substring(currentPosition));
        Matcher commaMatcher = commaPattern.matcher(inputString.substring(currentPosition));

        Matcher startMatcher = startPattern.matcher(inputString.substring(currentPosition));
        Matcher endMatcher = endPattern.matcher(inputString.substring(currentPosition));
        Matcher left_parnMatcher = left_parnPattern.matcher(inputString.substring(currentPosition));
        Matcher right_parnMatcher = right_parnPattern.matcher(inputString.substring(currentPosition));
        Matcher readnMatcher = readPattern.matcher(inputString.substring(currentPosition));
        Matcher printMatcher = printPattern.matcher(inputString.substring(currentPosition));
        Matcher dquetsMatcher = dquetsPattern.matcher(inputString.substring(currentPosition));

        // assign variables
        Matcher assignMatcher = assignPattern.matcher(inputString.substring(currentPosition));
        Matcher asMatcher = asPattern.matcher(inputString.substring(currentPosition));

        Matcher stringMatcher = stringPattern.matcher(inputString.substring(currentPosition));
        Matcher intMatcher = intPattern.matcher(inputString.substring(currentPosition));
        Matcher floatMatcher = floatPattern.matcher(inputString.substring(currentPosition));
        Matcher boolMatcher = boolPattern.matcher(inputString.substring(currentPosition));
        Matcher addMatcher = addPattern.matcher(inputString.substring(currentPosition));

        Matcher dotMatcher = dotpattern.matcher(inputString.substring(currentPosition));
        Matcher ifMatcher = ifpattern.matcher(inputString.substring(currentPosition));
        Matcher thenMatcher = thenpattern.matcher(inputString.substring(currentPosition));
        Matcher endifMatcher = endifpattern.matcher(inputString.substring(currentPosition));
        Matcher conditionMatcher = conditionpattern.matcher(inputString.substring(currentPosition));


        if (whitespaceMatcher.lookingAt()) {
            currentPosition += whitespaceMatcher.end();
            return getNextToken();
        } else if (numberMatcher.lookingAt()) {
            Token token = new Token(TokenType.NUMBER, numberMatcher.group());
            currentPosition += numberMatcher.end();
            return token;
        } else if (startMatcher.lookingAt()) {
            Token token = new Token(TokenType.START, startMatcher.group());
            currentPosition += startMatcher.end();
            return token;
        } else if (declaratorMatcher.lookingAt()) {
        Token token = new Token(TokenType.DECLARATOR, declaratorMatcher.group());
        currentPosition += declaratorMatcher.end();
        return token;
        }else if (operatorMatcher.lookingAt()) {
            Token token = new Token(TokenType.OPERATOR, operatorMatcher.group());
            currentPosition += operatorMatcher.end();
            return token;
        }
        //brackets
        else if (left_parnMatcher.lookingAt()) {
            Token token = new Token(TokenType.LEFT_PAREN, left_parnMatcher.group());
            currentPosition += left_parnMatcher.end();
            return token;
        }else if (right_parnMatcher.lookingAt()) {
            Token token = new Token(TokenType.RIGHT_PAREN, right_parnMatcher.group());
            currentPosition += right_parnMatcher.end();
            return token;
        }else if (readnMatcher.lookingAt()) {
            Token token = new Token(TokenType.READ, readnMatcher.group());
            currentPosition += readnMatcher.end();
            return token;
        }else if (commaMatcher.lookingAt()) {
            Token token = new Token(TokenType.COMMA, commaMatcher.group());
            currentPosition += commaMatcher.end();
            return token;
        }
        //print
        else if (printMatcher.lookingAt()) {
            Token token = new Token(TokenType.PRINT, printMatcher.group());
            currentPosition += printMatcher.end();
            return token;
        }else if (dquetsMatcher.lookingAt()) {
            Token token = new Token(TokenType.DQUETS, dquetsMatcher.group());
            currentPosition += dquetsMatcher.end();
            return token;
        }
        //assign variables
        else if (assignMatcher.lookingAt()) {
            Token token = new Token(TokenType.ASSIGN, assignMatcher.group());
            currentPosition += assignMatcher.end();
            return token;
        }else if (asMatcher.lookingAt()) {
            Token token = new Token(TokenType.AS, asMatcher.group());
            currentPosition += asMatcher.end();
            return token;
        }else if (stringMatcher.lookingAt()) {
            Token token = new Token(TokenType.STRING, stringMatcher.group());
            currentPosition += stringMatcher.end();
            return token;
        }else if (intMatcher.lookingAt()) {
            Token token = new Token(TokenType.INTEGER, intMatcher.group());
            currentPosition += intMatcher.end();
            return token;
        }else if (floatMatcher.lookingAt()) {
            Token token = new Token(TokenType.FLOAT, floatMatcher.group());
            currentPosition += floatMatcher.end();
            return token;
        }else if (boolMatcher.lookingAt()) {
            Token token = new Token(TokenType.BOOLEAN, boolMatcher.group());
            currentPosition += boolMatcher.end();
            return token;
        }else if (addMatcher.lookingAt()) {
            Token token = new Token(TokenType.ADD, addMatcher.group());
            currentPosition += addMatcher.end();
            return token;
        }
        else if (conditionMatcher.lookingAt()) {
            Token token = new Token(TokenType.CONDITION, conditionMatcher.group());
            currentPosition += conditionMatcher.end();
            return token;
        }

        else if (dotMatcher.lookingAt()) {
            Token token = new Token(TokenType.DOT, dotMatcher.group());
            currentPosition += dotMatcher.end();
            return token;
        }else if (ifMatcher.lookingAt()) {
            Token token = new Token(TokenType.IF, ifMatcher.group());
            currentPosition += ifMatcher.end();
            return token;
        }else if (thenMatcher.lookingAt()) {
            Token token = new Token(TokenType.THEN, thenMatcher.group());
            currentPosition += thenMatcher.end();
            return token;
        }else if (endifMatcher.lookingAt()) {
            Token token = new Token(TokenType.ENDIF, endifMatcher.group());
            currentPosition += endifMatcher.end();
            return token;
        }else if (ifMatcher.lookingAt()) {
            Token token = new Token(TokenType.IF, ifMatcher.group());
            currentPosition += ifMatcher.end();
            return token;
        }else if (endMatcher.lookingAt()) {
            Token token = new Token(TokenType.END, endMatcher.group());
            currentPosition += endMatcher.end();
            return token;
        }
        else if (identifierMatcher.lookingAt()) {
            Token token = new Token(TokenType.IDENTIFIER, identifierMatcher.group());
            currentPosition += identifierMatcher.end();
            return token;
        }else {
            Token token = new Token(TokenType.UNKNOWN, inputString.substring(currentPosition, currentPosition + 1));
            currentPosition += 1;
            return token;
        }
    }

    public List<Token> tokenizeEditText(EditText editText) {
        String text = editText.getText().toString();
        Lexical_Analyser lexicalAnalyzer = new Lexical_Analyser(text);
        List<Token> tokens = new ArrayList<>();
        Token token = lexicalAnalyzer.getNextToken();

        while (token != null) {
            tokens.add(token);
            token = lexicalAnalyzer.getNextToken();
        }

        return tokens;
    }

    public void printTokenList(List<Token> tokenList) {
        for (Token token : tokenList) {
            System.out.println(token.getType() + ": " + token.getValue());
        }
    }


}

class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + type + ", " + value + "]";
    }
}


