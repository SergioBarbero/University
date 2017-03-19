/**
  *@Author: Sergio Barbero Báscones
  *
  *
  *
  *
  */
#include<stdio.h>

int main(){
  int n, a;
  printf("Escribe un número: ");
  scanf("%d", &n);
  for(a = 0; a <= 10; a++){
    printf("%d * %d = %d\n", n, a, n*a);
  }
}
