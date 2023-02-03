#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int qq = 0; qq < t; qq++){
      int n;
      cin >> n;
      
      int c;
      cin >> c;
      vector<int> costs(c,0);
      for(int k = 0; k < c; k++){
         cin >> costs[k];
      }
      
      int N = 20000;
      vector<int> dp(N,INT_MAX);
      dp[0] = 0;
      for(int k = 0; k < c; k++){
         for(int j = N-1; j >= 0; j--){
            if(dp[j] == INT_MAX) continue;
            
            if(j+costs[k] >= N) continue;
            dp[j+costs[k]] = min(dp[j+costs[k]],dp[j]+1);
         }
      }
      
      int a1 = -1;
      int a2 = -1;
      for(int k = n; k < N; k++){
         if(dp[k] != INT_MAX){
            a1 = k;
            a2 = dp[k];
            break;
         }
      }
      
      cout << a1 << " " << a2 << "\n";
   
   }
   
   
   
   
   return 0;
}