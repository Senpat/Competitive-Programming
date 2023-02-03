#include <bits/stdc++.h>

using namespace std;


const long long MOD = 998244353LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 301;
   int N2 = N*N;
   vector<vector<long long>> dp(N, vector<long long>(2*N2,0LL));
   
   int n;
   cin >> n;
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   dp[2][array[2]+array[1]+N2] = 1;
   dp[2][array[2]-array[1]+N2] = 1;
   
   for(int k = 2; k < n-1; k++){
      for(int j = 0; j < 2*N2; j++){
         if(dp[k][j] == 0LL) continue;
         
         int x = j-N2;
         
         dp[k+1][array[k+1]+x+N2] = dp[k+1][array[k+1]+x+N2] + dp[k][j];
         if(dp[k+1][array[k+1]+x+N2] >= MOD) dp[k+1][array[k+1]+x+N2] -= MOD;
         
         if(x == 0) continue;
         
         dp[k+1][array[k+1]-x+N2] = dp[k+1][array[k+1]-x+N2] + dp[k][j];
         if(dp[k+1][array[k+1]-x+N2] >= MOD) dp[k+1][array[k+1]-x+N2] -= MOD;
         
         
         
      }
   }
   
   long long answer = 0LL;
   for(int k = 0; k < 2*N2; k++){
      answer = (answer + dp[n-1][k] + MOD)%MOD;
   }
   
   cout << answer << endl;
   
   return 0;
}