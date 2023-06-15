#include <bits/stdc++.h>


int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
   
   int n,x;
   std::cin >> n >> x;
   
   std::vector<int> coins(n);
   for(int k = 0; k < n; k++){
      std::cin >> coins[k];
   }
   
   sort(coins.begin(),coins.end());
   
   std::vector<int> dp(x+1,INT_MAX);
   
   dp[0] = 0L;
   for(int k = 0; k < x; k++){
      if(dp[k] == INT_MAX) continue;
      for(int j = 0; j < n; j++){
         if(k+coins[j] <= x){
            dp[k+coins[j]] = std::min(dp[k+coins[j]],dp[k]+1);
         }
      }
      
   }
   
   if(dp[x] == INT_MAX){
      std::cout << -1 << std::endl;
   } else {
      std::cout << dp[x] << std::endl;
   }
      
   
   
   return 0;
}