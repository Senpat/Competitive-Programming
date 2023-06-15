#include <bits/stdc++.h>

const long long MOD = 1000000007LL;

int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
      
   int n,x;
   std::cin >> n >> x;
   
   std::vector<int> coins(n);
   for(int k = 0; k < n; k++){
      std::cin >> coins[k];
   }
   
   std::vector<long long> dp(x+1);
   dp[0] = 1L;
   for(int k = 0; k < n; k++){
      for(int j = 0; j < x; j++){
         if(j + coins[k] <= x){
            dp[j+coins[k]] = (dp[j+coins[k]] + dp[j] + MOD)%MOD;
         }
      }
   }
   
   std::cout << dp[x] << std::endl;
   
   return 0;
}