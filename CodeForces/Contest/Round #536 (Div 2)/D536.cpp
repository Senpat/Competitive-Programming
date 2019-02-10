#include <bits/stdc++.h>

using namespace std;

int n,m;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   vector<vector<int>> adj(n+1);
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   priority_queue<int,vector<int>,greater<int>> pq;
   
   vector<int> answer;
   answer.push_back(1);
   
   
   unordered_set<int> seen;
   seen.insert(1);
   
   for(int i : adj[1]){
      if(seen.find(i) != seen.end()) continue;
      pq.push(i);
      seen.insert(i);
   }
   
   while(!pq.empty()){
      int cur = pq.top();
      pq.pop();
      
      
      
      answer.push_back(cur);
      
      for(int nei : adj[cur]){
         if(seen.find(nei) == seen.end()){
            seen.insert(nei);
            pq.push(nei);
         }
      }
   
   }
   
   for(int i : answer){
      cout << i << " ";
   }
   
   
   
   
   return 0;
}