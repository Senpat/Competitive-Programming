#include <bits/stdc++.h>
//WRONG: too slow because stores starting point unnecessarily, and doesn't account for going back to nodes properly
//correct sol: uses floyd warshall + bit dp
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<long long>> adj(n,vector<long long>(n,LLONG_MAX));
   
   int pn = (1<<n);
   //dp[a][b][mask], minimum path to go from a to b using nodes in mask
   vector<vector<vector<long long>>> dp(n,vector<vector<long long>>(n,vector<long long>(pn,LLONG_MAX)));
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w, a--,b--;
      adj[a][b] = w;
      
      //dp[a][b][(1 << a) ^ (1 << b)] = w;
   }
   
   
   //O(n^3 * 2^n)
   for(int a = 0; a < n; a++){
      for(int mask = 0; mask < (1 << n); mask++){
         for(int c = 0; c < n; c++){
            for(int b = 0; b < n; b++){
            //check path going from a to b adding c
               if(dp[a][b][mask] == LLONG_MAX || adj[b][c] == LLONG_MAX) 
                  continue;
               if(((mask>>b)&1) == 1 && ((mask>>a)&1) == 1){
                  dp[a][c][mask | (1 << c)] = min(dp[a][c][mask | (1 << c)], dp[a][b][mask] + adj[b][c]);
               }
            }
         }
      }
   }
   
   long long answer = LLONG_MAX;
   for(int a = 0; a < n; a++){
      for(int b = 0; b < n; b++){
         answer = min(answer,dp[a][b][pn-1]);
      }
   }
   
   if(answer == LLONG_MAX){
      cout << "No" << endl;
   } 
   else {
      cout << answer << endl;
   }
     
   
   
   return 0;
}