#include <bits/stdc++.h>
//editorial
using namespace std;

const long long MOD = 998244353LL;

//inverse adj matrix
vector<unordered_set<int>> iadj;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   iadj = vector<unordered_set<int>>(n+1,unordered_set<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      iadj[a].insert(b);
      iadj[b].insert(a);
   }
   
   vector<int> dist(n+1,INT_MAX);
   dist[n] = 0;
   
   unordered_set<int> uset;
   for(int k = 1; k < n; k++){
      uset.insert(k);
   }
   
   queue<int> q;
   q.push(n);
   
   vector<int> order;
   
   while(!q.empty()){
      int v = q.front();
      q.pop();
      order.push_back(v);
      
      unordered_set<int> rem;
      for(int i : uset){
         if(iadj[v].find(i) != iadj[v].end()) continue;
         rem.insert(i);
         dist[i] = dist[v]+1;
         q.push(i);
      }
      
      for(int r : rem){
         uset.erase(r);
      }
   }
   
   vector<long long> distsum(n+1,0LL);
   vector<long long> dp(n+1,0LL);
   dp[n] = 1LL;
   distsum[0] = 1LL;
   
   bool saw1 = false;
   for(int v : order){
      if(v == n) continue;
      if(v == 1) saw1 = true;
      
      long long sub = 0LL;
      for(int nei : iadj[v]){
         if(dist[nei] == dist[v]-1){
            sub = (sub + dp[nei] + MOD)%MOD;
         }
      }
      
      dp[v] = ((distsum[dist[v]-1] - sub)%MOD + MOD)%MOD;
      distsum[dist[v]] = (distsum[dist[v]] + dp[v] + MOD)%MOD;
   }
   
   if(!saw1){
      cout << "-1\n";
   } else {
      cout << dp[1] << endl;
   }
   
   
   
   
   return 0;
}