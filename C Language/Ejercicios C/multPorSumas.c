#include<stdio.h>

int main(){
  int a,b;
  printf("Introduce a: ");
  scanf("%d", &a);
  printf("Introduce b: ");
  scanf("%d", &b);
  printf("%d * %d = %d\n", a, b, multip(a, b));
}

int multip(int a, int b){
  if(b == 0)
    return 0;
  else
    return a + multip(a,--b);
}
