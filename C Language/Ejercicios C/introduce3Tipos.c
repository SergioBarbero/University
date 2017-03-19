void introduceFloat(float a);
void introduceDouble(double b);
void introduceEntero(int c);

int main(){
  float *a;
  double *b;
  int *c;
  introduceFloat(*a);
  introduceDouble(*b);
  introduceEntero(*c);
  printf("\nFloat: %f \nDouble: %lf \nEntero: %d", *a, *b, *c);
  return 0;
}

void introduceFloat(float *a){
  printf("Introduce un float: ");
  scanf("%f",a);
}

void introduceDouble(double *b){
  printf("\nIntroduce un double: ");
  scanf("%lf", b);
}

void introduceEntero(int *c){
  printf("\nIntroduce un entero: ");
  scanf("%d", c);
}
