#include <bits/stdc++.h>

using namespace std;

struct state{
   long long d;
   int v;
   int half;
   
   state(long long a, int b, int c) : d(a), v(b), half(c) {}
};

vector<vector<pair<int,long long>>> adj;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   adj = vector<vector<pair<int,long long>>>(n+1,vector<pair<int,long long>>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      adj[a].push_back(make_pair(b,w));
   }
   
   vector<vector<long long>> dist(n+1,vector<long long>(2,LLONG_MAX));
   
   dist[1][0] = 0LL;
   
   auto compare = [](state a, state b){
      if(a.d == b.d){
         return a.v < b.v;
      }
      return a.d > b.d;
   };
   priority_queue<state,vector<state>,decltype(compare)> pq(compare);
   
   pq.emplace(0LL,1,0);
   
   while(!pq.empty()){
      state s = pq.top();
      pq.pop();
      //cout << s.d << " " << s.v << " " << s.half << endl;
      
      if(dist[s.v][s.half] != s.d) continue;
      
      for(auto e : adj[s.v]){
         //don't half
         long long newd = dist[s.v][s.half] + e.second;
         if(newd < dist[e.first][s.half]){
            dist[e.first][s.half] = newd;
            pq.emplace(newd,e.first,s.half);
         }
         //half
         if(s.half == 0){
            long long newd = dist[s.v][0] + e.second/2LL;
            if(newd < dist[e.first][1]){
               dist[e.first][1] = newd;
               pq.emplace(newd,e.first,1);
            }
         }
      }
   }
   
   long long answer = min(dist[n][0],dist[n][1]);
   cout << answer << endl;
   
   
   
   return 0;
}