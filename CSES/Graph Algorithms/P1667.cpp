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
   
   vector<int> parent(n+1,-1);
   vector<int> dist(n+1,INT_MAX);
   dist[1] = 1;
   
   queue<int> q;
   q.push(1);
   while(!q.empty()){
      int v = q.front();
      q.pop();
      
      for(int nei : adj[v]){
         if(dist[nei] != INT_MAX){
            continue;
         }
         dist[nei] = dist[v]+1;
         parent[nei] = v;
         q.push(nei);
      }
   }
   
   if(dist[n] == INT_MAX){
      cout << "IMPOSSIBLE\n";
   } else {
      vector<int> answer;
      int i = n;
      while(i != -1){
         answer.push_back(i);
         i = parent[i];
      }
      reverse(answer.begin(),answer.end());
      cout << dist[n] << endl;
      for(int i : answer){   
         cout << i << " ";
      }
   }
   
   
      
   
   return 0;
}