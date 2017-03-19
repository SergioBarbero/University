	int li = 0;

%%

.+	{printf("%d %s", ++li, yytext);}


