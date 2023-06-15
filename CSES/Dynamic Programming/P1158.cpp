#include <bits/stdc++.h>


int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
   
   int n,x;
   std::cin >> n >> x;
   
   std::vector<int> price(n);
   std::vector<int> pages(n);
   for(int k = 0; k < n; k++){
      std::cin >> price[k];
   }
   for(int k = 0; k < n; k++){
      std::cin >> pages[k];
   }
   
   std::vector<int> dp(x+1,-1);
   dp[0] = 0;
   
   for(int k = 0; k < n; k++){
      for(int j = x-1; j >= 0; j--){
         if(dp[j] == -1) continue;
         int newj = price[k] + j;
         if(newj <= x){
            dp[newj] = std::max(dp[newj],pages[k] + dp[j]);
         }
      }
   }
   
   int answer = 0;
   for(int k = 0; k <= x; k++){
      answer = std::max(answer,dp[k]);
   }
   
   std::cout << answer << std::endl;
   
   return 0;
}