#include <bits/stdc++.h>

using namespace std;


vector<vector<int>> adj;

vector<bool> seen;

void dfs(int v){
   for(int nei : adj[v]){
      if(seen[nei]) continue;
      
      seen[nei] = true;
      dfs(nei);
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   vector<int> freq(m);
   vector<string> ballot(m);
   
   vector<vector<int>> indexof(m,vector<int>(n,0));
   
   for(int k = 0; k < m; k++){
      int i;
      string s;
      cin >> i >> s;
      freq[k] = i;
      ballot[k] = s;
      for(int j = 0; j < n; j++){
         indexof[k][ballot[k].at(j)-'A'] = j;
      }
   }
   
   for(int k = 0; k < n; k++) adj.push_back(vector<int>());
   
   for(int k = 0; k < n; k++){
      for(int j = k+1; j < n; j++){
         int kp = 0;
         int jp = 0;
         
         for(int h = 0; h < m; h++){
            if(indexof[h][k] < indexof[h][j]){
               kp += freq[h];
            } else {
               jp += freq[h];
            }
         }
         
         if(kp > jp){
            adj[k].push_back(j);
         } else {
            adj[j].push_back(k);
         }
      }
   }
   
   //for each node check if all nodes are reachable from it
   for(int k = 0; k < n; k++){
      seen = vector<bool>(n,false);
      
      seen[k] = true;
      
      dfs(k);
      
      bool found = true;
      for(int k = 0; k < n; k++){
         found &= seen[k];
      }
      
      if(found){
         cout << (char)('A'+k) << ": can win\n";
      } else {
         cout << (char)('A'+k) << ": can't win\n";
      }
   }
      
   
   
   return 0;
}