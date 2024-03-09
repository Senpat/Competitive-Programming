#include <bits/stdc++.h>

using namespace std;

vector<int> adj;

vector<bool> seen;
vector<bool> path;

vector<int> cyclehead;

void dfs1(int v){
   seen[v] = true;
   path[v] = true;
   
   int nei = adj[v];
   if(path[nei]){
      cyclehead.push_back(nei);
   } else if(!seen[nei]){
      dfs1(nei);
   }
   
   path[v] = false;
}

int cyclesize;
vector<int> dist;

void dfs2(int v, int stop){
   cyclesize++;
   
   if(adj[v] == stop){
      dist[v] = cyclesize;
   } else {
      dfs2(adj[v],stop);
   }
   
   dist[v] = cyclesize;
}

void dfs3(int v){
   if(dist[adj[v]] == -1){
      dfs3(adj[v]);
   }
   
   dist[v] = dist[adj[v]]+1;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   adj = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> adj[k];
   }
   
   seen = vector<bool>(n+1,false);
   path = vector<bool>(n+1,false);
   
   cyclehead = vector<int>();
   
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         dfs1(k);
      }
   }
   
   dist = vector<int>(n+1,-1);
   for(int v : cyclehead){
      if(adj[v] == v){
         dist[v] = 1;
      } else {
         cyclesize = 0;
         dfs2(v,v);
      }
   }
   
   for(int k = 1; k <= n; k++){
      if(dist[k] == -1){
         dfs3(k);
      }
   }
      
   for(int k = 1; k <= n; k++){
      cout << dist[k] << " ";
   }
   
   
   
   return 0;
}