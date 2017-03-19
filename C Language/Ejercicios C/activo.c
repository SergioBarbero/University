#include<stdio.h>

int main(){
  int n;
  scanf("%d", &n);
  procedimiento(&n);
  printf("n es igual a: %d", n);
}

void procedimiento(int *n){
  *n= *n * 2;
}

