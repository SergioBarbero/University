/**
  *@Author: Sergio Barbero Báscones
  *@Name: convertidorBases
  *@Descripcion: Programa que pasa un número decimal a binario, octal y hexadecimal
  *
  *
  */
#include<stdio.h>

int calculaFactorial(int);
int calculaSumatorio(int);

int main(){
  int n;
  int factorial;
  int sumatorio;
  printf("Introduce un número: ");
  scanf("%d", &n);
  factorial = calculaFactorial(n);
  sumatorio = calculaSumatorio(n);
  printf("Factorial: %d", factorial);
  printf("Sumatorio: %d", sumatorio);
}

int calculaFactorial(int n){
  int resultado;
  if(n == 1 || n == 0)
    return 1;
  else
    return n * calculaFactorial(n-1);
}

int calculaSumatorio(int n){
  int resultado;
  if(n>0)
    return n + calculaSumatorio(n-1);
}
    

  

  

