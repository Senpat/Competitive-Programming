#include <bits/stdc++.h>

using namespace std;

long long MOD = 1000000007LL;
vector<long long> rands;

long long gethash(int v, int p, const vector<vector<int>>& adj, vector<int>& depth){
   
   int curdepth = 0;
   
   vector<long long> neihashes;
   for(int nei : adj[v]){
      if(nei == p) continue;
      long long neihash = gethash(nei,v,adj,depth);
      curdepth = max(curdepth,depth[nei]+1);
      neihashes.push_back(neihash);
   }
   
   depth[v] = curdepth;
   long long ret = 1LL;
   for(long long neihash : neihashes){
      long long cursum = (rands[curdepth] + neihash + MOD)%MOD;
      ret = (ret * cursum + MOD)%MOD;
   }
   
   return ret;
      
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   mt19937_64 rng(chrono::steady_clock::now().time_since_epoch().count());
   
   int N = 100005;
   for(int k = 0; k < N; k++){
      rands.push_back(rng()%MOD);
   }
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      auto adj1 = vector<vector<int>>(n+1,vector<int>());
      auto adj2 = vector<vector<int>>(n+1,vector<int>());
      
      for(int k = 0; k < n-1; k++){
         int a,b;
         cin >> a >> b;
         adj1[a].push_back(b);
         adj1[b].push_back(a);
      }
      
      for(int k = 0; k < n-1; k++){
         int a,b;
         cin >> a >> b;
         adj2[a].push_back(b);
         adj2[b].push_back(a);
      }
      
      vector<int> depth1 = vector<int>(n+1,0);
      vector<int> depth2 = vector<int>(n+1,0);
      
      auto hash1 = gethash(1,-1,adj1,depth1);
      auto hash2 = gethash(1,-1,adj2,depth2);
      
      if(hash1 == hash2){
         cout << "YES\n";
      } else {
         cout << "NO\n";
      }
   }   
   
   return 0;
}