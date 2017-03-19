#include<stdio.h>

int main(){
  int n;
  printf("Introduce n: ");
  scanf("%d", &n);
  printf("f(%d): %d\n", n, fibonacci(n));

}


int fibonacci(int n){
  if(n == 0 || n == 1){
    if(n == 0)
      return 0;
    else
      return 1;
  }else
    return fibonacci(n-1)+fibonacci(n-2);
}

