#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;
int n,m;

vector<vector<int>> adj;
vector<long long> dp;
vector<bool> seen;

void dfs(int v){
   seen[v] = true;
   long long cursum = 0LL;
   for(int nei : adj[v]){
      if(!seen[nei])
         dfs(nei);
      cursum = (cursum + dp[nei] + MOD)%MOD;
   }
   
   if(v == n) cursum = (cursum + 1 + MOD)%MOD;
   dp[v] = cursum;
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
   }
   
   dp = vector<long long>(n+1,0LL);
   seen = vector<bool>(n+1,false);
   
   dfs(1);
   
   cout << dp[1] << endl;
   
   return 0;
}