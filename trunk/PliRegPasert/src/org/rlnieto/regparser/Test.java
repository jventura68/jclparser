package org.rlnieto.regparser;

import org.antlr.runtime.*;
import java.io.*;


public class Test {
    public static void main(String[] args) throws Exception {

    	File fichero = new File("src/org/rlnieto/regparser/registro.txt");
    	FileInputStream is = new FileInputStream(fichero);
    	
    	ANTLRInputStream input = new ANTLRInputStream(is);
    	
    	System.out.println("Iniciando el análisis");
    	
        registroLexer lexer = new registroLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        registroParser parser = new registroParser(tokens);
        parser.registro();
        
    	System.out.println("Fin de la ejecución");
    }
}