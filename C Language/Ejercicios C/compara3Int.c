#include<stdio.h>

int main(){
  int a, b, c;
  printf("a = ");
  scanf("%d", &a);
  printf("\nb = ");
  scanf("%d", &b);
  printf("\nc = ");
  scanf("%d", &c);
  int suma = a+b+c;
  int menor, mediano, mayor;
  mayor = maximoDe2(maximoDe2(a,b),c);
  menor = minimoDe2(minimoDe2(a,b),c);
  mediano = calculaMediana(mayor, menor, suma);
  printf("\n%d < %d < %d\n", menor, mediano, mayor);
}
/**
  *@param a Primer valor
  *@param b Segundo valor
  *@return Valor mayor de los dos
  */
int maximoDe2(int a, int b){
  if(b<a)
    return a;
  else
    return b;
}
/**
  *@param a Primer valor
  *@param b Segundo valor
  *@return Valor menor de los 2
  */
int minimoDe2(int a, int b){
  if(a<b)
    return a;
  else
    return b;
}
/**
  *@param mayor Valor mayor de los 3
  *@param menor Valor menor de los 3
  *@param suma Suma de los 3 valores
  *@return mediana
  */
int calculaMediana(int mayor, int menor, int suma){
  return suma-(mayor+menor);
}
