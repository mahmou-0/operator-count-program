package odev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Lexical {

    private final List<Operator> operators;
    private final String UNARY = "UNARY";
    private final String BINARY = "BINARY";
    private final String ARITHMETIC = "ARITHMETIC";
    private final String RELATIONAL = "RELATIONAL";
    private final String LOGICAL = "LOGICAL";
    private int unaryOperationsCount;
    private int binaryOperationsCount;
    private int arithmeticOperationsCount;
    private int relationalOperationsCount;
    private int logicalOperationsCount;

    public Lexical() {
        operators = Arrays.asList(
                new Operator("++", Arrays.asList(ARITHMETIC, UNARY)),
                new Operator("--", Arrays.asList(ARITHMETIC, UNARY)),
                new Operator("+=", ARITHMETIC), new Operator("-=", ARITHMETIC),
                new Operator("/=", ARITHMETIC), new Operator("*=", ARITHMETIC),
                new Operator("%=", ARITHMETIC), new Operator("&=", ARITHMETIC),
                new Operator("|=", ARITHMETIC), new Operator("^=", ARITHMETIC),
                new Operator("<=", RELATIONAL), new Operator(">=", RELATIONAL),
                new Operator("==", RELATIONAL), new Operator("!=", RELATIONAL),
                new Operator("&&", LOGICAL), new Operator("||", LOGICAL),
              //  new Operator("+", ARITHMETIC), new Operator("-", ARITHMETIC),
              //  new Operator("*", ARITHMETIC), new Operator("/", ARITHMETIC),
              //  new Operator("%", ARITHMETIC), new Operator("&", ARITHMETIC),
              //  new Operator("|", ARITHMETIC), new Operator("^", ARITHMETIC),
              //  new Operator("=", ARITHMETIC),
                new Operator("<", RELATIONAL),
                new Operator("+", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("-", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("*", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("/", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("%", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("&", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("|", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("^", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator("=", Arrays.asList(ARITHMETIC, BINARY)),
                new Operator(">", RELATIONAL), new Operator("!", LOGICAL));
    }

    public void calculate(String inputFilePath) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(inputFilePath));
            String line;
            boolean currentlyInMultilineComment = false;
            while ((line = file.readLine()) != null) {
            	line = line.trim();
            	if(line.startsWith("/*")){
	                currentlyInMultilineComment = true;
	            }
	            if(!currentlyInMultilineComment && !line.startsWith("//")){
	            	int indexOfInlineCommentStart = line.indexOf("//");
	            	if(indexOfInlineCommentStart != -1) {
	            		line = line.substring(0, indexOfInlineCommentStart);
	            	}
	                for (int i = 0; i < operators.size(); i++) {
	                    Operator currentOperator = operators.get(i);
	                    if (line.contains(currentOperator.getStr())) {
	                        incrementCounter(currentOperator.getTypes());
	                        int lineLength = line.length();
	                        int operatorIndex = line.indexOf(currentOperator.getStr());
	                        if (lineLength > operatorIndex + currentOperator.getStr().length()) {
	                            line = line.substring(0, line.indexOf(currentOperator.getStr())) +
	                                    line.substring(operatorIndex + currentOperator.getStr().length());
	                        } else {
	                            line = line.substring(0, line.indexOf(currentOperator.getStr()));
	                        }
	                        i = 0;
	                    }
	                }
	            }
	            if(line.startsWith("*/") && currentlyInMultilineComment) {
	                currentlyInMultilineComment = false;
	            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void incrementCounter(List<String> types) {
        for (String type : types) {
            switch (type) {
                case ARITHMETIC:
                    arithmeticOperationsCount++;
                    break;
                case RELATIONAL:
                    relationalOperationsCount++;
                    break;
                case LOGICAL:
                    logicalOperationsCount++;
                    break;
                case UNARY:
                    unaryOperationsCount++;
                    break;
                case BINARY:
                    binaryOperationsCount++;
                    break;
            }
        }
    }

    @Override
    public String toString() {
    	System.out.println("The Information Of Operator:");
    	
        return "Unary Operations Count= " + unaryOperationsCount +
                "\n	Binary Operations Count= " + binaryOperationsCount +
                "\n	Arithmetic Operations Count= " + arithmeticOperationsCount +
                "\n	Relational Operations Count= " + relationalOperationsCount +
                "\n	Logical Operations Count= " + logicalOperationsCount +
                "\n The information Of perand\n	The Operand Count=" +
                (2*(arithmeticOperationsCount+relationalOperationsCount+logicalOperationsCount)
                		-unaryOperationsCount );
    }
}
