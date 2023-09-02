#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   vector<vector<pair<int,long long>>> adj(n+1,vector<pair<int,long long>>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      adj[a].push_back(make_pair(b,w));
   }
   
   vector<long long> dist(n+1,LLONG_MAX);
   dist[1] = 0LL;
   
   priority_queue<pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>>> pq;
   
   pq.push(make_pair(0LL,1));
   
   while(!pq.empty()){
      auto p = pq.top();
      pq.pop();
      long long d = p.first;
      int v = p.second;
      
      if(d != dist[v]) 
         continue;
      
      for(auto e : adj[v]){
         long long newd = d + e.second;
         if(newd > dist[e.first]) 
            continue;
         dist[e.first] = newd;
         pq.push(make_pair(newd,e.first));
      }
   }
    
   for(int k = 1; k <= n; k++){
      cout << dist[k] << " ";
   }
         
   
   return 0;
}