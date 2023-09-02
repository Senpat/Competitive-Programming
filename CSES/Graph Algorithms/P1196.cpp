#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   long long w;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,x;
   cin >> n >> m >> x;
   
   vector<vector<Edge>> adj(n+1,vector<Edge>());
   
   for(int k = 0; k < m; k++){
      int a,b,w;
      cin >> a >> b >> w;
      adj[a].push_back({b,w});
   }
   
   priority_queue<pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>>> pq;
   
   vector<priority_queue<long long>> dists(n+1,priority_queue<long long>());
   vector<int> seen(n+1,0);
   
   dists[1].push(0);
   pq.push(make_pair(0,1));
   
   while(!pq.empty()){
      auto [d,v] = pq.top();
      pq.pop();
      
      if(dists[v].top() < d || seen[v] >= x){
         continue;
      }
      seen[v]++;
      
      for(Edge e : adj[v]){
         if(seen[e.to] >= x) continue;
         long long newd = d+e.w;
         if(dists[e.to].size() < x){
            dists[e.to].push(newd);
            pq.push(make_pair(newd,e.to));
         } else if(dists[e.to].top() > newd){
            dists[e.to].pop();
            dists[e.to].push(newd);
            pq.push(make_pair(newd,e.to));
         }
      }
   }
   
   vector<long long> answer;
   
   while(!dists[n].empty()){
      answer.push_back(dists[n].top());
      dists[n].pop();
   }
   
   for(int k = answer.size()-1; k >= 0; k--){
      cout << answer[k] << " ";
   }
    
   
   
   return 0;
}