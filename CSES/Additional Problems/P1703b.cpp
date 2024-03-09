#include <bits/stdc++.h>
//wrong solution, doesn't account for nodes not on shortest path
using namespace std;

vector<int> getdist(int start, int n, const vector<vector<int>>& adj){
   vector<int> dist = vector<int>(n+1,-1);
   dist[start] = 0;
   queue<int> q;
   q.push(start);
   while(!q.empty()){
      int v = q.front();
      q.pop();
      
      for(int nei : adj[v]){
         if(dist[nei] != -1) continue;
         dist[nei] = dist[v]+1;
         q.push(nei);
      }
   }
   
   return dist;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> adj(n+1,vector<int>());
   vector<vector<int>> radj(n+1,vector<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      radj[b].push_back(a);
   }
   
   vector<int> dist1 = getdist(1,n,adj);
   vector<int> distn = getdist(n,n,radj);
   
   int dist = dist1[n];
   
   //0 means hasn't been filled, >1 means filled with that node, -1 means filled with multiple
   vector<int> node(dist+1,0);
   for(int k = 1; k <= n; k++){
      if(dist1[k] != -1 && distn[k] != -1 && dist1[k] + distn[k] == dist){
         if(node[dist1[k]] == 0){
            node[dist1[k]] = k;
         } else if(node[dist1[k]] > 0){
            node[dist1[k]] = -1;
         }
      }
         
   }
   
   vector<int> answer;
   for(int k = 0; k <= dist; k++){
      if(node[k] > 0){
         answer.push_back(node[k]);
      }
   }
   
   sort(answer.begin(),answer.end());
   cout << answer.size() << endl;
   for(int i : answer){
      cout << i << " ";
   }
   
   
   return 0;
}