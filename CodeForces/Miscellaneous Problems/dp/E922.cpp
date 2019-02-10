#include <bits/stdc++.h>

using namespace std;

#define N 1005
#define M 10005

long long dp[N][M];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   long long W,B,X;
   cin >> n >> W >> B >> X;
   
   int c[n+1];
   for(int k = 0; k < n; k++){
      cin >> c[k];
   }
   
   long long costs[n+1];
   for(int k = 0; k < n; k++){
      cin >> costs[k];
   }
   
   fill_n(&dp[0][0],N*M,-1);
   
   dp[0][0] = W;
   
   for(int k = 0; k < n; k++){
      for(int j = 0; j < M; j++){
         if(dp[k][j] == -1) continue;
         //h is how many birds you add
         for(int h = 0; h <= c[k]; h++){
            if(dp[k][j] < costs[k]*h) break;
            dp[k+1][j+h] = max(dp[k+1][j+h], min(W+(j+h)*B,dp[k][j] - costs[k]*h + X));
         }
      }
   }
   
   
   int answer = -1;
   for(int k = M-1; k >= 0 && answer == -1; k--){
      if(dp[n][k] != -1) answer = k;
   }
   
   cout << answer << endl;
   
   
   
   
   
   
   return 0;
}