#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   int i;
};

vector<vector<Edge>> adj;

vector<bool> seen;
vector<bool> isbackedge;
vector<int> backto;
vector<int> backfrom;
vector<Edge> parent;

vector<pair<int,int>> answer;

void dfs(int v){

   seen[v] = true;
   for(Edge e : adj[v]){
      if(isbackedge[e.i] || e.to == parent[v].to) continue;
      if(seen[e.to]){
         //backedge
         isbackedge[e.i] = true;
         answer[e.i] = make_pair(v,e.to);
         
         backto[e.to]++;
         backfrom[v]++;
      } else {
         parent[e.to] = {v,e.i};
         answer[e.i] = make_pair(v,e.to);
         dfs(e.to);
      }
      
   }

}

bool found;
vector<int> backedgeover;
void checkbridges(int v){
   
   int sumover = 0;
   for(Edge e : adj[v]){
      if(isbackedge[e.i] || e.to == parent[v].to){
         continue;
      }
      checkbridges(e.to);
      sumover += backedgeover[e.i];
   }
   
   //find # of backedges over its parent
   if(parent[v].to != -1){
      backedgeover[parent[v].i] = sumover + backfrom[v] - backto[v];
      if(backedgeover[parent[v].i] == 0){
         found = true;
      }
   }
   
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m; 
   
   adj = vector<vector<Edge>>(n+1,vector<Edge>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back({b,k});
      adj[b].push_back({a,k});
   }
   
   seen = vector<bool>(n+1,false);
   isbackedge = vector<bool>(m,false);
   backto = vector<int>(n+1,0);
   backfrom = vector<int>(n+1,0);
   parent = vector<Edge>(n+1);
   parent[1] = {-1,-1};
   
   answer = vector<pair<int,int>>(m);
   
   dfs(1);
   
   found = false;
   backedgeover = vector<int>(m,0);
   checkbridges(1);
   
   for(int k = 1; k <= n; k++){
      if(!seen[k]) found = true;
   }
   
   if(found){
      cout << "IMPOSSIBLE\n";
   } else {
      for(int k = 0; k < m; k++){
         cout << answer[k].first << " " << answer[k].second << "\n";
      }
   }
   
   
   return 0;
}