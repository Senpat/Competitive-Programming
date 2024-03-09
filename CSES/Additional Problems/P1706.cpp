#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<bool> seen;

int cursize;
void dfs(int v){
   seen[v] = true;
   cursize++;
   
   for(int nei : adj[v]){
      if(!seen[nei]){
         dfs(nei);
      }
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   seen = vector<bool>(n+1,false);
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   vector<int> compsizes;
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         cursize = 0;
         dfs(k);
         compsizes.push_back(cursize);
      }
   }
   
   bitset<100005> bset;
   bset.set(0);
   for(int i : compsizes){
      bset |= (bset << i);
   }
   
   for(int k = 1; k <= n; k++){
      if(bset[k]) cout << "1";
      else cout << "0";
   }
   
   return 0;
}