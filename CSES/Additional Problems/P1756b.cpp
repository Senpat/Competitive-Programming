#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   int i;
};


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<Edge>> adj(n+1,vector<Edge>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back({b,k});
      adj[b].push_back({a,k});
   }
   
   vector<pair<int,int>> answer(m);
   
   vector<bool> seenedge(m,false);
   vector<bool> seen(n+1,false);
   
   for(int k = 1; k <= n; k++){
      if(seen[k]) continue;
      queue<int> q;
      q.push(k);
      
      seen[k] = true;
      while(!q.empty()){
         int v = q.front();
         q.pop();
         
         for(Edge e : adj[v]){
            if(seenedge[e.i]) continue;
            
            answer[e.i] = make_pair(v,e.to);
            seenedge[e.i] = true;
            
            if(!seen[e.to]){
               seen[e.to] = true;
               q.push(e.to);
            }
            
            
         }
      }
   }
   
   for(int k = 0; k < m; k++){
      cout << answer[k].first << " " << answer[k].second << "\n";
   }
   
   return 0;
}