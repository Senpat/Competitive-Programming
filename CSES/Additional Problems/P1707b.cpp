#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> adj(n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   int answer = INT_MAX;
   
   for(int k = 1; k <= n; k++){
      vector<int> dist(n+1,-1);
      vector<int> parent(n+1,-1);
       
      dist[k] = 0;
      
      queue<int> q;
      q.push(k);
      
      while(!q.empty()){
         int v = q.front();
         q.pop();
         
         for(int nei : adj[v]){
            if(nei == parent[v]) continue;
            if(nei < k) continue;            //optimization
            if(dist[nei] != -1){
               answer = min(answer,dist[v] + dist[nei] + 1);
               continue;
            }
            dist[nei] = dist[v]+1;
            parent[nei] = v;
            q.push(nei);
         }
      }
      
   }
   
   if(answer == INT_MAX){
      cout << "-1\n";
   } else {
      cout << answer << endl;
   }
   
   return 0;
}