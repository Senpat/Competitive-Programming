#include <bits/stdc++.h>

using namespace std;

vector<long long> coins;

vector<vector<int>> adj;
vector<vector<int>> radj;

vector<bool> seen;
vector<int> order;

void dfs(int v){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(!seen[nei]){
         dfs(nei);
      }
   }
   
   order.push_back(v);
}

int compnum;
vector<int> comp;
vector<long long> compcoins;

void dfs2(int v){
   comp[v] = compnum;
   compcoins[compnum] += coins[v];
   
   for(int nei : radj[v]){
      if(comp[nei] == -1){
         dfs2(nei);
      }
   }
}

vector<unordered_set<int>> compadj;
vector<long long> dp;

void dfs3(int v){
   dp[v] = compcoins[v];
   long long maxnei = 0LL;
   for(int nei : compadj[v]){
      if(dp[nei] == -1LL){
         dfs3(nei);
      }
      maxnei = max(maxnei,dp[nei]);
   }
   dp[v] += maxnei;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   coins = vector<long long>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> coins[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   radj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      radj[b].push_back(a);
   }
   
   seen = vector<bool>(n+1,false);
   order = vector<int>();
   
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         dfs(k);
      }
   }
   
   compnum = 0;
   comp = vector<int>(n+1,-1);
   compcoins = vector<long long>();
   for(int o = order.size()-1; o >= 0; o--){
      int v = order[o];
      if(comp[v] != -1) continue;
      compcoins.push_back(0LL);
      
      dfs2(v);
      
      compnum++;
   }
   
   compadj = vector<unordered_set<int>>(compnum,unordered_set<int>());
   
   for(int v = 1; v <= n; v++){
      for(int nei : adj[v]){
         if(comp[v] != comp[nei]){
            compadj[comp[v]].insert(comp[nei]);
         }
      }
   }
   
   dp = vector<long long>(compnum,-1LL);
   
   for(int v = 0; v < compnum; v++){
      if(dp[v] == -1LL){
         dfs3(v);
      }
   }
   
   long long answer = *max_element(dp.begin(),dp.end());
   cout << answer << endl;
   
   return 0;
}