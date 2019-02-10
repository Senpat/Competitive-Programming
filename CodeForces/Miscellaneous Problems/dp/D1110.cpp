#include <bits/stdc++.h>

using namespace std;

#define MAXN 1000005

int a[MAXN];
int dp[MAXN][3][3];                 //[n][(n-1,n,n+2)][(n,n+1,n+2)]

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   for(int k = 0; k < n; k++){
      int a1;
      cin >> a1;
      a[a1]++;
   }
   
   
   for(int k = 1; k <= m; k++){
      for(int j = 0; j < 3; j++){
         for(int h = 0; h < 3; h++){
            for(int g = 0; g < 3; g++){
               if(j + h + g > a[k]) continue;
               //g is how many you dedicate for +0,+1,+2
               dp[k+1][h][g] = max(dp[k+1][h][g], dp[k][j][h] + g + (a[k]-g-j-h)/3);
            }
         }
      }
   }
   /*
   int answer = 0;
   for(int k = 0; k < 3; k++){
      for(int j = 0; j < 3; j++){
         cout << dp[m][k][j] << endl;
         answer = max(answer,dp[m][k][j] + k + j);
      }
   }
   
   cout << answer << endl;*/
   cout << dp[m+1][0][0];
   
   
   
   
   return 0;
}