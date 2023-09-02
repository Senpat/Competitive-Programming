#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<bool> seen;

void dfs(int v){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(!seen[nei]){
         dfs(nei);
      }
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   seen = vector<bool>(n+1,false);
   vector<int> comps;
   
   for(int k = 1; k <= n; k++){
      if(seen[k]) continue;
      comps.push_back(k);
      dfs(k);
   }
   
   cout << comps.size()-1 << endl;
   
   for(int k = 1; k < comps.size(); k++){
      cout << comps[0] << " " << comps[k] << endl;
   }
   
   return 0;
}