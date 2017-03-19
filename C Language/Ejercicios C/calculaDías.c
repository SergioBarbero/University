#include<stdio.h>

int main(){
  int dia, mes, anno;
  printf("Introduzca una fecha (dd/mm/año): ");
  scanf("%d/%d/%d", &dia, &mes, &anno);
  if(mes>=1 && mes<=12){
    if(mes!=2){
     if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12)
       printf("El mes tiene 31 días");
     else
       printf("El mes tiene 30 días");
    }else{
      if((anno%4==0 && anno%100!=0 ) || anno%400)
	printf("El mes tiene 29 días");
      else
	printf("El mes tiene 28 días");
    }
  }
  return 0;
}
