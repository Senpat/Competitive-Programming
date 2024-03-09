#include <bits/stdc++.h>
//connected components dp
using namespace std;

const long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   //dp[x][y][z] - first x numbers, y components
   //z = 0 -> last number in new component (middle)
   //z = 1 -> last number in new component (end)
   //z = 2 -> last number at end of component (middle)
   //z = 3 -> last number at end of component (end)
   //z = 4 -> last number joined component
   vector<vector<vector<long long>>> dp(n,vector<vector<long long>>(n+1,vector<long long>(5,0LL)));
   
   dp[0][1][1] = 1LL;
   
   for(int k = 0; k < n-1; k++){
      for(int j = 1; j <= n; j++){
         long long lj = (long long)j;
         //make new component (you can always do it)
         if(j+1 <= n){
            //middle
            dp[k+1][j+1][0] = (dp[k+1][j+1][0] + dp[k][j][0]*(lj-1LL) + MOD)%MOD;
            dp[k+1][j+1][0] = (dp[k+1][j+1][0] + dp[k][j][1]*(lj-1LL) + MOD)%MOD;
            dp[k+1][j+1][0] = (dp[k+1][j+1][0] + dp[k][j][2]*(lj-1LL) + MOD)%MOD;
            dp[k+1][j+1][0] = (dp[k+1][j+1][0] + dp[k][j][3]*(lj-1LL) + MOD)%MOD;
            dp[k+1][j+1][0] = (dp[k+1][j+1][0] + dp[k][j][4]*(lj-1LL) + MOD)%MOD;
            
            //end
            dp[k+1][j+1][1] = (dp[k+1][j+1][1] + dp[k][j][0] * 2LL + MOD)%MOD;
            dp[k+1][j+1][1] = (dp[k+1][j+1][1] + dp[k][j][1] * 2LL + MOD)%MOD;
            dp[k+1][j+1][1] = (dp[k+1][j+1][1] + dp[k][j][2] * 2LL + MOD)%MOD;
            dp[k+1][j+1][1] = (dp[k+1][j+1][1] + dp[k][j][3] * 2LL + MOD)%MOD;
            dp[k+1][j+1][1] = (dp[k+1][j+1][1] + dp[k][j][4] * 2LL + MOD)%MOD;
         }
         
         //add number to end of component (middle)
         if(j >= 2){
            dp[k+1][j][2] = (dp[k+1][j][2] + dp[k][j][0] * (2*lj - 4LL) + MOD)%MOD;
            dp[k+1][j][2] = (dp[k+1][j][2] + dp[k][j][1] * (2*lj - 3LL) + MOD)%MOD;
            dp[k+1][j][2] = (dp[k+1][j][2] + dp[k][j][2] * (2*lj - 3LL) + MOD)%MOD;
            dp[k+1][j][2] = (dp[k+1][j][2] + dp[k][j][3] * (2*lj - 2LL) + MOD)%MOD;
            dp[k+1][j][2] = (dp[k+1][j][2] + dp[k][j][4] * (2*lj - 2LL) + MOD)%MOD;
         }
         
         //add number to end of component (end)
         if(k > 0){
            dp[k+1][j][3] = (dp[k+1][j][3] + dp[k][j][0] * 2LL + MOD)%MOD;
            dp[k+1][j][3] = (dp[k+1][j][3] + dp[k][j][1] + MOD)%MOD;
            dp[k+1][j][3] = (dp[k+1][j][3] + dp[k][j][2] * 2LL + MOD)%MOD;
            dp[k+1][j][3] = (dp[k+1][j][3] + dp[k][j][3] + MOD)%MOD;
            dp[k+1][j][3] = (dp[k+1][j][3] + dp[k][j][4] * 2LL + MOD)%MOD;
         }
         
         //join component
         if(j >= 2){
            dp[k+1][j-1][4] = (dp[k+1][j-1][4] + dp[k][j][0] * (lj-3LL) + MOD)%MOD;
            dp[k+1][j-1][4] = (dp[k+1][j-1][4] + dp[k][j][1] * (lj-2LL) + MOD)%MOD;
            dp[k+1][j-1][4] = (dp[k+1][j-1][4] + dp[k][j][2] * (lj-2LL) + MOD)%MOD;
            dp[k+1][j-1][4] = (dp[k+1][j-1][4] + dp[k][j][3] * (lj-1LL) + MOD)%MOD;
            dp[k+1][j-1][4] = (dp[k+1][j-1][4] + dp[k][j][4] * (lj-1LL) + MOD)%MOD;
         }
         
      }
   }
   
   long long answer = (dp[n-1][1][0] + dp[n-1][1][1] + dp[n-1][1][2] + dp[n-1][1][3] + dp[n-1][1][4] + MOD)%MOD;
   
   cout << answer << endl;
   
   return 0;
}