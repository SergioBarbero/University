%{
#include<stdio.h>
#include<string.h>
int et = 0;
int salto = 0;
%}

%union {
  int i;
  char *c;
}

%token <i> NUM
%token <c> ID
%token IF
%token THEN
%token FI
%token ELSE
%token WHILE
%token DO
%token OD
%token FOR
%token TO
%token PRINT


%%

stmtlist: stmtlist stmt 
	    | stmt
	    ;
stmt: assig {$<i>$=salto;}
	| struc {$<i>$=salto;}
	;
assig: ID { $<c>$=strdup($1);strcpy($<c>$,$1); printf("\tvalori %s\n", $1);} '=' arithexp { printf("\tasigna\n");}
	; 
struc: IF arithexp { printf("\tsifalsovea LBL%d\n", $<i>0);} THEN {salto=++$<i>0;} stmtlist {printf("\tvea LBL%d\n", $<i>0);} alternative
	 | WHILE { printf("LBL%d\n", salto);} arithexp  { printf("\tsifalsovea LBL%d\n", ++$<i>0);} DO {salto=++$<i>0;} stmtlist  OD {printf("\tvea LBL%d\n", $<i>-6);}{printf("LBL%d\n", ++$<i>-6);}
	 | FOR assig {printf("\tvalori V0\n");} TO arithexp {printf("\tasigna\nLBL%d\n", salto);} DO {printf("\tvalord %s\n\tvalord V0\n\tsub\n", $<c>2);} {printf("\tsifalsovea LBL%d\n", ++$<i>0); salto=++$<i>0;} {printf("\tvalori %d\n\tvalord %s\n\tmete 1\n\tsum\n\tasigna\nvea LBL%d", $<i>-1 ,$<c>2, $<i>0 );} stmtlist OD 
	 | PRINT '(' arithexp ')' { printf("\tprint\n"); }
	 ;

alternative: FI {printf("LBL%d\n", $<i>-6);}
		| ELSE {printf("LBL%d\n", --$<i>-7);} stmtlist  FI  {printf("LBL%d\n", ++$<i>-7);}
arithexp: arithexp '+' multexp { $<i>$=$<i>1+$<i>3; } { printf("\tsum\n"); }
	    | arithexp '-' multexp { $<i>$=$<i>1-$<i>3; } { printf("\tsub\n"); }
	    | multexp 
	    ;
multexp: multexp '*' value { $<i>$=$<i>1*$<i>3; } { printf("\tmul\n"); }
	   | multexp '/' value { $<i>$=$<i>1/$<i>3; } { printf("\tdiv\n"); }
	   | value { $<i>$=salto; }
	   ;
value: NUM { printf("\tmete %d\n", $<i>1); }
	   | ID 	{ printf("\tvalord %s\n", $<c>1); }
	   | '(' arithexp ')'
	   ;
%%
yyerror(char *s){printf("%s\n",s);}
main(){ yyparse(); }


