%{
#include "y.tab.h"
%}


%%

"IF"	return IF;
"THEN"	return THEN;
"ELSE"	return ELSE;
"FI"	return FI;

"WHILE"	return WHILE;
"FOR"	return FOR;
"TO"	return TO;
"DO"	return DO;
"OD"	return OD;


"PRINT"	return PRINT;

[0-9]+	{yylval.i = atoi(yytext);
		 return NUM;
		 }

([A-Z]|[a-z])+ {yylval.c = yytext;
			  return ID;
			}

[\ |"\t" |"\n"]	;

.	return yytext[0];

