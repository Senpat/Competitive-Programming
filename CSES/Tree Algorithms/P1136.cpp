#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<int> depth;
int D = 19;
vector<vector<int>> jump;
vector<vector<int>> euler;
int euleri;

void dfs(int v, int p){
   euler[v][0] = euleri++;
   
   for(int nei : adj[v]){
      if(nei == p) 
         continue;
      depth[nei] = depth[v]+1;
      jump[0][nei] = v;
      dfs(nei,v);
   }
   
   euler[v][1] = euleri++;
}

int lca(int a, int b){
         
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

//fenwick tree
vector<int> bits;

void update(int i, int x){
   for(; i < euleri; i += i&-i){
      bits[i] += x;
   }  
}

int psum(int i){
   int cursum = 0;
   for(; i > 0; i -= i&-i){
      cursum += bits[i];
   }
   return cursum;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   
   for(int k = 2; k <= n; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
      
   }
   
   depth = vector<int>(n+1);
   depth[1] = 0;
   jump = vector<vector<int>>(D,vector<int>(n+1));
   jump[0][1] = 1;
   euler = vector<vector<int>>(n+1,vector<int>(2));
   euleri = 1;
   
   dfs(1,-1);
   
   for(int d = 1; d < D; d++){
      for(int k = 1; k <= n; k++){
         jump[d][k] = jump[d-1][jump[d-1][k]];
      }
   }
   
   bits = vector<int>(euleri,0);
   
   for(int t = 0; t < q; t++){
      int a,b;
      cin >> a >> b;
      
      int curlca = lca(a,b);
      
      update(euler[a][0],1);
      update(euler[b][0],1);
      update(euler[curlca][0],-1);
      if(curlca != 1){
         update(euler[jump[0][curlca]][0],-1);
      }
   }
   
   for(int k = 1; k <= n; k++){
      int answer = psum(euler[k][1]) - psum(euler[k][0]-1);
      cout << answer << " ";
   }
      
      
   
   
   return 0;
}