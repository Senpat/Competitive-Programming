#include <bits/stdc++.h>

using namespace std;

vector<vector<pair<int,int>>> adj;

vector<pair<int,int>> answer;

vector<bool> seen;
vector<bool> isbackedge;
vector<int> parity;
vector<int> parent;

void dfs1(int v, int p){
   parent[v] = p;
   seen[v] = true;
   
   for(auto [to,i] : adj[v]){
      if(to == p) continue;
      if(isbackedge[i]) continue;
      if(seen[to]){
         //backedge
         isbackedge[i] = true;
         parity[v] = 1-parity[v];
         //direct edge upwards
         answer.push_back(make_pair(v,to));
      } else {
         dfs1(to,v);
      }
   }
}

//returns 1 if parent edge points down, 0 if parent edge points up
int dfs2(int v, int p){
   
   for(auto [to,i] : adj[v]){
      if(to == p) continue;
      if(isbackedge[i]) continue;
      
      int ret = dfs2(to,v);
      
      if(ret == 1){
         parity[v] = 1-parity[v];
      }
   }
   
   if(parent[v] != -1){
      if(parity[v] == 0){
         //point parent edge down
         answer.push_back(make_pair(parent[v],v));
         return 1;
      } else {
         //point parent edge up
         answer.push_back(make_pair(v,parent[v]));
         return 0;
      }
   } else {
      //should be 0
      return parity[v];  
   }
}   

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<pair<int,int>>>(n+1,vector<pair<int,int>>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(make_pair(b,k));
      adj[b].push_back(make_pair(a,k));
   }
   
   isbackedge = vector<bool>(m,false);
   seen = vector<bool>(n+1,false);
   parity = vector<int>(n+1,0);
   parent = vector<int>(n+1);
   
   answer = vector<pair<int,int>>();
   
   vector<int> roots;
   
   for(int k = 1; k <= n; k++){
      if(seen[k]) continue;
      dfs1(k,-1);
      roots.push_back(k);
   }
   
   bool fail = false;
   for(int root : roots){
      int ret = dfs2(root,-1);
      if(ret != 0){
         fail = true;
      }
   }
   
   if(fail){
      cout << "IMPOSSIBLE\n";
   } else {
      for(auto p : answer){
         cout << p.first << " " << p.second << endl;
      }
   }
   
   return 0;
}