#include <bits/stdc++.h>
//semi-t
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   int n,m;
   cin >> n >> m;
   
   //vector<vector<long long>> adj(n,vector<long long>(n,LLONG_MAX));
   vector<vector<long long>> dist(n,vector<long long>(n,LLONG_MAX));
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w, a--, b--;
      //adj[a][b] = w;
      dist[a][b] = w;
   }
   
   //floyd warshall (works with negative edge weights when there are no negative edge cycles)
   for(int k = 0; k < n; k++){
      for(int j = 0; j < n; j++){
         for(int h = 0; h < n; h++){
            if(k==j || k==h || j==h) continue;
            if(dist[j][k] == LLONG_MAX || dist[k][h] == LLONG_MAX) continue;
            dist[j][h] = min(dist[j][h], dist[j][k] + dist[k][h]);     
         }
      }
   }
   
   //dp to find min cycle
   int pn = (1 << n);
   //dp[mask][x] -> min dist to reach node x using points in mask
   vector<vector<long long>> dp(pn,vector<long long>(n,LLONG_MAX));
   
   for(int k = 0; k < n; k++){
      dp[1 << k][k] = 0LL;
   }
   
   for(int mask = 0; mask < pn; mask++){
      for(int k = 0; k < n; k++){
         if(dp[mask][k] == LLONG_MAX) continue;
         for(int nei = 0; nei < n; nei++){
            if(((mask >> nei)&1) == 1 || dist[k][nei] == LLONG_MAX) continue;
            dp[mask ^ (1 << nei)][nei] = min(dp[mask ^ (1 << nei)][nei], dp[mask][k] + dist[k][nei]);
         }
      }
   }
   
   long long answer = LLONG_MAX;
   for(int k = 0; k < n; k++){
      answer = min(answer,dp[pn-1][k]);
   }
   
   if(answer == LLONG_MAX){
      cout << "No" << endl;
   } else {
      cout << answer << endl;
   }
   
   return 0;
}