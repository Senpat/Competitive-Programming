#include <bits/stdc++.h>
//O(2^N * N^2)
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,x;
   cin >> n >> x;
   int pn = (1 << n);
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   //dp[x][y] -> use x mask, y groups = minimum sum of last group
   vector<vector<int>> dp(pn,vector<int>(n+1,INT_MAX));
   
   dp[1][1] = array[0];
   
   for(int mask = 1; mask < pn; mask++){
      for(int k = 1; k < n; k++){
         if(dp[mask][k] == INT_MAX) continue;
         
         for(int j = 0; j < n; j++){
            if((mask & (1 << j)) != 0) continue;
            
            //make new group
            dp[mask | (1 << j)][k+1] = min(dp[mask | (1 << j)][k+1],array[j]);
            
            //add to group
            if(dp[mask][k] + array[j] <= x){
               dp[mask | (1 << j)][k] = min(dp[mask | (1 << j)][k],dp[mask][k] + array[j]);
            }
            
         }
      }
   }
   
   int groups = n;
   for(int k = 1; k <= n; k++){
      if(dp[pn-1][k] != INT_MAX){
         groups = k;
         break;
      }
   }
   
   cout << groups << endl;
        
   
   return 0;
}