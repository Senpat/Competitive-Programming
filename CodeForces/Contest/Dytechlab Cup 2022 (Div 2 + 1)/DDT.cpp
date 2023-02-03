#include <bits/stdc++.h>

using namespace std;

struct State{
   long long d;
   int cur;
   int par;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t = 0;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<vector<pair<int,long long>>> adj(n+1,vector<pair<int,long long>>());
      
      for(int k = 0; k < m; k++){
         int a,b;
         long long w;
         cin >> a >> b >> w;
         
         adj[a].push_back({b,w});
         adj[b].push_back({a,w});
      }
      
      auto compare = [](State lhs, State rhs){
         if(lhs.d == rhs.d && lhs.cur == rhs.cur) return lhs.par < rhs.par;
         if(lhs.d == rhs.d) return lhs.cur < rhs.cur;
         return lhs.d < rhs.d;
         };
      
      vector<vector<long long>> mins(n+1,vector<long long>(n+1,LLONG_MAX));
      vector<vector<long long>> dist(n+1,vector<long long>(n+1,LLONG_MAX));
      
      priority_queue<State,vector<State>,decltype(compare)> pq(compare);
      
      dist[1][1] = 0LL;
      pq.push({0LL,1,1});
      
      while(!pq.empty()){
         State s = pq.top();
         pq.pop();
         
         if(dist[s.cur][s.par] != s.d) continue;
         
         for(const auto& e : adj[s.cur]){
            if(mins[s.cur][s.par] < e.second){
               long long newdist = dist[s.cur][s.par] + mins[s.cur][s.par];
               if(newdist < dist[e.first][s.par]){
                  dist[e.first][s.par] = newdist;
                  mins[s.cur][s.par];
                  pq.push({newdist,e.first,s.par});
               }
               
            } else {
               long long newdist = dist[s.cur][s.par] + e.second;
               if(newdist < dist[e.first][e.first]){
                  dist[e.first][e.first] = newdist;
                  mins[e.first][e.first] = e.second;
                  pq.push({newdist,e.first,e.first});
               }
            }
         }
      }
      
      long long answer = LLONG_MAX;
      for(int k = 1; k <= n; k++){
         answer = min(answer,dist[n][k]);
      }
      
      cout << answer << "\n";
      
      
   }
   
   
   return 0;
}