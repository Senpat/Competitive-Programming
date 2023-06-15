#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007L;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   vector<vector<int>> dp(n,vector<int>(m+1));
   
   if(array[0] == 0){
      for(int k = 1; k <= m; k++){
         dp[0][k] = 1;
      }
   } else {
      dp[0][array[0]] = 1;
   }
   
   for(int k = 0; k < n-1; k++){
      for(int j = 1; j <= m; j++){
         if(dp[k][j] == 0) continue;
         
         if(j-1 >= 0 && (j-1 == array[k+1] || array[k+1] == 0)){
            dp[k+1][j-1] += dp[k][j];
            if(dp[k+1][j-1] >= MOD) dp[k+1][j-1] -= MOD;
         }
         if(j == array[k+1] || array[k+1] == 0){
            dp[k+1][j] += dp[k][j];
            if(dp[k+1][j] >= MOD) dp[k+1][j] -= MOD;
         }
         if(j+1 >= 0 && (j+1 == array[k+1] || array[k+1] == 0)){
            dp[k+1][j+1] += dp[k][j];
            if(dp[k+1][j+1] >= MOD) dp[k+1][j+1] -= MOD;
         }
      }
   }
   
   int answer = 0;
   for(int k = 1; k <= m; k++){
      answer += dp[n-1][k];
      if(answer >= MOD) answer -= MOD;
   }
   
   cout << answer << endl;
   
   
      
   
   
   return 0;
}