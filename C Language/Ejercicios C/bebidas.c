/**
  *@Author: Sergio Barbero Báscones
  *
  *
  *
  *
  */
#include<stdio.h>
#include<math.h>
#define MAX 5

float leeImporteIntroducido(void);
float leeValorBebida(void);
float decrementaValor(float, float);

int main(){
  float dinero, bebida;
  dinero = leeImporteIntroducido();
  if(dinero > MAX){
    printf("Introduzca un valor igual o menor que 5\n");
    return 0;
  }
  bebida = leeValorBebida();
  if (decrementaValor(dinero, bebida) == 0)
    return 0;
}

float leeImporteIntroducido(){
  float dinero;
  printf("Introduce el dinero: ");
  scanf("%f",&dinero);
  return dinero;
}

float leeValorBebida(){
  float bebida;
  printf("Introduce el valor de la bebida: ");
  scanf("%f",&bebida);
  return bebida;
}

float decrementaValor(float dinero, float bebida){
  int n = 0, i;
  float valor[6];
  float vueltas = dinero - bebida;
  valor[0] = 2;
  valor[1] = 1;
  valor[2] = 0.5;
  valor[3] = 0.2;
  valor[4] = 0.1;
  valor[5] = 0.05;
  for(i=0; i<6; ++i){
    printf("%d %f\n", i, vueltas);
    while(vueltas >= valor[i]){
      n++;
      vueltas = vueltas- valor[i];
    }  
    printf("Monedas de %.2f€: %d\n", valor[i], n);
    n = 0;
  }
  return vueltas;
}
