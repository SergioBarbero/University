	#include<stdlib.h>
	#include<string.h>
	#include<ctype.h>
	
	#define DESP 2
	#define INIMAYUS 65
	#define FINMAYUS 90
	#define INIMINUS 97
	#define FINMINUS 122
	#define N_CHAR 24
	char encriptado;
%%

[a-zA-Z]	{if(tolower(yytext[0]) == yytext[0]){ //Es minuscula
		  if((yytext[0] + DESP) > FINMINUS){ //Vuelve a empezar
		    encriptado = yytext[0] - N_CHAR + DESP - 2;
		    printf("%c", encriptado);
		  }else{
		    encriptado = yytext[0]+DESP;
		    printf("%c", encriptado);
		  }
		}else{
		    if((yytext[0] + DESP) > FINMAYUS){ //Vuelve a empezar
		      encriptado = yytext[0] - N_CHAR + DESP - 2;
		      printf("%c", encriptado);
		    }else{
		      encriptado = yytext[0]+DESP;
		      printf("%c", encriptado);
		  }
		}
	      }
