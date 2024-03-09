#include <bits/stdc++.h>
//stupid sol
using namespace std;

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;

vector<vector<int>> adj;
vector<int> c;

vector<int> answer;

vector<int> p;
vector<int> dsusize;

int parent(int v){
   if(p[v] == v) return v;
   p[v] = parent(v);
   return p[v];
}

void join(int u, int v){
   int pu = parent(u);
   int pv = parent(v);
   
   if(pu == pv) return;
   
   p[pu] = pv;
   dsusize[pv] += dsusize[pu];
}

void dfs(int v, int p){
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs(nei,v);
      
      join(c[nei],c[v]);
   }
   
   answer[v] = dsusize[parent(c[v])];
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<int> sorted;
   c = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> c[k];
      sorted.push_back(c[k]);
   }
   
   sort(sorted.begin(),sorted.end());
   gp_hash_table<int,int> compress;
   for(int k = 0; k < sorted.size(); k++){
      if(k > 0 && sorted[k] == sorted[k-1]) continue;
      compress[sorted[k]] = k;
   }
   
   for(int k = 1; k <= n; k++) c[k] = compress[c[k]];
   
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   answer = vector<int>(n+1,0);
   
   p = vector<int>(n);
   dsusize = vector<int>(n,1);
   for(int k = 0; k < n; k++){
      p[k] = k;
   }
   
   dfs(1,-1);
   
   for(int k = 1; k <= n; k++){
      cout << answer[k] << " ";
   }

   return 0;
}