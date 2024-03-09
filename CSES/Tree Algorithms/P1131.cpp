#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;

vector<vector<int>> dist;

void dfs(int v, int p){
   
   int max0 = 0;
   int max1 = 0;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      
      dfs(nei,v);
      
      if(dist[nei][0]+1 > max0){
         max1 = max0;
         max0 = dist[nei][0]+1;
      } else if(dist[nei][0] + 1 > max1){
         max1 = dist[nei][0]+1;
      }
   }
   
   dist[v][0] = max0;
   dist[v][1] = max1;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   dist = vector<vector<int>>(n+1,vector<int>(2,0));        //first and second longest distances to a leaf
   
   dfs(1,-1);
   
   int answer = 0;
   for(int k = 1; k <= n; k++){
      answer = max(answer,dist[k][0] + dist[k][1]);
   }
   
   cout << answer << endl;
   
   return 0;
}