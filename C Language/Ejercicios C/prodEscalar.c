#define TAM 2;


int main(){
  float v1[TAM], v2[TAM];


float escalarDosV(float v1[TAM], float v2[TAM]){
  int i;
  float producto = 0;
  for(i = 0; i < TAM; i++){
    producto +=v1[i]*v2[i];
  }
  return producto;
}
