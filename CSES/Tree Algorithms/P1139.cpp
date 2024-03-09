#include <bits/stdc++.h>
 
using namespace std;
 
#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
 
vector<vector<int>> adj;
vector<int> c;
 
vector<int> answer;
 
set<int> dfs(int v, int p){
   
   int maxi = 0;
   set<int> cur;
   cur.insert(c[v]);
   
   for(int nei : adj[v]){
      if(nei == p) continue; 
      auto nset = dfs(nei,v);
      
      if(cur.size() < nset.size()) swap(cur,nset);
      for(int i : nset) cur.insert(i);
   }
   
   answer[v] = cur.size();
   return cur;
}
 
 
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   c = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> c[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   answer = vector<int>(n+1,0);
   
   dfs(1,-1);
   
   for(int k = 1; k <= n; k++){
      cout << answer[k] << " ";
   }
   
   return 0;
}