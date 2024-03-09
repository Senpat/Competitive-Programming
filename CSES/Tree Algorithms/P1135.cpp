#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<int> depth;

int jump[19][200005];
int D = 19;

void dfs(int v, int p){
   
   for(int nei : adj[v]){
      if(nei == p) 
         continue;
      depth[nei] = depth[v]+1;
      jump[0][nei] = v;
      dfs(nei,v);
   }
}

int getlca(int a, int b){
   if(depth[a] < depth[b]){
      swap(a,b);
   }
      
   for(int p = D-1; p >= 0; p--){
      if(depth[jump[p][a]] > depth[b]) a = jump[p][a];
   }
      
   if(depth[a] > depth[b]) a = jump[0][a];
      
   //a and b have the same depth
   if(a == b){
      return a;
   }
      
   for(int p = D-1; p >= 0; p--){
      if(jump[p][a] != jump[p][b]){
         a = jump[p][a];
         b = jump[p][b];
      }
   }
      
   return jump[0][a];
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   //jump = vector<vector<int>>(D,vector<int>(n+1));
   jump[0][1] = 1;
   
   for(int k = 2; k <= n; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
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
      
      int answer = depth[a] + depth[b] - 2*depth[getlca(a,b)];
      cout << answer << "\n";
   }
      
      
   
   
   return 0;
}