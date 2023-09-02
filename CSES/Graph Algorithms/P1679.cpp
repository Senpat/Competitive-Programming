#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;

//topological sort
stack<int> topsort;
unordered_set<int> cur;
vector<bool> seen;
bool fail;

void dfs(int v){
   seen[v] = true;
   cur.insert(v);
   for(int nei : adj[v]){
      if(cur.find(nei) != cur.end()){
         fail = true;
      }
      if(seen[nei]) continue;
      dfs(nei);
   }
   cur.erase(v);
   topsort.push(v);
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
   }
   
   seen = vector<bool>(n+1,false);
   topsort = stack<int>();
   fail = false;
   
   for(int k = 1; k <= n; k++){
      if(seen[k]) continue;
      cur = unordered_set<int>();
      dfs(k);
   }
   
   if(fail){
      cout << "IMPOSSIBLE\n";
   } else {
      vector<int> answer;
      while(!topsort.empty()){
         answer.push_back(topsort.top());
         topsort.pop();
      }
      
      for(int i : answer){
         cout << i << " ";
      }
   }
   
   
   return 0;
}