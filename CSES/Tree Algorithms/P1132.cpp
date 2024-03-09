#include <bits/stdc++.h>

using namespace std;

int n;
vector<vector<int>> adj;

int getlast(int root){
   
   //v, p
   queue<pair<int,int>> q;
   int ret = root;
   q.push(make_pair(root,-1));
   
   while(!q.empty()){
      auto [v,p] = q.front();
      q.pop();
      ret = v;
      
      for(int nei : adj[v]){
         if(nei == p) continue;
         q.push(make_pair(nei,v));
      }
   }
   
   return ret;
}

vector<int> getdist(int root){
   vector<int> dist(n+1,0);
   
   queue<pair<int,int>> q;
   q.push(make_pair(root,-1));
   dist[root] = 0;
   
   while(!q.empty()){
      auto [v,p] = q.front();
      q.pop();
      
      for(int nei : adj[v]){
         if(nei == p) continue;
         dist[nei] = dist[v]+1;
         q.push(make_pair(nei,v));
      }
   }
   
   return dist;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   int node1 = getlast(1);
   int node2 = getlast(node1);
   
   vector<int> dist1 = getdist(node1);
   vector<int> dist2 = getdist(node2);
   
   for(int k = 1; k <= n; k++){
      cout << max(dist1[k],dist2[k]) << " ";
   }
   
   
   
   return 0;
}