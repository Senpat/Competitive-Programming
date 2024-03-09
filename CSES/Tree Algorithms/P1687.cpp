#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<int> depth;

void dfs(int v, int p){
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      depth[nei] = depth[v]+1;
      dfs(nei,v);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   vector<int> parent(n+1);
   parent[1] = 1;
   
   for(int k = 2; k <= n; k++){
      int i;
      cin >> i;
      parent[k] = i;
      adj[k].push_back(i);
      adj[i].push_back(k);   
   }
   
   depth = vector<int>(n+1);
   depth[1] = 0;
   dfs(1,-1);
   
   int D = 19;
   vector<vector<int>> jump(D,vector<int>(n+1));
   for(int k = 1; k <= n; k++){
      jump[0][k] = parent[k];
   }
   
   for(int d = 1; d < D; d++){
      for(int k = 1; k <= n; k++){
         jump[d][k] = jump[d-1][jump[d-1][k]];
      }
   }
   
   for(int t = 0; t < q; t++){
      int v,d;
      cin >> v >> d;
      if(d > depth[v]){
         cout << "-1\n";
         continue;
      }
      
      int i = 0;
      for(int p = D-1; p >= 0; p--){
         if(i + (1 << p) <= d){
            v = jump[p][v];
            i += (1 << p);
         }
      }
      
      cout << v << endl;
   
   }
   
   
   return 0;
}