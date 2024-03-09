#include <bits/stdc++.h>
//misread problem: thought all vertices of degree 1 have the same color, and only those nodes are that color
using namespace std;

const long long MOD = 998244353LL;
vector<long long> pow2;

vector<int> color;

vector<vector<int>> adj;

//stores the # of nodes with the same color that is above it
vector<int> cdep;
vector<int> curcdep;

void dfs1(int v, int p){
   cdep[v] = curcdep[color[v]];
   curcdep[color[v]]++;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs1(nei,v);
   }
   
   curcdep[color[v]]--;
}

long long answer;

using M = map<pair<int,int>,int>;
M dfs2(int v, int p){
   
   M ret;
   
   pair<int,int> nextcolor = {color[v],cdep[v]+1};
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      
      M cur = dfs2(nei,v);
      
      answer = (answer + pow2[cur[nextcolor]]-1L + MOD)%MOD;
      
      if(cur.size() > ret.size()){
         swap(cur,ret);
      }
      
      for(auto [p1,p2] : cur){
         ret[p1] += p2;
      }
   }
   
   ret[{color[v],cdep[v]}]++;
   
   return ret;
   
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 200005;
   pow2 = vector<long long>(N);
   pow2[0] = 1L;
   for(int k = 1; k < N; k++){
      pow2[k] = (2L * pow2[k-1] + MOD)%MOD;
   }
   
   int n;
   cin >> n;
   
   color = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> color[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   cdep = vector<int>(n+1,0);
   curcdep = vector<int>(n+1,0);
   
   dfs1(1,-1);
   
   answer = (long long)n;
   
   auto ret = dfs2(1,-1);
   //now do color depths of 0
   
   for(int k = 1; k <= n; k++){
      pair<int,int> p = {k,0};
      int val = ret[p];
      if(val >= 2){
         answer = (answer + pow2[ret[p]]-val-1LL + MOD)%MOD;
      }
   }
   
   cout << answer << endl;
   
   
   return 0;
}