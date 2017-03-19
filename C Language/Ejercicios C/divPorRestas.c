#include<stdio.h>

int main(){
  int a, b;
  printf("Introduce un a: ");
  scanf("%d", &a);
  printf("Introduce un b: ");
  scanf("%d", &b);
  printf("%d / %d = %d\n",a , b, division(a, b));
}

int division(int a, int b){
  if(a < b)
    return 0;
  else
    return division(a-b,b)+1;
}
