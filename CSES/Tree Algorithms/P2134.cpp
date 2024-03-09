#include <bits/stdc++.h>

using namespace std;

const int N = 200005;
//Segtree
//point assign, range max query
//to call, v = 0, l = 0, r = size-1
//l,r and ql,qr are inclusive
class Segtree{
   private:
   int a[4*N];
   
   public:
   Segtree(){}
   
   void build(int v, int l, int r, const vector<int>& array){
      if(l == r){
         a[v] = array[l];
      } else {
         int mid = (l+r)/2;
         //left
         build(2*v+1,l,mid,array);
         //right
         build(2*v+2,mid+1,r,array);
         
         a[v] = max(a[2*v+1],a[2*v+2]);
      }
   }
   
   void assign(int v, int l, int r, int i, int x){
      if(l == r){
         a[v] = x;
      } else {
         int mid = (l+r)/2;
         if(i <= mid){
            //go left
            assign(2*v+1,l,mid,i,x);
         } else {
            //go right
            assign(2*v+2,mid+1,r,i,x);
         }
         
         a[v] = max(a[2*v+1],a[2*v+2]);
      }
   }
   
   //max
   int query(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         return a[v];
      } else if(r < ql || l > qr){
         return 0;
      } else {
         int mid = (l+r)/2;
         //left
         int lmax = query(2*v+1,l,mid,ql,qr);
         //right
         int rmax = query(2*v+2,mid+1,r,ql,qr);
         return max(lmax,rmax);
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

int pos[N][2];
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
   int curmax = 0;
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
   
   int q;
   cin >> n >> q;
   
   for(int k = 1; k <= n; k++){
      cin >> vals[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   //lca
   depth[1] = 0;
   initdfs(1,-1);
   initLCA();
   
   //hld
   dfs_sz(1,-1);
   
   head[1] = 1;
   curpos = 0;
   dfs_hld(1);
   
   vector<int> build(curpos,0);
   for(int k = 1; k <= n; k++){
      build[pos[k][0]] = vals[k];
   }
   
   segtree.build(0,0,curpos-1,build);
   
   for(int t = 0; t < q; t++){
      int qt,x1,x2;
      cin >> qt >> x1 >> x2;
      
      if(qt == 1){
         segtree.assign(0,0,curpos-1,pos[x1][0],x2);
         vals[x1] = x2;
      } else {
         int answer = query_path(x1,x2);
         cout << answer << " ";
      }
   }
   
   return 0;
}