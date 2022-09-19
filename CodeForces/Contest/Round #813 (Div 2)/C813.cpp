#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n,0);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      int answer = 0;
      vector<bool> zero(n+1,false);
      
      int last0 = 0;
      for(int k = 0; k < n-1; k++){
         int a = zero[array[k]] ? 0 : array[k];
         int b = zero[array[k+1]] ? 0 : array[k+1];
         
         if(a > b){
            for(int j = last0; j <= k; j++){
               if(!zero[array[j]]){
                  answer++;
                  zero[array[j]] = true;
               }
            }
            
            last0 = k+1;
         }
      }
      
      cout << answer << "\n";
   }
   
   return 0;
}