/**
  *@Author: Sergio Barbero Báscones
  *
  *
  *
  *
  */

int multRusa(int mtdo, int mtdor){
  int acu = 0;
  while(mtdor > 0){
    if(mtdor/2 != 0){
      acu += mtdo;
    }
    mtdo *= 2;
    mtdor /= 2;
  }
return acu;
}
