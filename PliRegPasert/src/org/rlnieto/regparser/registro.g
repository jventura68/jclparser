grammar registro;

registro  :	'DCL' ('1' ID opciones? ',') (NUMERO variable ','?)+';';

opciones 
	:	('DEF'  | 'BASED') '(' ID ')' ;
variable :	 ID type;


type:   PIC  |   CHAR ;

PIC 	:	'PIC' '\'' ('(' NUMERO ')')* MASCARA '\'';
CHAR 	:	 'CHAR' '(' NUMERO ')';

ID  :   ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

NUMERO :   ('0'..'9')+ ;
MASCARA :	('9'|'Z')('9'|'Z'|'V'|',')*;


/*------------------------------------------------------------------------------------------------------------------*/

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

NEWLINE:'\r' ? '\n' ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
