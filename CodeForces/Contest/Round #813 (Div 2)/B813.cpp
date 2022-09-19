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
      
      vector<int> answer(n,0);
      int start = 0;
      if(n % 2 == 1) {
         start = 1;
         answer[0] = 1;
      }
      
      for(int k = start; k < n; k += 2){
         answer[k] = k+2;
         answer[k+1] = k+1;
      }
      
      for(int k = 0; k < n; k++){
         cout << answer[k] << " ";
      }
      cout << "\n";
      
   }
   
   return 0;
}