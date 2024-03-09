#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<vector<int>> radj;

vector<bool> seen;
vector<int> order;
vector<int> comp;
int curcomp;

void dfs(int v){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(!seen[nei]){
         dfs(nei);
      }
   }
   //cout << v << endl;
   order.push_back(v);   
}

void dfs2(int v){
   comp[v] = curcomp;
   
   for(int nei : radj[v]){
      if(comp[nei] == -1){
         dfs2(nei);
      }
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   radj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      radj[b].push_back(a);
   }
   
   order = vector<int>();
   seen = vector<bool>(n+1,false);
   
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         dfs(k);
      }
   }
   
   comp = vector<int>(n+1,-1);
   curcomp = 0;
   for(int o = order.size()-1; o >= 0; o--){
      if(comp[order[o]] == -1){
         dfs2(order[o]);
         curcomp++;
      }
   }
   
   if(curcomp == 1){
      cout << "YES\n";
   } else {
      // a node in comp 1 can't go to comp 0
      int comp0 = -1;
      int comp1 = -1;
      for(int k = 1; k <= n; k++){
         if(comp[k] == 0) comp0 = k;
         if(comp[k] == 1) comp1 = k;
      }
      
      cout << "NO\n";
      cout << comp1 << " " << comp0 << endl;
   }
   
   return 0;
}