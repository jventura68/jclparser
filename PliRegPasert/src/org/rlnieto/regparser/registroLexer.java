// $ANTLR 3.3 Nov 30, 2010 12:45:30 /home/guig/Escritorio/antlr/registro.g 2010-12-21 22:37:28
package org.rlnieto.regparser;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class registroLexer extends Lexer {
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

    public registroLexer() {;} 
    public registroLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public registroLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/guig/Escritorio/antlr/registro.g"; }

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:3:7: ( 'DCL' )
            // /home/guig/Escritorio/antlr/registro.g:3:9: 'DCL'
            {
            match("DCL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:4:7: ( '1' )
            // /home/guig/Escritorio/antlr/registro.g:4:9: '1'
            {
            match('1'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:5:7: ( ',' )
            // /home/guig/Escritorio/antlr/registro.g:5:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:6:7: ( ';' )
            // /home/guig/Escritorio/antlr/registro.g:6:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:7:7: ( 'DEF' )
            // /home/guig/Escritorio/antlr/registro.g:7:9: 'DEF'
            {
            match("DEF"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:8:7: ( 'BASED' )
            // /home/guig/Escritorio/antlr/registro.g:8:9: 'BASED'
            {
            match("BASED"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:9:7: ( '(' )
            // /home/guig/Escritorio/antlr/registro.g:9:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:10:7: ( ')' )
            // /home/guig/Escritorio/antlr/registro.g:10:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "PIC"
    public final void mPIC() throws RecognitionException {
        try {
            int _type = PIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:12:6: ( 'PIC' '\\'' ( '(' NUMERO ')' )* MASCARA '\\'' )
            // /home/guig/Escritorio/antlr/registro.g:12:8: 'PIC' '\\'' ( '(' NUMERO ')' )* MASCARA '\\''
            {
            match("PIC"); 

            match('\''); 
            // /home/guig/Escritorio/antlr/registro.g:12:19: ( '(' NUMERO ')' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='(') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/guig/Escritorio/antlr/registro.g:12:20: '(' NUMERO ')'
            	    {
            	    match('('); 
            	    mNUMERO(); 
            	    match(')'); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            mMASCARA(); 
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PIC"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:13:7: ( 'CHAR' '(' NUMERO ')' )
            // /home/guig/Escritorio/antlr/registro.g:13:10: 'CHAR' '(' NUMERO ')'
            {
            match("CHAR"); 

            match('('); 
            mNUMERO(); 
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:15:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/guig/Escritorio/antlr/registro.g:15:9: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/guig/Escritorio/antlr/registro.g:15:33: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/guig/Escritorio/antlr/registro.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "NUMERO"
    public final void mNUMERO() throws RecognitionException {
        try {
            int _type = NUMERO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:17:8: ( ( '0' .. '9' )+ )
            // /home/guig/Escritorio/antlr/registro.g:17:12: ( '0' .. '9' )+
            {
            // /home/guig/Escritorio/antlr/registro.g:17:12: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/guig/Escritorio/antlr/registro.g:17:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMERO"

    // $ANTLR start "MASCARA"
    public final void mMASCARA() throws RecognitionException {
        try {
            int _type = MASCARA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:18:9: ( ( '9' | 'Z' ) ( '9' | 'Z' | 'V' | ',' )* )
            // /home/guig/Escritorio/antlr/registro.g:18:11: ( '9' | 'Z' ) ( '9' | 'Z' | 'V' | ',' )*
            {
            if ( input.LA(1)=='9'||input.LA(1)=='Z' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/guig/Escritorio/antlr/registro.g:18:20: ( '9' | 'Z' | 'V' | ',' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==','||LA4_0=='9'||LA4_0=='V'||LA4_0=='Z') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/guig/Escritorio/antlr/registro.g:
            	    {
            	    if ( input.LA(1)==','||input.LA(1)=='9'||input.LA(1)=='V'||input.LA(1)=='Z' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MASCARA"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:24:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='/') ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1=='/') ) {
                    alt8=1;
                }
                else if ( (LA8_1=='*') ) {
                    alt8=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /home/guig/Escritorio/antlr/registro.g:24:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("//"); 

                    // /home/guig/Escritorio/antlr/registro.g:24:14: (~ ( '\\n' | '\\r' ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='\u0000' && LA5_0<='\t')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='\uFFFF')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /home/guig/Escritorio/antlr/registro.g:24:14: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    // /home/guig/Escritorio/antlr/registro.g:24:28: ( '\\r' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='\r') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /home/guig/Escritorio/antlr/registro.g:24:28: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 
                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /home/guig/Escritorio/antlr/registro.g:25:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 

                    // /home/guig/Escritorio/antlr/registro.g:25:14: ( options {greedy=false; } : . )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='*') ) {
                            int LA7_1 = input.LA(2);

                            if ( (LA7_1=='/') ) {
                                alt7=2;
                            }
                            else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                                alt7=1;
                            }


                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/guig/Escritorio/antlr/registro.g:25:42: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match("*/"); 

                    _channel=HIDDEN;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:28:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // /home/guig/Escritorio/antlr/registro.g:28:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:36:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/guig/Escritorio/antlr/registro.g:36:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/guig/Escritorio/antlr/registro.g:36:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\\') ) {
                    alt9=1;
                }
                else if ( ((LA9_0>='\u0000' && LA9_0<='!')||(LA9_0>='#' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                    alt9=2;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/guig/Escritorio/antlr/registro.g:36:14: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/guig/Escritorio/antlr/registro.g:36:24: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:40:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/guig/Escritorio/antlr/registro.g:40:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:44:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt10=1;
                    }
                    break;
                case 'u':
                    {
                    alt10=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt10=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /home/guig/Escritorio/antlr/registro.g:44:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // /home/guig/Escritorio/antlr/registro.g:45:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/guig/Escritorio/antlr/registro.g:46:9: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/guig/Escritorio/antlr/registro.g:49:8: ( ( '\\r' )? '\\n' )
            // /home/guig/Escritorio/antlr/registro.g:49:9: ( '\\r' )? '\\n'
            {
            // /home/guig/Escritorio/antlr/registro.g:49:9: ( '\\r' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/guig/Escritorio/antlr/registro.g:49:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:53:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt12=3;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\\') ) {
                int LA12_1 = input.LA(2);

                if ( ((LA12_1>='0' && LA12_1<='3')) ) {
                    int LA12_2 = input.LA(3);

                    if ( ((LA12_2>='0' && LA12_2<='7')) ) {
                        int LA12_4 = input.LA(4);

                        if ( ((LA12_4>='0' && LA12_4<='7')) ) {
                            alt12=1;
                        }
                        else {
                            alt12=2;}
                    }
                    else {
                        alt12=3;}
                }
                else if ( ((LA12_1>='4' && LA12_1<='7')) ) {
                    int LA12_3 = input.LA(3);

                    if ( ((LA12_3>='0' && LA12_3<='7')) ) {
                        alt12=2;
                    }
                    else {
                        alt12=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/guig/Escritorio/antlr/registro.g:53:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/guig/Escritorio/antlr/registro.g:53:14: ( '0' .. '3' )
                    // /home/guig/Escritorio/antlr/registro.g:53:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/guig/Escritorio/antlr/registro.g:53:25: ( '0' .. '7' )
                    // /home/guig/Escritorio/antlr/registro.g:53:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/guig/Escritorio/antlr/registro.g:53:36: ( '0' .. '7' )
                    // /home/guig/Escritorio/antlr/registro.g:53:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/guig/Escritorio/antlr/registro.g:54:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/guig/Escritorio/antlr/registro.g:54:14: ( '0' .. '7' )
                    // /home/guig/Escritorio/antlr/registro.g:54:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/guig/Escritorio/antlr/registro.g:54:25: ( '0' .. '7' )
                    // /home/guig/Escritorio/antlr/registro.g:54:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/guig/Escritorio/antlr/registro.g:55:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/guig/Escritorio/antlr/registro.g:55:14: ( '0' .. '7' )
                    // /home/guig/Escritorio/antlr/registro.g:55:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // /home/guig/Escritorio/antlr/registro.g:60:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/guig/Escritorio/antlr/registro.g:60:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 
            match('u'); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNICODE_ESC"

    public void mTokens() throws RecognitionException {
        // /home/guig/Escritorio/antlr/registro.g:1:8: ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | PIC | CHAR | ID | NUMERO | MASCARA | COMMENT | WS | STRING | NEWLINE )
        int alt13=17;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // /home/guig/Escritorio/antlr/registro.g:1:10: T__17
                {
                mT__17(); 

                }
                break;
            case 2 :
                // /home/guig/Escritorio/antlr/registro.g:1:16: T__18
                {
                mT__18(); 

                }
                break;
            case 3 :
                // /home/guig/Escritorio/antlr/registro.g:1:22: T__19
                {
                mT__19(); 

                }
                break;
            case 4 :
                // /home/guig/Escritorio/antlr/registro.g:1:28: T__20
                {
                mT__20(); 

                }
                break;
            case 5 :
                // /home/guig/Escritorio/antlr/registro.g:1:34: T__21
                {
                mT__21(); 

                }
                break;
            case 6 :
                // /home/guig/Escritorio/antlr/registro.g:1:40: T__22
                {
                mT__22(); 

                }
                break;
            case 7 :
                // /home/guig/Escritorio/antlr/registro.g:1:46: T__23
                {
                mT__23(); 

                }
                break;
            case 8 :
                // /home/guig/Escritorio/antlr/registro.g:1:52: T__24
                {
                mT__24(); 

                }
                break;
            case 9 :
                // /home/guig/Escritorio/antlr/registro.g:1:58: PIC
                {
                mPIC(); 

                }
                break;
            case 10 :
                // /home/guig/Escritorio/antlr/registro.g:1:62: CHAR
                {
                mCHAR(); 

                }
                break;
            case 11 :
                // /home/guig/Escritorio/antlr/registro.g:1:67: ID
                {
                mID(); 

                }
                break;
            case 12 :
                // /home/guig/Escritorio/antlr/registro.g:1:70: NUMERO
                {
                mNUMERO(); 

                }
                break;
            case 13 :
                // /home/guig/Escritorio/antlr/registro.g:1:77: MASCARA
                {
                mMASCARA(); 

                }
                break;
            case 14 :
                // /home/guig/Escritorio/antlr/registro.g:1:85: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 15 :
                // /home/guig/Escritorio/antlr/registro.g:1:93: WS
                {
                mWS(); 

                }
                break;
            case 16 :
                // /home/guig/Escritorio/antlr/registro.g:1:96: STRING
                {
                mSTRING(); 

                }
                break;
            case 17 :
                // /home/guig/Escritorio/antlr/registro.g:1:103: NEWLINE
                {
                mNEWLINE(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\1\14\1\25\2\uffff\1\14\2\uffff\3\14\1\15\3\uffff\1\22\3"+
        "\uffff\2\14\1\uffff\4\14\1\uffff\1\15\1\uffff\1\42\1\43\3\14\2\uffff"+
        "\1\14\1\uffff\1\14\1\51\2\uffff";
    static final String DFA13_eofS =
        "\52\uffff";
    static final String DFA13_minS =
        "\1\11\1\103\1\60\2\uffff\1\101\2\uffff\1\111\1\110\2\54\3\uffff"+
        "\1\12\3\uffff\1\114\1\106\1\uffff\1\123\1\103\1\101\1\54\1\uffff"+
        "\1\54\1\uffff\2\60\1\105\1\47\1\122\2\uffff\1\104\1\uffff\1\50\1"+
        "\60\2\uffff";
    static final String DFA13_maxS =
        "\1\172\1\105\1\71\2\uffff\1\101\2\uffff\1\111\1\110\2\132\3\uffff"+
        "\1\12\3\uffff\1\114\1\106\1\uffff\1\123\1\103\1\101\1\132\1\uffff"+
        "\1\132\1\uffff\2\172\1\105\1\47\1\122\2\uffff\1\104\1\uffff\1\50"+
        "\1\172\2\uffff";
    static final String DFA13_acceptS =
        "\3\uffff\1\3\1\4\1\uffff\1\7\1\10\4\uffff\1\13\1\14\1\16\1\uffff"+
        "\1\20\2\17\2\uffff\1\2\4\uffff\1\15\1\uffff\1\21\5\uffff\1\1\1\5"+
        "\1\uffff\1\11\2\uffff\1\12\1\6";
    static final String DFA13_specialS =
        "\52\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\22\1\21\2\uffff\1\17\22\uffff\1\22\1\uffff\1\20\5\uffff\1"+
            "\6\1\7\2\uffff\1\3\2\uffff\1\16\1\15\1\2\7\15\1\13\1\uffff\1"+
            "\4\5\uffff\1\14\1\5\1\11\1\1\13\14\1\10\11\14\1\12\4\uffff\1"+
            "\14\1\uffff\32\14",
            "\1\23\1\uffff\1\24",
            "\12\15",
            "",
            "",
            "\1\26",
            "",
            "",
            "\1\27",
            "\1\30",
            "\1\32\14\uffff\1\31\34\uffff\1\31\3\uffff\1\31",
            "\1\32\14\uffff\1\33\34\uffff\1\32\3\uffff\1\32",
            "",
            "",
            "",
            "\1\34",
            "",
            "",
            "",
            "\1\35",
            "\1\36",
            "",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\32\14\uffff\1\31\34\uffff\1\31\3\uffff\1\31",
            "",
            "\1\32\14\uffff\1\33\34\uffff\1\32\3\uffff\1\32",
            "",
            "\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            "\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\44",
            "\1\45",
            "\1\46",
            "",
            "",
            "\1\47",
            "",
            "\1\50",
            "\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | PIC | CHAR | ID | NUMERO | MASCARA | COMMENT | WS | STRING | NEWLINE );";
        }
    }
 

}