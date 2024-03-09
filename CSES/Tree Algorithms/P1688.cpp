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
   
   int D = 19;
   vector<vector<int>> jump = vector<vector<int>>(D,vector<int>(n+1));
   jump[0][1] = 1;
   
   for(int k = 2; k <= n; k++){
      int p;
      cin >> p;
      
      adj[k].push_back(p);
      adj[p].push_back(k);
      
      jump[0][k] = p;
   }
   
   depth = vector<int>(n+1);
   depth[1] = 0;
   
   dfs(1,-1);
   
   for(int d = 1; d < D; d++){
      for(int k = 1; k <= n; k++){
         jump[d][k] = jump[d-1][jump[d-1][k]];
      }
   }
   
   for(int t = 0; t < q; t++){
      int a,b;
      cin >> a >> b;
      
      if(depth[a] < depth[b]){
         swap(a,b);
      }
      
      for(int p = D-1; p >= 0; p--){
         if(depth[jump[p][a]] > depth[b]) a = jump[p][a];
      }
      
      if(depth[a] > depth[b]) a = jump[0][a];
      
      //a and b have the same depth
      if(a == b){
         cout << a << "\n";
         continue;
      }
      
      //cout << a << " " << b << endl;
      for(int p = D-1; p >= 0; p--){
         if(jump[p][a] != jump[p][b]){
            a = jump[p][a];
            b = jump[p][b];
            //cout << p << " " << a << " " << b << endl;
         }
      }
      
      //a and b are 1 under the lca
      cout << jump[0][a] << "\n";
   }
      
      
   
   
   return 0;
}