#include<stdio.h>

int main(){
  int v1[5];
  int v2[5];
  int i = 0;
  for(i = 0; i < 5; i++){
    printf("Introduce un valor para el elemento %d: ", i);
    scanf("%d", &v1[i]);
    v2[i] = 2*v1[i];
    printf("\nValor %d de v2: %d\n",i, v2[i]);
  }
}
