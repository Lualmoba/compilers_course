/******************************
 * This is the test driver for cs536 project 2 (fall 2018)
 * It opens a test file on the command line,
 * then calls the scanner generated by JLex to get the next token.
 * The token is displayed (for testing/debugging purposes) and the process is repeated
 * until the end of file token is reached.
 */


import java.io.*;
import java_cup.runtime.*;

public class P2 {

  public static void
  main(String args[]) throws java.io.IOException {

	if (args.length != 1) {
       		System.out.println(
			"Error: Input file must be named on command line." );
		System.exit(-1);
    	}

	
    	java.io.FileInputStream yyin = null; 

    	try {
    		yyin = new java.io.FileInputStream(args[0]);
    	} catch (FileNotFoundException notFound){
       		System.out.println ("Error: unable to open input file."); 
		System.exit(-1);
    	}

    // lex is a JLex-generated scanner that will read from yyon
    	Yylex lex = new Yylex(yyin);	

    	System.out.print ("Begin test of CSX scanner.");
    	System.out.println (" Scanning file "+
        		args[0]+ ":");



	Symbol token = lex.yylex();

/**********************
 * Extend the code in the switch to display all the valid CSX tokens.
 * 
 */
	
	while ( token.sym != sym.EOF ) {

    		System.out.print( ((CSXToken) token.value).linenum + ":"
				+ ((CSXToken) token.value).colnum + " ");

		switch (token.sym) {
		  case sym.INTLIT:
    			System.out.println("\tinteger literal(" +
				((CSXIntLitToken) token.value).intValue + ")");
			break;

		  case sym.PLUS:
    			System.out.println("\t+");
			break;

		  case sym.NOTEQ:
    			System.out.println("\t!=");
			break;

		  default:
			throw new RuntimeException();
		}

		token = lex.yylex(); // get next token
	}
    	System.out.println("End test of CSX scanner.");
  }

}
