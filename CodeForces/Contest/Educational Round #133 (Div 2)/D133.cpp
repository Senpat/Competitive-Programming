#include <bits/stdc++.h>

using namespace std;


long long MOD = 998244353LL;

int S = 500;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<long long>> dp(n+1,vector<long long>(S,0LL));
   for(int k = m; k <= n; k += m){
      dp[k][0] = 1LL;
   }
   
   for(int x = 1; x < S; x++){
      int i = m+x;
      
      vector<long long> total(i,0LL);
      
      for(int k = 1; k <= n; k++){
         long long prev = total[k%i];
         total[k%i] = (prev + dp[k][x-1] + MOD)%MOD;
         dp[k][x] = (prev + MOD)%MOD;
      }
      
      /*
      for(int k = 1; k <= n; k++){
         cout << dp[k][x] << " ";
      }
      cout << endl;*/
   }
      
   
   for(int k = 1; k <= n; k++){
      long long sum = 0LL;
      for(int j = 0; j < S; j++){
         sum = (sum + dp[k][j] + MOD)%MOD;
      }
      cout << sum << " ";
   }
   cout << "\n";
   
   
   return 0;
}