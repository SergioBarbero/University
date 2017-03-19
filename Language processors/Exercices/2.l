	int nchar = 0;
	int nli = 0;

%%

.		{nchar++;}
\n		{if(nchar == 0)
		  printf("%d", ++nli);
		 nchar = 0;}
 

