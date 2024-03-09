#include <bits/stdc++.h>
//experimenting with tle sol
using namespace std;
 
#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
 
vector<vector<int>> adj;
vector<int> c;
 
vector<int> answer;
 
set<int> dfs(int v, int p){
   
   int maxi = 0;
   vector<set<int>> sets;
   
   for(int nei : adj[v]){
      if(nei == p) continue; 
      sets.push_back(move(dfs(nei,v)));
      
      if(sets.back().size() > sets[maxi].size()){
         maxi = sets.size()-1;
      }
   }
   
   if(sets.size() == 0){
      set<int> ret;
      ret.insert(c[v]);
      answer[v] = 1;
      return ret;
   }
   
   for(int k = 0; k < sets.size(); k++){
      if(k != maxi){
         for(int i : sets[k]){
            sets[maxi].insert(i);
         }
      }
   }
   
   sets[maxi].insert(c[v]);
   
   answer[v] = sets[maxi].size();
   return sets[maxi];
}
 
 
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   c = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> c[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   answer = vector<int>(n+1,0);
   
   dfs(1,-1);
   
   for(int k = 1; k <= n; k++){
      cout << answer[k] << " ";
   }
   
   return 0;
}