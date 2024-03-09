#include <bits/stdc++.h>

using namespace std;

long long nl;
vector<vector<int>> adj;

vector<long long> subsize;       //stores # of nodes in the subtree
vector<long long> dist;         //stores sum of distance to nodes

void dfs1(int v, int p){
   
   dist[v] = 0LL;
   subsize[v] = 1LL;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs1(nei,v);
      
      subsize[v] += subsize[nei];
      dist[v] += subsize[nei] + dist[nei];
   }
   
}

void dfs2(int v, int p){
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dist[nei] = dist[v] - subsize[nei] + (nl - subsize[nei]);
      dfs2(nei,v);
   }
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   nl = (long long)n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   subsize = vector<long long>(n+1,0LL);
   dist = vector<long long>(n+1,0LL);
   
   //accurates sets dist for node 1
   dfs1(1,-1);
   
   dfs2(1,-1);
   
   for(int k = 1; k <= n; k++){
      cout << dist[k] << " ";
   }
   
   return 0;
}