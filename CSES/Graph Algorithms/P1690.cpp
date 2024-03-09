#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> adj(n,vector<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b, a--,b--;
      adj[a].push_back(b);
   }
   
   int pn = (1 << n);
   //dp[mask][x] = # of ways to go from 1 to x using the nodes in mask
   vector<vector<long long>> dp(pn, vector<long long>(n,0LL));
   dp[1][0] = 1LL;
   
   for(int mask = 1; mask < pn; mask++){
      for(int k = 0; k < n; k++){
         if(dp[mask][k] == 0LL) continue;
         for(int nei : adj[k]){
            if((mask & (1 << nei)) == 0){
               int newmask = mask | (1 << nei);
               dp[newmask][nei] += dp[mask][k];
               if(dp[newmask][nei] >= MOD) dp[newmask][nei] -= MOD;
            }
         }
      }
   }
   
   long long answer = dp[pn-1][n-1];
   cout << answer << endl;
   
   return 0;
}