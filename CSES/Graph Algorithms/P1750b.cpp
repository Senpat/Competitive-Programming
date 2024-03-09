#include <bits/stdc++.h>
//hopefully doesn't tle
using namespace std;

vector<int> to;

//stores -1 if it is not a part of a cycle, otherwise stores the length of the cycle that it's in
vector<int> cycle;
vector<bool> seen;
vector<int> depth;

int curcyclelen;
int stop;
int root;

void dfs(int v){
   seen[v] = true;
   
   //seen on a previous dfs
   if(depth[to[v]] != -1 && !seen[to[v]]){
      seen[v] = false;
      return;
   }
   
   
   if(!seen[to[v]]){
      depth[to[v]] = depth[v]+1;
      dfs(to[v]);
      
      if(stop != -1 && depth[v] >= stop){
         cycle[v] = curcyclelen;
      }
   } else {
      //cycle detected
      curcyclelen = depth[v] - depth[to[v]] + 1;
      stop = depth[to[v]];
      
      cycle[v] = curcyclelen;
   }
   
   seen[v] = false;
}

vector<int> disttocycle;
vector<int> firstcycle;       //first node in cycle that it reaches

void dfs2(int v){
   if(firstcycle[v] != -1) return;
   
   if(cycle[v] != -1){
      disttocycle[v] = 0;
      firstcycle[v] = v;
   } else {
      dfs2(to[v]);
      disttocycle[v] = disttocycle[to[v]]+1;
      firstcycle[v] = firstcycle[to[v]];
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   to = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> to[k];
   }
   
   int D = 17;
   vector<vector<int>> jump(n+1,vector<int>(D+1,-1));
   
   for(int k = 1; k <= n; k++){
      jump[k][0] = to[k];
   }
   
   for(int d = 1; d <= D; d++){
      for(int k = 1; k <= n; k++){
         jump[k][d] = jump[jump[k][d-1]][d-1];  
      }
   }
   
   cycle = vector<int>(n+1,-1);
   seen = vector<bool>(n+1,false);
   depth = vector<int>(n+1,-1);
   
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         curcyclelen = -1;
         stop = -1;
         depth[k] = 0;
         root = k;
         dfs(k);  
      }
   }
   
   disttocycle = vector<int>(n+1,-1);
   firstcycle = vector<int>(n+1,-1);
   
   for(int k = 1; k <= n; k++){
      if(firstcycle[k] == -1){
         dfs2(k);
      }
   }
   
   /*
   for(int k = 1; k <= n; k++){
      cout << cycle[k] << " ";
   }
   cout << endl;
   */
   for(int t = 0; t < q; t++){
      int v,x;
      cin >> v >> x;
      
      //cout << disttocycle[v] << " " << firstcycle[v] << endl;
      
      if(disttocycle[v] < x){
         x = (x-disttocycle[v])%cycle[firstcycle[v]];
         v = firstcycle[v];
      }
      
      
      
      int i = 0;
      for(int d = D; d >= 0; d--){
         if(i + (1 << d) <= x){
            v = jump[v][d];
            i += (1 << d);
         }
      }
      
      cout << v << endl;
   }
   
   
   return 0;
}