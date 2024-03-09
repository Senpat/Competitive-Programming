#include <bits/stdc++.h>

using namespace std;

vector<unordered_set<int>> adj;

vector<int> path;

void euler(int v){

   while(adj[v].size() > 0){
      int nei = *adj[v].begin();
      adj[v].erase(adj[v].begin());
      adj[nei].erase(v);
      
      euler(nei);
   }
   
   path.push_back(v);
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<unordered_set<int>>(n+1,unordered_set<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].insert(b);
      adj[b].insert(a);
   }
   
   bool fail = false;
   for(int k = 1; k <= n; k++){
      if(adj[k].size() % 2 != 0){
         fail = true;
         break;
      }
      
   }
   
   if(fail){
      cout << "IMPOSSIBLE" << endl;
      return 0;
   }
   
   
   path = vector<int>();
   euler(1);
   
   if(path.size() != m+1){
      cout << "IMPOSSIBLE" << endl;
      return 0;
   }
   
   
   for(int i : path){
      cout << i << " ";
   }
   
   return 0;
}