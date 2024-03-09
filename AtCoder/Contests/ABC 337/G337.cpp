#include <bits/stdc++.h>

using namespace std;

class Segtree{
   private:
   vector<vector<int>> a;
   
   public:
   Segtree(int size){
      a = vector<vector<int>>(4*size);
   }
   
   void build(int v, int l, int r, const vector<int>& array){
      a[v] = vector<int>(r-l+1);
      for(int k = 0; k < r-l+1; k++){
         a[v][k] = array[k+l];
      }
      sort(a[v].begin(),a[v].end());
      
      if(l < r){
         int mid = (l+r)/2;
         
         build(2*v+1,l,mid,array);
         build(2*v+2,mid+1,r,array);
      }
   }
   
   //get # of elements between ql and qr that are < w
   int query(int v, int l, int r, int ql, int qr, int w){
      if(ql <= l && qr >= r){
         //binary search
         int bl = 0;
         int br = r-l+1 -1;
         int ans = -1;
         while(bl <= br){
            int mid = bl + (br-bl)/2;
            
            if(a[v][mid] < w){
               ans = mid;
               bl = mid+1;
            } else {
               br = mid-1;
            }
         }
         
         return ans+1;
      } else if(qr < l || ql > r){
         return 0;
      } else {
         int mid = (l+r)/2;
         
         return query(2*v+1,l,mid,ql,qr,w) + query(2*v+2,mid+1,r,ql,qr,w);
      }
   }
};

int n;
vector<vector<int>> adj;

//flatten tree
int t;
vector<int> in;
vector<int> out;

void flatten(int v, int p){
   in[v] = t;
   t++;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      flatten(nei,v);
   }
   
   out[v] = t-1;
}

Segtree segtree(0);
vector<long long> answer;

long long dfs1(int v, int p){
   long long ret = 0LL;
   for(int nei : adj[v]){
      if(nei == p) continue;
      ret += dfs1(nei,v);
   }
   
   //add cases where v (aka u) is w
   ret += (long long)segtree.query(0,0,n-1,in[v],out[v],v);
   return ret;
}

void dfs2(int v, int p){
   //assume answer for v has already been calculated
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      
      answer[nei] = answer[v];
      answer[nei] += (long long)(nei-1 - segtree.query(0,0,n-1,in[nei],out[nei],nei));
      answer[nei] -= (long long)(segtree.query(0,0,n-1,in[nei],out[nei],v));
      
      dfs2(nei,v);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   t = 0;
   in = vector<int>(n+1);
   out = vector<int>(n+1);
   
   flatten(1,-1);
   
   vector<int> order = vector<int>(n);
   for(int k = 1; k <= n; k++){
      order[in[k]] = k;
   }
   
   segtree = Segtree(n);
   segtree.build(0,0,n-1,order);
   
   //do rerooting
   answer = vector<long long>(n+1);
   answer[1] = dfs1(1,-1);
   
   dfs2(1,-1);
   
   for(int k = 1; k <= n; k++){
      cout << answer[k] << " ";
   }
   
   return 0;
}