#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<int> array(n,0);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      int has = 0;
      for(int k = 0; k < m; k++){
         if(array[k] <= m) has++;
      }
      
      int answer = m-has;
      cout << answer << "\n";
   }
   
   return 0;
}