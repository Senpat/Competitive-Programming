#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int from;
   int to;
   long long w;
};

vector<bool> root;
vector<vector<int>> adj;
vector<bool> seen;
void dfs(int v){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(root[nei]){
         root[nei] = false;
      } else if(!seen[nei]){
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
   vector<Edge> edges(m);
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      edges[k] = {a,b,w};
      adj[a].push_back(b);
   }
   
   seen = vector<bool>(n+1,false);
   root = vector<bool>(n+1,false);
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         dfs(k);
         root[k] = true;
      }
   }
   
   vector<long long> dist(n+1,LLONG_MAX);
   vector<int> parent(n+1,-1);
   for(int k = 1; k <= n; k++){
      if(!root[k]) continue;
      //cout << k << endl;
      dist[k] = 0LL;
      parent[k] = k;
   }
   
   bool stopped = false;
   int lastupdate = -1;
   for(int rep = 0; rep < n; rep++){
      bool found = false;
      for(Edge e : edges){
         if(dist[e.from] < LLONG_MAX){
            long long newd = dist[e.from] + e.w;
            if(newd < dist[e.to]){
               dist[e.to] = newd;
               parent[e.to] = e.from;
               found = true;
               lastupdate = e.to;
            }
         }
      }
      
      if(!found){
         stopped = true;
         break;
      }
   }
   
   if(stopped){
      cout << "NO\n";
   } else {
      //currently, last update is either in the negative cycle or inside the negative cycle
      //go back n times to guarantee that it is in the negative cycle
      
      for(int rep = 0; rep < n; rep++){
         lastupdate = parent[lastupdate];
      }
      
      vector<int> path;
      path.push_back(lastupdate);
      
      lastupdate = parent[lastupdate];
      while(lastupdate != path[0]){
         path.push_back(lastupdate);
         lastupdate = parent[lastupdate];
      }
      
      path.push_back(lastupdate);
      
      cout << "YES\n";
      for(int p = path.size()-1; p >= 0; p--){
         cout << path[p] << " ";
      }
   }
   
   
   
   return 0;
}