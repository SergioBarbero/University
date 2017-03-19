#include<stdio.h>

void escribeMensaje(int);
void escribeDiv(int);
int introduceNum(void);
int esPar(int);
int esPrimo(int);
/**
  *@Description: Programa que pasado un número, muestra en pantalla sus divisores, dice si es primo o no y dice si es par o impar.
  *@Author: Sergio Barbero Báscones
  *
  *
  */
int main(){
  int n;
  n = introduceNum();
  escribeDiv(n);
  escribeMensaje(n);
}

int introduceNum(){
  int n;
  printf("Introduce un número: ");
  scanf("%d", &n);
  return n;
}

void escribeDiv(int n){
  if(esPrimo(n) == 0){
    int i;
    printf("Divisores de %d: ", n);
      for( i = 1; i <= n; i++){
	if(esPrimo(i) && n%i == 0){
	  printf("%d, ", i);
      }
    }
      printf("\n");
  }else
    printf("Divisores de %d: %d, %d. \n",n, 1, n);
}

void escribeMensaje(int n){
  if(esPar(n) == 1){
    if(n != 2)
      printf("El número %d es par y no es primo\n", n);
    else
      printf("El número %d es par y es primo\n", n);
  }else{
    if(esPrimo(n) == 1)
      printf("El número %d es impar y es primo\n", n);
    else
      printf("El número %d es impar y no es primo\n", n);
  }
}

int esPar(int n){
  if(n%2==0)
    return 1;
  else
    return 0;
}

int esPrimo(int n){
  if(esPar(n) == 1 && n != 2)
    return 0;
  else{
    int i;
    if(n == 1 || n == 2)
      return 1;
    else{
      for(i = 2; i<n; i++){
        if(n%i == 0)
	  return 0;
      }
      return 1;
    }      
  }
}
