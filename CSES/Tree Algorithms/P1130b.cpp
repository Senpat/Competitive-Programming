#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;

vector<vector<int>> dp;

void dfs(int v, int p){
   
   int sum1 = 0;
   int mindiff = INT_MAX;
   for(int nei : adj[v]){
      if(nei == p) continue;
      
      dfs(nei,v);
      
      int curmax = max(dp[nei][0],dp[nei][1]);
      sum1 += curmax;
      mindiff = min(mindiff, curmax-dp[nei][1]);
      
   }
   
   if(mindiff != INT_MAX){
      dp[v][0] = sum1 - mindiff + 1;
   }
   dp[v][1] = sum1;
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   dp = vector<vector<int>>(n+1,vector<int>(2,0));       //dp[x][0] -> max # of edges using x, dp[x][1] -> max # of edges not using x
   
   dfs(1,-1);
   
   cout << max(dp[1][0],dp[1][1]) << endl;
   
   
   return 0;
}