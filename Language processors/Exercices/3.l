	int i = 0;

%%

" "|\t	{printf("%d%s", ++i, yytext);}

