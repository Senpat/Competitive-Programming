#include <bits/stdc++.h>

using namespace std;

#define MAXN 100005

long long costs[MAXN];
unordered_set<int> seen;
long long min1;
vector<vector<int>> adj(MAXN);

void dfs(int v){
   min1 = min(min1,costs[v]);
   seen.insert(v);
   for(int nei : adj[v]){
      if(seen.find(nei) != seen.end()) continue;
      dfs(nei);
      
   }
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   for(int k = 1; k <= n; k++){
      cin >> costs[k];
   }
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a>> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   long long sum = 0;
   
   for(int k = 1; k <= n; k++){
      if(seen.find(k) != seen.end()) continue;
      min1 = LLONG_MAX;
      seen.insert(k);
      dfs(k);
      sum += min1;
      //cout << k << " " << sum << endl;
   }
   cout << sum;
   
   
   
   return 0;
}