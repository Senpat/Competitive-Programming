#include <bits/stdc++.h>
//didn't realize flights are 1 way
using namespace std;

struct Edge{
   int to;
   int i;
};

vector<vector<Edge>> adj;
vector<bool> seen;
vector<bool> isbackedge;
vector<int> backfrom;
vector<int> backto;

void dfs1(int v, int p){
   seen[v] = true;
   
   for(Edge e : adj[v]){
      if(e.to == p || isbackedge[e.i]) continue;
      if(seen[e.to]){
         isbackedge[e.i] = true;
         backfrom[v]++;
         backto[e.to]++;
      } else {
         dfs1(e.to,v);
      }
   }
   
}

vector<int> backedges;
vector<bool> isartpoint;

void dfs2(int v, int p){
   //don't worry about setting isartpoint for node 1
   bool haschild = false;
   for(Edge e : adj[v]){
      if(e.to == p || isbackedge[e.i]) continue;
      dfs2(e.to,v);
      
      backedges[v] += backedges[e.to] + backfrom[e.to];
      haschild = true;
   }
   backedges[v] -= backto[v];
   
   isartpoint[v] = (backedges[v] == 0 && haschild);
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
   backfrom = vector<int>(n+1,0);
   backto = vector<int>(n+1,0);
   
   dfs1(1,-1);
   
   //# of backedges that go over this node
   backedges = vector<int>(n+1,0);
   //is articulation point
   isartpoint = vector<bool>(n+1,false);
   
   dfs2(1,-1);
   
   //get shortest path from 1 to n
   vector<int> parent = vector<int>(n+1,-1);
   parent[1] = 0;
   queue<int> q;
   q.push(1);
   
   while(!q.empty()){
      int v = q.front();
      q.pop();
      
      for(Edge e : adj[v]){
         if(parent[e.to] != -1) continue;
         parent[e.to] = v;
         q.push(e.to);
      }
   }
   
   vector<int> path;
   int i = n;
   while(i != 0){
      path.push_back(i);
      i = parent[i];
   }
   
   vector<int> answer;
   for(int k = path.size()-1; k >= 0; k--){
      if(path[k] == 1 || path[k] == n || isartpoint[path[k]]){
         answer.push_back(path[k]);
      }
   }
   sort(answer.begin(),answer.end());
   /*
   for(int k = 1; k <= n; k++){
      cout << backedges[k] << " " << isartpoint[k] << endl;
   }*/
   
   cout << answer.size() << endl;
   for(int i : answer){
      cout << i << " ";
   }
   
   
   return 0;
}