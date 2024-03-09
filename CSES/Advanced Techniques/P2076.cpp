#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   int i;
};

vector<vector<Edge>> adj;
vector<bool> seen;
vector<bool> isbackedge;
vector<int> backto;
vector<int> backfrom;

vector<int> paredge;

void dfs(int v, int p){
   seen[v] = true;
   
   for(Edge e : adj[v]){
      if(e.to == p || isbackedge[e.i]) continue;
      if(seen[e.to]){
         isbackedge[e.i] = true;
         backfrom[v]++;
         backto[e.to]++;
      } else {
         paredge[e.to] = e.i;
         dfs(e.to,v);
      }
   }
}

int num0 = 0;
vector<int> over;

void dfs2(int v, int p){
   over[v] = backfrom[v] - backto[v];
   
   for(Edge e : adj[v]){
      if(e.to == p || isbackedge[e.i]) continue;
      dfs2(e.to,v);
      
      over[v] += over[e.to];
   }
   
   if(v > 1 && over[v] == 0) num0++;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<Edge>>(n+1,vector<Edge>());
   vector<pair<int,int>> edges;
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back({b,k});
      adj[b].push_back({a,k});
      
      edges.push_back({a,b});
   }
   
   seen = vector<bool>(n+1,false);
   isbackedge = vector<bool>(m,false);
   backto = vector<int>(n+1,0);
   backfrom = vector<int>(n+1,0);
   paredge = vector<int>(n+1,-1);
   
   dfs(1,-1);
   
   over = vector<int>(n+1,0);
   dfs2(1,-1);
   
   cout << num0 << endl;
   for(int k = 2; k <= n; k++){
      //cout << over[k] << " " << paredge[k] << endl;
      if(over[k] == 0){
         cout << edges[paredge[k]].first << " " << edges[paredge[k]].second << endl;
      }
   }
   
   
   return 0;
}