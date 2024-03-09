#include <bits/stdc++.h>

using namespace std;

int n,m;
vector<vector<int>> adj;
vector<int> dp;
vector<int> nxt;

void dfs(int v){
   if(dp[v] != -1) return;
   
   int maxpath = 0;
   int nextv = -1;
   for(int nei : adj[v]){
      dfs(nei);
      
      if(dp[nei] == 0 && nei != n) continue;
      
      if(dp[nei]+1 > maxpath){
         maxpath = dp[nei]+1;
         nextv = nei;
      }
   }
   
   dp[v] = maxpath;
   nxt[v] = nextv;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
   }
   
   dp = vector<int>(n+1,-1);
   nxt = vector<int>(n+1,-1);
   
   dfs(1);
   
   if(dp[1] == 0){
      cout << "IMPOSSIBLE\n";
   } else {
      vector<int> path;
      int i = 1;
      do{
         path.push_back(i);
         i = nxt[i];
      } while(i != -1);
      
      cout << path.size() << endl;
      for(int i : path){
         cout << i << " ";
      }
   }
   
   return 0;
}