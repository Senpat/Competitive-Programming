#include <bits/stdc++.h>
using namespace std;
 

int main() {
   int N = 1000000000;
   int i  =0;
   for(int k = 3; k < N/2; k+=2){
      for(int j = k; j < N; j+=k*2){
         i++;
      }
   }
   cout << i << endl; 
   
   
}