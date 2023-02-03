#include <bits/stdc++.h>
//in contest attempt
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         int curmax = array[k];
         int curxor = array[k];
         for(int j = k+1; j < n; j++){
            curmax = max(curmax,array[j]);
            curxor ^= array[j];
            answer = max(answer,curmax ^ curxor);
         }
      }
      
      cout << answer << endl;
   }
   
   
   return 0;
}