#include <bits/stdc++.h>
//bellman ford
using namespace std;

struct Edge{
   int from;
   int to;
   long long w;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> adj(n+1,vector<int>());
   vector<Edge> edges(m);
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      edges[k] = {a,b,-w};
      
      adj[a].push_back(b);
   }
   
   vector<long long> dist(n+1,LLONG_MAX);
   dist[1] = 0LL;
   bool stopped = false;
   
   unordered_set<int> lastupdated;
   
   for(int rep = 0; rep < n; rep++){
      bool found = false;
      for(int k = 0; k < m; k++){
         auto e = edges[k];
         if(dist[e.from] < LLONG_MAX){
            if(dist[e.to] > dist[e.from] + e.w){
               found = true;
               dist[e.to] = dist[e.from] + e.w;
               
               if(rep == n-1){
                  lastupdated.insert(e.to);
               }
            }
         }
      }
      
      if(!found){
         stopped = true;
         break;
      }
   }
   
   //see if n can be reached by any node that was updated on the last iteration (part of negative cycle)
   queue<int> q;
   for(int i : lastupdated){
      q.push(i);
   }
   
   while(!q.empty()){
      int v = q.front();
      q.pop();
      
      for(int nei : adj[v]){
         if(lastupdated.find(nei) == lastupdated.end()){
            lastupdated.insert(nei);
            q.push(nei);
         }
      }
   }
      
      
      
   
   if(stopped || lastupdated.find(n) == lastupdated.end()){
      cout << -dist[n] << endl;
   } else {
      cout << "-1\n";
   }
   
   
   
   return 0;
}