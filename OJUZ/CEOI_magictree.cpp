#include <bits/stdc++.h>
//problem from https://usaco.guide/adv/slope-trick?lang=cpp
using namespace std;

vector<vector<int>> adj;

vector<pair<int,long long>> fruits;

map<int,long long> dfs(int v, int p){
   
   map<int,long long> ret;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      auto neimap = dfs(nei,v);
      
      if(ret.size() < neimap.size()) swap(ret,neimap);
      
      for(auto [d,w] : neimap){
         ret[d] += w;
      }
   }
   
   if(fruits[v].first != -1){
      //add this fruit
      long long tot = fruits[v].second;
      ret[fruits[v].first] += tot;
      
      int cur = fruits[v].first;
      //this will loop O(N) times amortized (every fruit will be removed once)
      while(true){
         auto it = ret.upper_bound(cur);
         if(it == ret.end()) break;
         cur = it->first;
         
         if(it->second <= tot){
            tot -= it->second;
            ret.erase(it);
         } else {
            it->second -= tot;
            break;
         }
      }
   }
   
   return ret;
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,D;
   cin >> n >> m >> D;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 2; k <= n; k++){
      int p;
      cin >> p;
      adj[k].push_back(p);
      adj[p].push_back(k);
   }
   
   fruits = vector<pair<int,long long>>(n+1,{-1,-1LL});
   for(int k = 0; k < m; k++){
      int v,d;
      long long w;
      cin >> v >> d >> w;
      fruits[v] = {d,w};
   }
   
   auto ret = dfs(1,-1);
   
   long long answer = 0LL;
   for(auto [d,w] : ret){
      answer += w;
   }
   
   cout << answer << endl;
   
   
   
   return 0;
}