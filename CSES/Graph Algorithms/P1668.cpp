#include <bits/stdc++.h>

using namespace std;


vector<vector<int>> adj;
vector<int> color;
bool fail;

void dfs(int v){
   
   for(int nei : adj[v]){
      if(color[nei] == 0){
         color[nei] = 3-color[v];
         dfs(nei);
      } else {
         if(color[nei] != 3-color[v]){
            fail = true;
         }
      }
   }
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   color = vector<int>(n+1,0);
   
   fail = false;
   for(int k = 1; k <= n; k++){
      if(color[k] != 0) continue;
      color[k] = 1;
      
      dfs(k);
   }
   
   if(fail){
      cout << "IMPOSSIBLE\n";
   } else {
      for(int k = 1; k <= n; k++){
         cout << color[k] << " ";
      }
   }
   
   return 0;
}