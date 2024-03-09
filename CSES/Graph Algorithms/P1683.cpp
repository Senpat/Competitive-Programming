#include <bits/stdc++.h>
//find scc's

using namespace std;

vector<vector<int>> adj;
vector<vector<int>> radj;

vector<int> order;
vector<bool> seen;
void dfs1(int v){
   seen[v] = true;
   for(int nei : adj[v]){
      if(!seen[nei]){
         dfs1(nei);
      }
   }
   order.push_back(v);
}

int compnum;
vector<int> comp;

void dfs2(int v){
   comp[v] = compnum;
   
   for(int nei : radj[v]){
      if(comp[nei] == 0){
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
         dfs1(k);
      }
   }
   
   compnum = 0;
   comp = vector<int>(n+1);
   
   for(int o = order.size()-1; o >= 0; o--){
      if(comp[order[o]] == 0){
         compnum++;
         dfs2(order[o]);
      }
   }
   
   cout << compnum << endl;
   for(int k = 1; k <= n; k++){
      cout << comp[k] << " ";
   }
   
   
   return 0;
}