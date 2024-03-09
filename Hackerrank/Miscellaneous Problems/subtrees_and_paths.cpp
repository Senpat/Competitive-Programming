#include <bits/stdc++.h>

using namespace std;

const int N = 100005;

//range add update, range max query
class Segtree{
   private:
   int a[4*N];
   int op[4*N];
   
   public:
   Segtree(){
      for(int k = 0; k < 4*N; k++){
         a[k] = 0;
         op[k] = 0;
      }
   }
   
   void propagate(int v){
      op[2*v+1] += op[v];
      a[2*v+1] += op[v];
      op[2*v+2] += op[v];
      a[2*v+2] += op[v];
      op[v] = 0;
   }
   
   void update(int v, int l, int r, int ql, int qr, int x){
      if(l >= ql && r <= qr){
         op[v] += x;
         a[v] += x;
      } else if(r < ql || l > qr){
         return;
      } else {
         propagate(v);
         
         int mid = (l+r)/2;
         
         update(2*v+1,l,mid,ql,qr,x);
         update(2*v+2,mid+1,r,ql,qr,x);
         
         a[v] = max(a[2*v+1],a[2*v+2]);
      }
   }
   
   //max
   int query(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         return a[v];
      } else if (r < ql || l > qr){
         return INT_MIN;
      } else{
         propagate(v);
         
         int mid = (l+r)/2;
         
         return max(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
      }
   }
};


vector<vector<int>> adj;
//lca
const int D = 18;
int n;
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
//gets max from v to ancestor (lca)
int query_path2(int v, int lca){
   int curmax = INT_MIN;
   while(v != -1 && depth[v] >= depth[lca]){
      if(depth[head[v]] >= depth[lca]){
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
   //cout << a << " " << b << " " << lca << endl;
   //cout << lca << endl;
   //COUNTS LCA TWICE
   return max(query_path2(a,lca),query_path2(b,lca));
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   //ifstream fin("subtrees_and_paths_test.txt");
   
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   //lca
   depth[1] = 0;
   jump[1][0] = 1;
   initdfs(1,-1);
   initLCA();
   
   //hld
   dfs_sz(1,-1);
   
   head[1] = 1;
   curpos = 0;
   dfs_hld(1);
   
   int q;
   cin >> q;
   
   for(int t = 0; t < q; t++){
      string qt;
      int x1,x2;
      cin >> qt >> x1 >> x2;
      
      if(qt == "add"){
         segtree.update(0,0,curpos-1,pos[x1][0],pos[x1][1]-1,x2);
      } else {
         cout << query_path(x1,x2) << "\n";
      }
   }
   
   //cout << pos[3][0] << " " << pos[3][1] << endl;
   //cout << pos[59][0] << " " << pos[59][1] << endl;
   //cout << getlca(12,3) << endl;
   
   return 0;
}