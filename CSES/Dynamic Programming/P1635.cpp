#include <bits/stdc++.h>

const long long MOD = 1000000007LL;

int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
   
   int n,x;
   std::cin >> n >> x;
   
   std::vector<int> array(n);
   for(int k = 0; k < n; k++){
      std::cin >> array[k];
   }
   
   std::vector<long long> dp(x+1);
   dp[0] = 1L;
   
   for(int k = 0; k < x; k++){
      for(int j = 0; j < n; j++){
         if(k+array[j] <= x){
            dp[k+array[j]] = (dp[k+array[j]] + dp[k] + MOD)%MOD;
         }
      }
   }
   
   std::cout << dp[x] << std::endl;
   
   
   return 0;
}