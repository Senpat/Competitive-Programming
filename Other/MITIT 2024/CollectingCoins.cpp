#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   long long c;
   long long r;
};

int n;
vector<vector<Edge>> adj;

bool check(long long x){
   //maxd[v] stores the max coins you can have when reaching v
   vector<long long> maxd(n+1,-1LL);
   
   maxd[1] = x;
   
   priority_queue<pair<long long,int>> pq;
   //priority_queue<pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>>> pq;
   
   pq.push({x,1});
   
   while(!pq.empty()){
      auto [d,v] = pq.top();
      pq.pop();
      
      if(v == n) return true;
      if(maxd[v] != d) continue;
      
      for(auto e : adj[v]){
         if(d >= e.c){
            if(e.c < e.r) return true;
            
            long long newd = d-e.c+e.r;
            if(newd > maxd[e.to]){
               maxd[e.to] = newd;
               pq.push({newd,e.to});
            }
         }
      }
   }
   
   return false;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int m;
   cin >> n >> m;
   
   adj = vector<vector<Edge>>(n+1,vector<Edge>());
   for(int k = 0; k < m; k++){
      int a,b;
      long long c,r;
      cin >> a >> b >> c >> r;
      
      adj[a].push_back({b,c,r});
      adj[b].push_back({a,c,r});
   }
   
   long long l = 0LL;
   long long r = 100000000000005LL;
   long long ans = r;
   
   while(l <= r){
      long long mid = l + (r-l)/2L;
      
      if(check(mid)){
         ans = mid;
         r = mid-1;
      } 
      else {
         l = mid+1;
      }
   }
   
   cout << ans << endl;
   
   
   
   return 0;
}