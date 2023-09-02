//WRONG - shortest cycle is not necessarily a backedge.
#include <bits/stdc++.h>

using namespace std;

//pair of to, i
vector<vector<pair<int,int>>> adj;
//pair of a, b
vector<pair<int,int>> edges;

vector<bool> seen;
vector<bool> isbackedge;
vector<int> depth;

void dfs(int v, int p){
   seen[v] = true;
   for(auto [to,i] : adj[v]){
      if(to == p) continue;
      if(isbackedge[i]){
         continue;
      }
      if(seen[to]){
         //make back edge
         isbackedge[i] = true;
      } else {
         depth[to] = depth[v]+1;
         dfs(to,v);
      }
     
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<pair<int,int>>>(n+1,vector<pair<int,int>>());
   edges = vector<pair<int,int>>(m);
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(make_pair(b,k));
      adj[b].push_back(make_pair(a,k));
      edges[k] = make_pair(a,b);
   }
   
   seen = vector<bool>(n+1);
   isbackedge = vector<bool>(m);
   depth = vector<int>(n+1);
   
   for(int k = 1; k <= n; k++){
      if(seen[k]) continue;
      depth[k] = 1;
      dfs(k,-1);
   }
      
   int answer = INT_MAX;
   for(int k = 0; k < m; k++){
      if(isbackedge[k]){
         answer = min(answer,abs(depth[edges[k].first] - depth[edges[k].second])+1);
      }
   }
   
   if(answer == INT_MAX){
      cout << "-1\n";
   } else {
      cout << answer << endl;
   }
   
   
   return 0;
}