#include <bits/stdc++.h>

using namespace std;


vector<vector<int>> adj;
vector<int> dist;
vector<int> nxt;

void dfs(int v){
   
   int longest = 1;
   int nextpath = -1;
   for(int nei : adj[v]){
      if(dist[nei] == 0){
         dfs(nei);
      }
      if(dist[nei]+1 > longest){
         longest = dist[nei]+1;
         nextpath = nei;
      }
      //cout << v << " " << nei << " " << dist[nei] << endl;
   }
   
   dist[v] = longest;
   nxt[v] = nextpath;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   for(int k = 0; k <= n; k++){
      adj.push_back(vector<int>());
      dist.push_back(0);
      nxt.push_back(-1);
   }
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
   }
   
   for(int k = 1; k <= n; k++){
      if(dist[k] != 0) continue;
      dfs(k);
   }
   
   int longest = 0;
   int start = -1;
   for(int k = 1; k <= n; k++){
      if(dist[k] > longest){
         longest = dist[k];
         start = k;
      }
   }
   
   cout << longest << endl;
   vector<int> answer;
   int i = start;
   while(i != -1){
      answer.push_back(i);
      i = nxt[i];
   }
   for(int x : answer){
      cout << x << " ";
   }
   
   
   
   return 0;
}