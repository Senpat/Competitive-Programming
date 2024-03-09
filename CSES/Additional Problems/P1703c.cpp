#include <bits/stdc++.h>
//wrong
using namespace std;

int n;
vector<vector<int>> adj;

vector<bool> getseen(int start, const vector<vector<int>>& curadj){
   vector<bool> seen = vector<bool>(n+1,false);
   seen[start] = true;
   queue<int> q;
   q.push(start);
   while(!q.empty()){
      int v = q.front();
      q.pop();
      
      for(int nei : curadj[v]){
         if(seen[nei]) continue;
         seen[nei] = true;
         q.push(nei);
      }
   }
   
   return seen;
}

vector<bool> seen1;
vector<bool> seenn;

vector<vector<int>> adjon;
vector<bool> onpath;
vector<bool> visited;
void dfson(int v){
   if(v == n || !seen1[v] || !seenn[v]) return;
   onpath[v] = true;
   visited[v] = true;
   
   for(int nei : adj[v]){
      if(onpath[nei]) continue;
      adjon[v].push_back(nei);
      if(!visited[nei]){
         dfson(nei);
      }
   }  
   
   onpath[v] = false;
}

void dfs(int v, vector<int>& order){
   visited[v] = true;
   for(int nei : adjon[v]){
      if(visited[nei]) continue;
      dfs(nei,order);
   }
   order.push_back(v);
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   vector<vector<int>> radj(n+1,vector<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      radj[b].push_back(a);
   }
   
   //find edges that are a part of a path from 1 to n
   seen1 = getseen(1,adj);
   seenn = getseen(n,radj);
   
   adjon = vector<vector<int>>(n+1,vector<int>());
   onpath = vector<bool>(n+1,false);
   visited = vector<bool>(n+1,false);
   dfson(1);
   
   vector<int> order1;
   visited = vector<bool>(n+1,false);
   dfs(1,order1);
   
   for(int k = 1; k <= n; k++){
      if(adjon[k].size() > 0){
         adjon[k].insert(adjon[k].begin(),adjon[k].back());
         adjon[k].pop_back();
      }
   }
   vector<int> order2;
   visited = vector<bool>(n+1,false);
   dfs(1,order2);
   
   vector<int> answer;
   for(int k = 0; k < order1.size(); k++){
      if(order1[k] == order2[k]){
         answer.push_back(order1[k]);  
      }
   }
   
   sort(answer.begin(),answer.end());
   cout << answer.size() << endl;
   for(int i : answer){
      cout << i << " ";
   }
   
   return 0;
}