#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,s,t;
   cin >> n >> s >> t;
   
   vector<bool> can(n);
   
   vector<unordered_set<int>> adj(n,unordered_set<int>());
   
   for(int k = 0; k < n; k++){
      char ch;
      int vn;
      cin >> ch >> vn;
      
      can[k] = (ch == 'N');
      
      for(int j = 0; j < vn; j++){
         int i;
         cin >> i;
         adj[k].insert(i);
      }
   }
   
   unordered_set<int> vs;
   for(int k = 0; k < n; k++) vs.insert(k);
   
   vector<int> dist(n,INT_MAX);
   
   //(v, distance)
   queue<pair<int,int>> q;
   q.push({s,0});
   dist[s] = 0;
   vs.erase(s);
   
   while(!q.empty()){
      auto p = q.front();
      q.pop();
      
      int v = p.first;
      int d = p.second;
      
      if(v == t){
         break;
      }
      
      if(can[v]){
         for(int nei : adj[v]){
            if(dist[nei] != INT_MAX) continue;
            dist[nei] = d+1;
            vs.erase(nei);
            q.push({nei,d+1});
         }
      } else {
         unordered_set<int> nextvs;
         for(int next : vs){
            if(dist[next] != INT_MAX) continue;
            if(adj[v].find(next) == adj[v].end()){
               dist[next] = d+1;
               q.push({next,d+1});
            } else {
               nextvs.insert(next);
            }
         }
         vs = move(nextvs);
      }
   }
   
   
   if(dist[t] == INT_MAX){
      cout << "impossible\n";
   } else {
      cout << dist[t] << "\n";
   }
   
   return 0;
}