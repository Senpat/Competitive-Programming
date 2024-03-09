#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   long long w;
};

const long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<Edge>> adj(n+1,vector<Edge>());
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      adj[a].push_back({b,w});
   }
   
   vector<long long> mindist(n+1,LLONG_MAX);
   vector<long long> numpath(n+1,0LL);
   vector<int> minpath(n+1,INT_MAX);
   vector<int> maxpath(n+1,0);
   
   priority_queue<pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>>> pq;
   pq.push(make_pair(0LL,1));
   mindist[1] = 0LL;
   numpath[1] = 1LL;
   minpath[1] = 0;
   maxpath[1] = 0;
   
   while(!pq.empty()){
      auto [d,v] = pq.top();
      pq.pop();
      
      if(mindist[v] != d){
         //seen node before
         continue;
      }
      
      for(Edge e : adj[v]){
         int nei = e.to;
         long long newd = mindist[v] + e.w;
         if(mindist[nei] > newd){
            mindist[nei] = newd;
            numpath[nei] = numpath[v];
            minpath[nei] = minpath[v]+1;
            maxpath[nei] = maxpath[v]+1;
            pq.push(make_pair(newd,nei));
         } else if(newd == mindist[nei]){
            numpath[nei] = (numpath[nei] + numpath[v] + MOD)%MOD;
            minpath[nei] = min(minpath[nei],minpath[v]+1);
            maxpath[nei] = max(maxpath[nei],maxpath[v]+1);
         }
      }
   }
   
   cout << mindist[n] << " " << numpath[n] << " " << minpath[n] << " " << maxpath[n] << endl;
   
   
   
   
   
   return 0;
}