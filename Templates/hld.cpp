#include <bits/stdc++.h>

using namespace std;

const int N = 200005;

/*
Segtree code
*/


vector<vector<int>> adj;
//lca
const int D = 18;
int n = INT_MIN;
int jump[N][D];
int depth[N];

void initdfs(int v, int p){
   for(int nei : adj[v]){
      if(nei == p) continue;
      depth[nei] = depth[v]+1;
      jump[nei][0] = v;
      initdfs(nei,v);
   }
}

void initLCA() {
   for(int d = 1; d < D; d++) {
      for(int i = 1; i <= n; i++) {
         jump[i][d] = jump[jump[i][d-1]][d-1];
      }
   }
}

int getlca(int a, int b){
   if(depth[a] < depth[b]){
      swap(a,b);
   }
   
   for(int i = D-1; i >= 0; i--){
      if (((depth[a]-depth[b]) & (1<<i)) != 0){
         a = jump[a][i];
      }
   }
   if(a==b) return a;
   
   for(int i = D-1; i >= 0; i--){
      if(jump[a][i] != jump[b][i]){
         a = jump[a][i];
         b = jump[b][i];
      }
   }
   return jump[a][0];
}

//hld
int parent[N];
int sz[N];
void dfs_sz(int v, int p){
   parent[v] = p;
   //delete parent from adjacency matrix
   if(p != -1){
      adj[v].erase(find(adj[v].begin(),adj[v].end(),p));
   }
   sz[v] = 1;
   
   for(int& nei : adj[v]){
      dfs_sz(nei,v);
      sz[v] += sz[nei];
      if(sz[nei] > sz[adj[v][0]]){
         swap(nei,adj[v][0]);
      }
   }
}

int pos[N][2];       //in at pos[v][0], out at pos[v][1]
//subtree queries are [in[v], out[v]), path queries are [in[head[v]], in[v]]
int head[N];
int curpos;
void dfs_hld(int v){
   pos[v][0] = curpos++;
   
   for(int nei : adj[v]){
      head[nei] = (nei == adj[v][0] ? head[v] : nei); 
      dfs_hld(nei);
   }
   
   pos[v][1] = curpos;
}

Segtree segtree;
int vals[N];
//gets max from v to ancestor (lca)
int query_path2(int v, int lca){
   int curmax = INT_MIN;
   while(v != -1 && depth[v] >= depth[lca]){
      if(head[v] == v){
         curmax = max(curmax,vals[v]);
      } else if(depth[head[v]] >= depth[lca]){
         curmax = max(curmax,segtree.query(0,0,curpos-1,pos[head[v]][0],pos[v][0]));
      } else {
         curmax = max(curmax,segtree.query(0,0,curpos-1,pos[lca][0],pos[v][0]));
      }
      v = parent[head[v]];
   }
   return curmax;
}

//get max on path
int query_path(int a, int b){
   int lca = getlca(a,b);
   //cout << lca << endl;
   //COUNTS LCA TWICE
   return max(query_path2(a,lca),query_path2(b,lca));
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   /*
   input
   */
   //REMINDER: USE GLOBAL N
   
   //lca
   depth[1] = 0;
   initdfs(1,-1);
   initLCA();
   
   //hld
   dfs_sz(1,-1);
   
   head[1] = 1;
   curpos = 0;
   dfs_hld(1);
   
   /*
   build segtree, etc.
   */
   
   return 0;
}