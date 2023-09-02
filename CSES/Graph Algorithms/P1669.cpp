#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<int> parent;
int start1;
int start2;

void dfs(int v, int p){
   if(start1 != -1) return;
   //cout << v << " " << p << endl;
   parent[v] = p;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      if(parent[nei] != 0){
         start1 = nei;
         start2 = v;
         return;
      }
      dfs(nei,v);
      if(start1 != -1) return;
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
   
   
   parent = vector<int>(n+1,0);
   start1 = -1;
   start2 = -1;
   
   for(int k = 1; k <= n; k++){
      if(parent[k] != 0) continue;   
      dfs(k,-1);
      if(start1 != -1) break;
   }
   //cout << "done dfs" << endl;
   //cout << start1 << " " << start2 << endl;
   //for(int k = 1; k <= n; k++){
   //   cout << parent[k] << " ";
   //}
   //cout << endl;
   if(start1 == -1){
      cout << "IMPOSSIBLE\n";
      return 0;
   }
   vector<int> path;
   path.push_back(start1);
   path.push_back(start2);
   
   int i = parent[start2];
   while(i != start1){
      //cout << i << endl;
      path.push_back(i);
      i = parent[i];
   }
   
   path.push_back(start1);
   
   cout << path.size() << endl;
   for(int i : path){
      cout << i << " ";
   }
   
   
   
   return 0;
}