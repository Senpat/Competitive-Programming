#include <bits/stdc++.h>

using namespace std;

vector<unordered_set<int>> adj;
vector<int> path;

int extrai;

void euler(int v){
   
   while(adj[v].size() > 0){
      auto i = adj[v].begin();
      int nei = *i;
      adj[v].erase(i);
      
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
   vector<int> indegree(n+1,0);
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].insert(b);
      indegree[b]++;
   }
   
   bool fail = false;
   for(int k = 2; k <= n-1; k++){
      if(adj[k].size() != indegree[k]){
         fail = true;
         break;
      }
   }
   
   if(adj[1].size() != indegree[1]+1 || adj[n].size() != indegree[n]-1){
      fail = true;
   }
   
   if(fail){
      cout << "IMPOSSIBLE\n";
      return 0;
   }
   
   path = vector<int>();
   euler(1);
   
   if(path.size() != m+1){
      cout << "IMPOSSIBLE\n";
   } else {
      for(int p = path.size()-1; p >= 0; p--){
         cout << path[p] << " ";
      }
   }
   
   return 0;
}