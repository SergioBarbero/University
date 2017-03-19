#include<stdio.h>

int main(){
  int v[10];
  int i;
  int resultado = 0;
  for(i= 0;i<10;i++){
    printf("\nValor %d: ", i);
    scanf("%d", &v[i]);
    resultado += v[i];
  }
  printf("resultado: %d\n", resultado);
}
