#include<stdio.h>

int main(){
  int a, b;
  printf("a: ");
  scanf("%d", &a);
  printf("\nb: ");
  scanf("%d", &b);
  printf("a^b = %d\n", potencia(a, b));
}

int potencia(a, b){
  if(b == 0)
    return 1;
  else{
    return a * potencia(a, b-1);
  }
}


  
