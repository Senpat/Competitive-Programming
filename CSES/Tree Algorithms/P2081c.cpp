#include <bits/stdc++.h>
//usaco guide sol attempt (centroid decomp with bit)
using namespace std;

const int MAXN = 200005;
int m1,m2;

vector<vector<int>> adj;
vector<bool> marked;

long long answer;

int subsize[MAXN];

void dfs_size(int v, int p){
   subsize[v] = 1;
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs_size(nei,v);
      subsize[v] += subsize[nei];
   }
}

int dfs_centroid(int v, int p, int sz){
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      if(subsize[nei] > sz/2){
         return dfs_centroid(nei,v,sz);
      }
   }
   return v;
}

int dfs_depth(int v, int p){
   int d = 1;
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      d = max(d,dfs_depth(nei,v)+1);
   }
   return d;
}

long long bit[MAXN];

void update(int i, long long x, int sz){
   i++;     //1-indexed
   for(; i <= sz+1; i += i&-i){
      bit[i] += x;
   }
}

long long psum(int i){
   i++;     //1-indexed
   long long cursum = 0LL;
   for(; i > 0; i -= i&-i){
      cursum += bit[i];
   }
   return cursum;
}

void dfs(int v, int p, int d, int sz, bool merge){
   
   if(!merge){
      //m2 -> anywhere <= m2-d
      answer += psum(min(sz,m2-d));
      //m1-1 -> anywhere <= m1-1-d
      answer -= psum(min(sz,m1-1-d));
   } else {
      update(d,1LL,sz);
   }
   
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs(nei,v,d+1,sz,merge);
   }
}

void solve(int v){
   dfs_size(v,-1);
   
   int sz = subsize[v];
   int centroid = dfs_centroid(v,-1,sz);
   
   update(0,1LL,sz);
   //dumb optimization: don't need to update the last child
   int last = -1;
   for(int nei : adj[centroid]){
      if(!marked[nei]) last = nei;
   }
   
   for(int nei : adj[centroid]){
      if(marked[nei]) continue;
      dfs(nei,centroid,1,sz,false);
      if(nei != last){
         dfs(nei,centroid,1,sz,true);
      }
   }
   
   fill(bit,bit+sz+2,0LL);
   marked[centroid] = true;
   
   for(int nei : adj[centroid]){
      if(marked[nei]) continue;
      solve(nei);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n >> m1 >> m2;
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   answer = 0LL;
   marked = vector<bool>(n+1,false);
   solve(1);
   cout << answer << endl;
   
   return 0;
}