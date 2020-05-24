#include <bits/stdc++.h>

using namespace std;

long long MOD = 998244353;
const int MAXN = 502;

long long arr[MAXN];
long long dp[MAXN][MAXN][2];
long long psums[MAXN];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   
   cin >> n >> m;
   
   if(m==1){
      cout << 0 << endl;
      return 0;
   }
   
   arr[0] = 0;
   arr[1] = 1;
   
   for(int i = 2; i <= n; i++){
      
      dp[0][0][0] = 1;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < i; j++){
            for(int h = 0; h < 2; h++){
                  //simulate adding same color
               if(j<i-1){
                  if(j == i-2){
                     dp[k+1][j+1][1] = (dp[k+1][j+1][1]+dp[k][j][h]+MOD)%MOD;
                  } 
                  else {
                     dp[k+1][j+1][h] = (dp[k+1][j+1][h]+dp[k][j][h]+MOD)%MOD;
                  }
               }
                  //simulate adding different color
               dp[k+1][0][h] = (dp[k+1][0][h]+dp[k][j][h]+MOD)%MOD;
            }
         }
      }
      
      
      
      for(int k = 0; k < i; k++){
         arr[i] = (arr[i] + dp[n-1][k][1]+MOD)%MOD;
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < i; j++){
            for(int h = 0; h < 2; h++){
               dp[k][j][h] = 0;
            }
         }
      }
   }
   
   //psums
      
      
   psums[0] = 0;
      
   for(int k = 1; k <= n+1; k++){
      psums[k] = (psums[k-1]+arr[k-1]+MOD)%MOD;
   }
      
   long answer = 0L;
      
   for(int k = 1; k <= n; k++){
      if(k >= (int)m) 
         continue;
      int i = m/k;
      if(k*i==m) i--;
      i = min(i,n);
      long prod = (arr[k]*psums[i+1]+MOD)%MOD;
      answer = (answer + prod + MOD)%MOD;
   }
      
   answer = (answer*2 + MOD)%MOD;
   cout << answer << endl;
   
   return 0;
}