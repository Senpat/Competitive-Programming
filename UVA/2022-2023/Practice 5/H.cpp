#include <bits/stdc++.h>

using namespace std;

vector<long long> djikstra(int n, const vector<vector<pair<int,long long>>>& adj, int x){
   vector<long long> dist(n+1,LLONG_MAX);
   
   //pair(distance, node)
   priority_queue<pair<long long, int>,vector<pair<long long,int>>, greater<pair<long long,int>>> pq;
   dist[x] = 0LL;
   pq.push({0LL,x});
   
   while(!pq.empty()){
      auto p = pq.top();
      pq.pop();
      
      int v = p.second;
      long long d = p.first;
      
      if(dist[v] != d){
         continue;
      }
      
      for(const auto& e : adj[v]){
         long long newd = d+e.second;
         if(newd < dist[e.first]){
            dist[e.first] = newd;
            pq.push({newd,e.first});
         }
      }
   }
   
   return dist;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m,p;
   long long g;
   cin >> n >> m >> p >> g;
   
   vector<long long> start(p,0LL);
   for(int k = 0; k < p; k++){
      cin >> start[k];
   }
   
   vector<vector<pair<int,long long>>> adj(n+1,vector<pair<int,long long>>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      adj[a].push_back({b,w});
      adj[b].push_back({a,w});
   }
   
   //do djikstras from every starting point, also 1
   vector<long long> dist1 = djikstra(n,adj,1);
   
   vector<vector<long long>> dists(p,vector<long long>());
   long long initial = 0LL;
   for(int k = 0; k < p; k++){
      dists[k] = djikstra(n,adj,start[k]);
      
      initial += dists[k][1];
   }
   
   long long answer = initial;
   for(int k = 2; k <= n; k++){
      //place an edge from k to 1 and see if it improves the answer
      
      long long num = 0LL;
      for(int j = 0; j < p; j++){
         //check if this node passes through there
         if(dist1[k]+dists[j][k] == dist1[start[j]]){
            num++;
         }  
      }
      
      answer = min(answer, initial-dist1[k]*num + g*num);
   }
   
   cout << answer << endl;
   
   
   
   
   
   
   return 0;
}