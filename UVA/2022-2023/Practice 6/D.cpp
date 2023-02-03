#include <bits/stdc++.h>
//find shortest cycle
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> adj(n,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   int shortest = INT_MAX;
   
   for(int k = 0; k < n; k++){
      vector<int> dist(n,INT_MAX);
      dist[k] = 0;
      
      //(d,(v,p))
      queue<pair<int,pair<int,int>>> q;
      q.push({0,{k,-1}});
      
      while(!q.empty()){
         auto ret = q.front();
         q.pop();
         
         int d = ret.first;
         int v = ret.second.first;
         int p = ret.second.second;
         
         if(d != dist[v]) continue;
         
         for(int nei : adj[v]){
            if(nei == p) continue;
            
            if(dist[nei] == INT_MAX){
               dist[nei] = d+1;
               q.push({d+1,{nei,v}});
            } else {
               shortest = min(shortest,dist[nei]+d+1);
            }
         }
            
      }
   }
   
   
   
   if(shortest == INT_MAX){
      cout << "impossible\n";
   } else {
      cout << shortest << endl;
   }
   
   
   
   return 0;
}