#include <bits/stdc++.h>
//bug: if(v == p) and processing children values before recursing so they weren't set
using namespace std;

vector<vector<int>> adj;

vector<int> color;

int nodetime;
vector<int> in;
vector<int> out;
vector<int> order;

void dfs1(int v, int p){
   in[v] = nodetime;
   order.push_back(v);
   
   nodetime++;
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs1(nei,v);
   }
   
   out[v] = nodetime-1;
}

vector<set<int>> sets;

long long answer;

//range update, range max query
class Segtree{
   private:
   vector<long long> op;
   vector<long long> a;
   
   public:
   Segtree(int size){
      op = vector<long long>(4*size,0LL);
      a = vector<long long>(4*size,0LL);
   }
   
   void propagate(int v){
      op[2*v+1] += op[v];
      a[2*v+1] += op[v];
      op[2*v+2] += op[v];
      a[2*v+2] += op[v];
      
      op[v] = 0;
   }
   
   void update(int v, int l, int r, int ql, int qr, long long x){
      if(l >= ql && r <= qr){
         op[v] += x;
         a[v] += x;
      } else if(qr < l || ql > r){
         return;
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         update(2*v+1,l,mid,ql,qr,x);
         update(2*v+2,mid+1,r,ql,qr,x);
         
         a[v] = max(a[2*v+1],a[2*v+2]);
      }
   }
   
   long long query(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         return a[v];
      } else if(l > qr || r < ql){
         return 0LL;                //can't be lower than 0
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         return max(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
      }
   }
};

Segtree segtree(0);

void dfs2(int v, int p){
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs2(nei,v);
   }
   
   //update segtree with a[v]
   segtree.update(0,0,nodetime-1,in[v],out[v],1LL);
   
   //remove previous occurances
   while(true){
      auto it = sets[color[v]].lower_bound(in[v]);
      if(it == sets[color[v]].end() || *it > out[v]) break;
      int node = order[*it];
      segtree.update(0,0,nodetime-1,in[node],out[node],-1LL);
      sets[color[v]].erase(it);
   }
   
   sets[color[v]].insert(in[v]);
   
   //get top two max (minimum path num is 1, for the case where one end of the path is v)
   long long max1 = 1LL;
   long long max2 = 1LL;
   for(int nei : adj[v]){
      if(nei == p) continue;
      
      long long val = segtree.query(0,0,nodetime-1,in[nei],out[nei]);
      if(val > max1){
         max2 = max1;
         max1 = val;
      } else if(val > max2){
         max2 = val;
      }
   }
   
   answer = max(answer,max1*max2);
   
   
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      adj = vector<vector<int>>(n+1,vector<int>());
      
      for(int k = 2; k <= n; k++){
         int i;
         cin >> i;
         adj[k].push_back(i);
         adj[i].push_back(k);
      }
      
      color = vector<int>(n+1);
      for(int k = 1; k <= n; k++){
         cin >> color[k];
      }
      
      nodetime = 0;
      in = vector<int>(n+1);
      out = vector<int>(n+1);
      order = vector<int>();
      dfs1(1,-1);
      
      sets = vector<set<int>>(n+1,set<int>());
      
      answer = 0LL;
      
      segtree = Segtree(nodetime);
      
      dfs2(1,-1);
      
      cout << answer << "\n";
      
   
   }
   
   return 0;
}