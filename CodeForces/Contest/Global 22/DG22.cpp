#include <bits/stdc++.h>

using namespace std;

void dfs(int v, const vector<vector<int>>& adj, vector<int>& answer){
   answer.push_back(v);
   
   int last = -1;
   for(int nei : adj[v]){
      if(adj[nei].size() > 0){
         last = nei;
      } else {
         answer.push_back(nei);
      }
   }
   
   if(last != -1) dfs(last,adj,answer);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n+1);
      for(int k = 1; k <= n; k++){
         cin >> array[k];
      }
      
      //int m2 = n+1;
      int m = 0;
      int start = 0;
      vector<vector<int>> adj(n+2,vector<int>());
      for(int k = 1; k <= n; k++){
         adj[array[k]].push_back(k);
         if(array[k] == n+1) start = n+1;
         if(k < array[k]){
            m = max(m,k);
         }
      }
      
      //if(m == 0) m = m2;
      
      vector<int> answer;
      dfs(start,adj,answer);
      
      cout << m << "\n";
      for(int k = 1; k < answer.size(); k++){
         cout << answer[k] << " ";
      }
      cout << "\n";
      
      
   }
   
   
   return 0;
}