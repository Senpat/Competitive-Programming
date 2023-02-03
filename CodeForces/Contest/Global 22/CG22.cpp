#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 105;
   //
   //[num0][num1]
   vector<vector<bool>> even(N,vector<bool>(N,false));
   vector<vector<bool>> odd(N,vector<bool>(N,false));
   
   even[0][0] = true;
   odd[0][0] = false;
   
   for(int k = 0; k < N; k++){
      for(int j = 0; j < N; j++){
         if(k == 0 && j == 0) continue;
         
         if((j)%2 == 0){
            //check if even is possible (opponent want odd)
            if(k > 0 && !odd[k-1][j]) even[k][j] = true;
            if(j > 0 && !odd[k][j-1]) even[k][j] = true;
            
            //check if odd is possible (opponent want even)
            if(k > 0 && !even[k-1][j]) odd[k][j] = true;
            if(j > 0 && !even[k][j-1]) odd[k][j] = true;
            
         } else {
            //check if even is possible (opponent want even)
            if(k > 0 && !even[k-1][j]) even[k][j] = true;
            if(j > 0 && !even[k][j-1]) even[k][j] = true;
            
            //check if odd is possible (oppoent want odd)
            if(k > 0 && !odd[k-1][j]) odd[k][j] = true;
            if(j > 0 && !odd[k][j-1]) odd[k][j] = true;
         }
         
           
      }
   }
   /*
   //print
   int pn = 6;
   for(int k = 0; k < pn; k++){
      for(int j = 0; j < pn; j++){
         cout << (int)even[k][j] << " ";
      }
      cout << endl;
   }*/
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      int o = 0;
      int e = 0;
      for(int k = 0; k < n; k++){
         int i;
         cin >> i;
         if(abs(i)%2 == 0){
            e++;
         } else {
            o++;
         }
      }
      
      if(even[e][o]){
         cout << "Alice\n";
      } else {
         cout << "Bob\n";
      }
   }
   
   
   return 0;
}