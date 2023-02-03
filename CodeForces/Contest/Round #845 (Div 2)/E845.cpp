#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   int w;
   int dir;
};

int n;
vector<vector<Edge>> adj;

unordered_set<int> seen;

stack<int> topstack;


void dfsconnected(int v, int thresh){
   for (const auto& e : adj[v]){
      if(seen.find(e.to) != seen.end()) 
         continue;
      if(e.dir == -1 && e.w >= thresh) 
         continue;
      seen.insert(e.to);
      dfsconnected(e.to,thresh);
   }
}

void topsort(int v, int thresh){
   for( const auto& e : adj[v]){
      if(seen.find(e.to) != seen.end()) 
         continue;
      if(e.dir == -1 && e.w >= thresh) 
         continue;
      seen.insert(e.to);
      topsort(e.to,thresh);
   }
   
   topstack.push(v);
}

bool check(int thresh){
   //do topsort
   topstack = stack<int>();
   seen = unordered_set<int>();
   
   for(int k = 1; k <= n; k++){
      if(seen.find(k) == seen.end()){
         seen.insert(k);
         topsort(k,thresh);
      }
   }
   
   //get top of topsort
   int ttop = topstack.top();
   
   //check if connected
   seen = unordered_set<int>();
   seen.insert(ttop);
   dfsconnected(ttop,thresh);
   return seen.size() == n;
}




int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
   
      int m;
      cin >> n >> m;
      
      vector<int> edges;
      
      adj = vector<vector<Edge>>(n+1,vector<Edge>());
      
      for(int k = 0; k < m; k++){
         int a,b,w;
         cin >> a >> b >> w;
         
         Edge e1 = {b,w,1};
         Edge e2 = {a,w,-1};
         adj[a].push_back(e1);
         adj[b].push_back(e2);
         
         edges.push_back(w);
      }
      
      //check if connected
      seen = unordered_set<int>();
      seen.insert(1);
      dfsconnected(1,INT_MAX);
      if(seen.size() != n){
         cout << -1 << endl;
         continue;
      }
      
      sort(edges.begin(),edges.end());
      
      int l = 0;
      int r = m-1;
      int ans = edges[m-1];
      while(l <= r){
         int mid = l + (r-l)/2;
            
         //thresh is edges.get(mid)
         if(check(edges[mid])){
            if(mid == 0) ans = 0;
            else ans = edges[mid-1];
               
            r = mid-1;
         } 
         else {
            l = mid+1;
         }
      }
      
      cout << ans << endl;
   
   }
   
   
   return 0;
}