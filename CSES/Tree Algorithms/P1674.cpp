#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<int> subsize;

void dfs(int v, int p){
   subsize[v] = 0;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs(nei,v);
      
      subsize[v] += (subsize[nei]+1);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 2; k <= n; k++){
      int p;
      cin >> p;
      adj[k].push_back(p);
      adj[p].push_back(k);
   }
   
   subsize = vector<int>(n+1,0);
   
   dfs(1,-1);
   
   for(int k = 1; k <= n; k++){
      cout << subsize[k] << " ";
   }
   
   return 0;
}