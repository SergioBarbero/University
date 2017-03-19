	#include <stdio.h>
	#include <string.h>
	#include <stdlib.h>

	int empty = 0;
	char *tag;
	char *ctag;

	char *prefix;
	char *cprefix;

	char *url;
	int n_char = 0;
	int n_int = 0;
	int n_float = 0;
	int n_cint = 0;
	int n_cfloat = 0;
	int cnum = 0;
	int num = 0;
word	[^ \t\n\>]+
name 	[a-zA-Z0-9]+
integer [0-9]+
exp 	[E|e][-+]?{integer}
float 	[-+]?{integer}(\.)?{integer}?{exp}?
blank	[\t|" "]
%x COM CDATA PI ETAG ATT NUM STAG XMLNS

%%

<INITIAL>"<!--" 				BEGIN(COM);
<INITIAL>\<NUM{blank}+VAL{blank}*\={blank}*	BEGIN(NUM);
<NUM>\'{integer}\'				{n_cint++; cnum++;}
<NUM>\'{float}\'				{n_cfloat++; cnum++;}
<COM>"-->"					BEGIN(INITIAL);
<INITIAL>"<!["CDATA"["				BEGIN(CDATA);
<CDATA>"]]>"					BEGIN(INITIAL);
<INITIAL>"<?"					BEGIN(PI);
<PI>"?>"					BEGIN(INITIAL);
<INITIAL>"</"   				BEGIN(ETAG);
<ETAG>">"					{if(cnum > num){ 
							tag = strdup(ctag);
							num = cnum;
							n_int = n_cint;
							n_float = n_cfloat;
							n_cint = n_cfloat = cnum = 0;
						}BEGIN(INITIAL);}
<INITIAL>"<"					BEGIN(STAG);
<STAG>{word}					{ctag = strdup(yytext); BEGIN(ATT);}	
<ATT>xmlns:					BEGIN(XMLNS);
<ATT>xmlns\=					{BEGIN(XMLNS); cprefix = "";}
<ATT>[^xmlns {name}]:{name}"="\"{name}\"	;
<XMLNS>{name}	 				{cprefix = strdup(yytext);}
<XMLNS>\"{word}\"				{if(yyleng > n_char){
							url = strdup(yytext);
							prefix = strdup(cprefix);
							n_char = yyleng;}
						BEGIN(ATT);}
<ATT>">"					BEGIN(INITIAL);
<ATT,NUM>"/>"					{empty++; BEGIN(INITIAL);}		
<INITIAL,COM,ETAG,PI,CDATA,ATT,XMLNS,NUM>.|\n 	;


%%

main(int argc, char **argv)
{
	if(argc > 1) {
		FILE *file;
		file=fopen(argv[1], "r");
		if(!file) {
			fprintf(stderr, "no se puede abrir %s\n", argv[1]);
			exit(1);
		}
		yyin = file;
	}
yylex();
	printf("\n- Elementos vacios: %d\n- La etiqueta %s tiene %d números.\nDe los cuales: %d son enteros y %d en punto flotante.\n- El valor más largo de espacio de nombres es %s ",  empty,tag, num, n_int, n_float, url);
	if(strcmp("", prefix) == 0) //URL no tiene prefijo asociado
		printf("y no tiene prefijo asociado.\n");
	else //Tiene prefijo asociado
		printf("y el prefijo asociado es %s.\n",prefix);
}
