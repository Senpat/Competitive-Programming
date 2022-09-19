#include <bits/stdc++.h>

using namespace std;


long long MOD = 998244353LL;

int S = 650;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<long long> sum(n+1,0LL);
   
   vector<vector<long long>> dp(2,vector<long long>(n+1,0LL));
   for(int k = m; k <= n; k += m){
      dp[0][k] = 1LL;
      sum[k] += dp[0][k];
   }
   
   //int thresh = 0;
   for(int x = 1; x < S; x++){
      int i = m+x;
      
      //thresh += i;
      //if(thresh > n) break;
      vector<long long> total(i,0LL);
      
      int v = 1;
      for(int k = 1; k <= n; k++){
         long long prev = total[v];
         total[v] = prev + dp[0][k];
         if(total[v] >= MOD) total[v] -= MOD;
         
         dp[1][k] = prev;
         
         v++;
         if(v >= i) v -= i;
      }
      
      for(int k = 1; k <= n; k++){
         sum[k] += dp[1][k];
         if(sum[k] >= MOD) sum[k] -= MOD;
         
         //dp[0][k] = dp[1][k];
         //dp[k][1] = 0LL;
      }
      
      swap(dp[0],dp[1]);
      //fill(dp[1].begin(),dp[1].end(),n);
      
      /*
      for(int k = 1; k <= n; k++){
         cout << dp[k][x] << " ";
      }
      cout << endl;*/
   }
     
   for(int k = 1; k <= n; k++){
      cout << sum[k] << " ";
   }
   cout << "\n"; 
   /*
   for(int k = 1; k <= n; k++){
      long long sum = 0LL;
      for(int j = 0; j < S; j++){
         sum = (sum + dp[k][j] + MOD)%MOD;
      }
      cout << sum << " ";
   }
   cout << "\n";
   */
   
   return 0;
}