#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;

vector<int> parent;

int found1;
int found2;

vector<bool> onpath;

void dfs(int v, int p){
   parent[v] = p;
   if(found1 != -1) return;
   
   onpath[v] = true;
   for(int nei : adj[v]){
      if(onpath[nei]){
         //backedge found
         found1 = nei;
         found2 = v;
         return;
      } if(parent[nei] != -1){
         //crossedge or seen edge
         continue;
      } else {
         dfs(nei,v);
      }
      if(found1 != -1) return;
   }
   onpath[v] = false;
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
   }
   
   parent = vector<int>(n+1,-1);
   found1 = -1;
   found2 = -1;
   onpath = vector<bool>(n+1,false);
   
   for(int v = 1; v <= n; v++){
      if(parent[v] != -1) continue;
      dfs(v,-1);
      if(found1 != -1) break;
   }
   
   if(found1 == -1){
      cout << "IMPOSSIBLE\n";
   } else {
      vector<int> path;
      path.push_back(found1);
      int i = found2;
      while(i != found1){
         path.push_back(i);
         i = parent[i];
      }
      path.push_back(found1);
      
      cout << path.size() << endl;
      for(int k = path.size()-1; k >= 0; k--){
         cout << path[k] << " ";
      }
   }
   
   return 0;
}