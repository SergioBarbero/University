	#include <ctype.h>
	#include<stdlib.h>
	#include<string.h>

	int i = 0;
%%

[A-Z]	{printf("%c", tolower(yytext[0]));}	

