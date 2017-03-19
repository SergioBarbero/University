/**
  *@Author: Sergio Barbero Báscones
  *@Name: convertidorBases
  *@Descripcion: Programa que pasa un número decimal a binario, octal y hexadecimal
  *
  *
  */
#include<stdio.h>

void cambioBaseX(int, int);
int leeNumeroDecimal(void);
void imprimeResultado(int);

int main(){
  int dec;
  dec = leeNumeroDecimal();
  printf("Número binario: ");
  cambioBaseX(dec, 2);
  printf("\nNúmero octal: ");
  cambioBaseX(dec, 8);
  printf("\nNúmero hexadecimal: ");
  cambioBaseX(dec, 16);
  printf("\n");
  return 0;
}

int leeNumeroDecimal(){
  int dec;
  printf("Introduce un número decimal: ");
  scanf("%d", &dec);
  return dec;
}

void cambioBaseX(int dec, int base){
  int res;  
  if(dec != 0){
    res = dec%base;    
    cambioBaseX(dec/base, base);
    if(base == 16){
      switch(res){
	case 10 : printf("A "); break;
        case 11 : printf("B "); break;
	case 12 : printf("C "); break;
	case 13 : printf("D "); break;
	case 14 : printf("E "); break;
	case 15 : printf("F "); break;
	default : imprimeResultado(res);
      }
    }else
       imprimeResultado(res);
  }
}

void imprimeResultado(int res){
  printf("%d ", res);
}
