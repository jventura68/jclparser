// $ANTLR 3.3 Nov 30, 2010 12:45:30 /home/guig/Escritorio/antlr/registro.g 2010-12-21 22:37:28
package org.rlnieto.regparser;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class registroParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "NUMERO", "PIC", "CHAR", "MASCARA", "COMMENT", "WS", "ESC_SEQ", "STRING", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "NEWLINE", "'DCL'", "'1'", "','", "';'", "'DEF'", "'BASED'", "'('", "')'"
    };
    public static final int EOF=-1;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int ID=4;
    public static final int NUMERO=5;
    public static final int PIC=6;
    public static final int CHAR=7;
    public static final int MASCARA=8;
    public static final int COMMENT=9;
    public static final int WS=10;
    public static final int ESC_SEQ=11;
    public static final int STRING=12;
    public static final int HEX_DIGIT=13;
    public static final int UNICODE_ESC=14;
    public static final int OCTAL_ESC=15;
    public static final int NEWLINE=16;

    // delegates
    // delegators


        public registroParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public registroParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return registroParser.tokenNames; }
    public String getGrammarFileName() { return "/home/guig/Escritorio/antlr/registro.g"; }



    // $ANTLR start "registro"
    // /home/guig/Escritorio/antlr/registro.g:3:1: registro : 'DCL' ( '1' ID ( opciones )? ',' ) ( NUMERO variable ( ',' )? )+ ';' ;
    public final void registro() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:3:11: ( 'DCL' ( '1' ID ( opciones )? ',' ) ( NUMERO variable ( ',' )? )+ ';' )
            // /home/guig/Escritorio/antlr/registro.g:3:13: 'DCL' ( '1' ID ( opciones )? ',' ) ( NUMERO variable ( ',' )? )+ ';'
            {
            match(input,17,FOLLOW_17_in_registro11); 
            // /home/guig/Escritorio/antlr/registro.g:3:19: ( '1' ID ( opciones )? ',' )
            // /home/guig/Escritorio/antlr/registro.g:3:20: '1' ID ( opciones )? ','
            {
            match(input,18,FOLLOW_18_in_registro14); 
            match(input,ID,FOLLOW_ID_in_registro16); 
            // /home/guig/Escritorio/antlr/registro.g:3:27: ( opciones )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=21 && LA1_0<=22)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /home/guig/Escritorio/antlr/registro.g:3:27: opciones
                    {
                    pushFollow(FOLLOW_opciones_in_registro18);
                    opciones();

                    state._fsp--;


                    }
                    break;

            }

            match(input,19,FOLLOW_19_in_registro21); 

            }

            // /home/guig/Escritorio/antlr/registro.g:3:42: ( NUMERO variable ( ',' )? )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==NUMERO) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/guig/Escritorio/antlr/registro.g:3:43: NUMERO variable ( ',' )?
            	    {
            	    match(input,NUMERO,FOLLOW_NUMERO_in_registro25); 
            	    pushFollow(FOLLOW_variable_in_registro27);
            	    variable();

            	    state._fsp--;

            	    // /home/guig/Escritorio/antlr/registro.g:3:59: ( ',' )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==19) ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // /home/guig/Escritorio/antlr/registro.g:3:59: ','
            	            {
            	            match(input,19,FOLLOW_19_in_registro29); 

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match(input,20,FOLLOW_20_in_registro33); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "registro"


    // $ANTLR start "opciones"
    // /home/guig/Escritorio/antlr/registro.g:5:1: opciones : ( 'DEF' | 'BASED' ) '(' ID ')' ;
    public final void opciones() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:6:2: ( ( 'DEF' | 'BASED' ) '(' ID ')' )
            // /home/guig/Escritorio/antlr/registro.g:6:4: ( 'DEF' | 'BASED' ) '(' ID ')'
            {
            if ( (input.LA(1)>=21 && input.LA(1)<=22) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            match(input,23,FOLLOW_23_in_opciones52); 
            match(input,ID,FOLLOW_ID_in_opciones54); 
            match(input,24,FOLLOW_24_in_opciones56); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "opciones"


    // $ANTLR start "variable"
    // /home/guig/Escritorio/antlr/registro.g:7:1: variable : ID type ;
    public final void variable() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:7:10: ( ID type )
            // /home/guig/Escritorio/antlr/registro.g:7:13: ID type
            {
            match(input,ID,FOLLOW_ID_in_variable65); 
            pushFollow(FOLLOW_type_in_variable67);
            type();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "variable"


    // $ANTLR start "type"
    // /home/guig/Escritorio/antlr/registro.g:10:1: type : ( PIC | CHAR );
    public final void type() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:10:5: ( PIC | CHAR )
            // /home/guig/Escritorio/antlr/registro.g:
            {
            if ( (input.LA(1)>=PIC && input.LA(1)<=CHAR) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type"

    // Delegated rules


 

    public static final BitSet FOLLOW_17_in_registro11 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_registro14 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_registro16 = new BitSet(new long[]{0x0000000000680000L});
    public static final BitSet FOLLOW_opciones_in_registro18 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_registro21 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMERO_in_registro25 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variable_in_registro27 = new BitSet(new long[]{0x0000000000180020L});
    public static final BitSet FOLLOW_19_in_registro29 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_20_in_registro33 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_opciones43 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_opciones52 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_opciones54 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_opciones56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_variable65 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_type_in_variable67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_type0 = new BitSet(new long[]{0x0000000000000002L});

}