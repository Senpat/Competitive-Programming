#include <bits/stdc++.h>
//sol using dfs tree, overcomplicated
using namespace std;

struct Edge{
   int to;
   int i;
};

vector<Edge> adj;
vector<pair<int,int>> edges;

vector<pair<int,int>> answer;

vector<bool> isbackedge;
vector<bool> seen;

void dfs(int v){
   
   for(auto e : adj[v]){
      if(isbackedge[e.i]) continue;
      if(seen[e.to]){
         //is back edge
         isbackedge[e.i] = true
      }
   }
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<Edge>());
   edges = vector<pair<int,int>>(m);
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back({b,k});
      adj[b].push_back({a,k});
      
      edges[k] = make_pair(a,b);
   }
   
   answer = vector<pair<int,int>>(m);
   
   isbackedge = vector<bool>(m,false);
   seen = vector<bool>(n+1,false);
   
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         seen[k] = true;
         dfs(k);
      }
   }
   
   
   
   return 0;
}