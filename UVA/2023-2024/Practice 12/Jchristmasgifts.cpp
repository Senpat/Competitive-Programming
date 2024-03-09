#include <bits/stdc++.h>

using namespace std;

vector<unordered_set<int>> adj;

vector<bool> seen;

vector<int> path;

void euler(int v){
   seen[v] = true;
   
   while(adj[v].size() > 0){
      auto it = adj[v].begin();
      int nei = *it;
      adj[nei].erase(v);
      adj[v].erase(it);
      
      euler(nei);
   }
   
   path.push_back(v);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<unordered_set<int>>(n+2,unordered_set<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].insert(b);
      adj[b].insert(a);
   }
   
   //n+1th node connects to nodes with odd degree
   int odd = 0;
   for(int k = 1; k <= n; k++){
      if(adj[k].size()%2 == 1){
         adj[k].insert(n+1);
         adj[n+1].insert(k);
         odd++;
      }
   }
   
   seen = vector<bool>(n+2,false);
   
   cout << odd << endl;
   for(int k = 1; k <= n+1; k++){
      if(seen[k]) continue;
      path = vector<int>();
      euler(k);
      
      for(int j = 1; j < path.size(); j++){
         if(path[j] != n+1 && path[j-1] != n+1){
            cout << path[j-1] << " " << path[j] << endl;
         }
      }
   }
   
   return 0;
}