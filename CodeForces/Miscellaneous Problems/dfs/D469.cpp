#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
//also tle
//based off of D469.java
using namespace std;

const int RANDOM = chrono::high_resolution_clock::now().time_since_epoch().count();
struct chash {
    int operator()(int x) const { return x ^ RANDOM; }
};

//dinic from ray
class FlowEdge{
   public:
   int from, to;
   long long flow, capacity;
   FlowEdge* residual;
   bool isres;
   
   FlowEdge(int f, int t, long long cap, bool r): from(f), to(t), flow(0LL), capacity(cap), isres(r){}
   
   long long capacityLeft(){
      return capacity-flow;
   }
   
   void augment(long long val){
      flow += val;
      residual->flow -= val;
   }
};

class MaxFlow{
   //Dinic with optimizations
   public:
   int N, source, sink;
   vector<vector<FlowEdge*>> edges;
   vector<int> depth;
   long long INF = LLONG_MAX/2LL;
   
   MaxFlow(int n, int x, int y): N(n), source(x), sink(y), edges(vector<vector<FlowEdge*>>(n+1,vector<FlowEdge*>())), depth(vector<int>(n+1,-1)){}
   
   void addEdge(int from, int to, long long cap){
      FlowEdge* forward = new FlowEdge(from,to,cap,false);
      FlowEdge* backward = new FlowEdge(to,from,0LL,true);
      forward->residual = backward;
      backward->residual = forward;
      edges[from].push_back(forward);
      edges[to].push_back(backward);
   }
   
   long long mfmc(){
      long long res = 0LL;
      vector<int> magic(N+1,0);
      
      while(assignDepths()){
         long long flow = dfs(source, INF, magic);
         while(flow > 0LL){
            res += flow;
            flow = dfs(source, INF, magic);
         }
         magic = vector<int>(N+1,0);
      }
      
      return res;
      
   }
   
   bool assignDepths(){
      fill(depth.begin(),depth.end(),-1);
      queue<int> q;
      q.push(source);
      depth[source] = 0;
      while(!q.empty()){
         int curr = q.front();
         q.pop();
         
         for(FlowEdge* e : edges[curr]){
            if(e->capacityLeft() > 0LL && depth[e->to] == -1){
               depth[e->to] = depth[curr]+1;
               q.push(e->to);
            }
         }
      
      }
      
      return depth[sink] != -1;
   }
   
   long long dfs(int curr, long long bottleneck, vector<int>& magic){
      if(curr == sink) return bottleneck;
      
      for(; magic[curr] < edges[curr].size(); magic[curr]++){
         FlowEdge* e = edges[curr][magic[curr]];
         if(e->capacityLeft() > 0LL && depth[e->to]-depth[curr] == 1){
            long long val = dfs(e->to, min(bottleneck, e->capacityLeft()), magic);
            if(val > 0LL){
               e->augment(val);
               return val;
            }
         }
         
      }
      
      return 0LL; // no flow
   }
};

int a2i,b2i;
vector<vector<int>> adj;
vector<int> color;

void gencolor(int v){
   
   for(int nei : adj[v]){
      if(color[nei] == 0){
         color[nei] = 3-color[v];
         gencolor(nei);
      }
   }
}

MaxFlow genflow(int n, const gp_hash_table<int,int,chash>& indexof, int a, int b, bool a2, bool b2){
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(auto [x,i] : indexof){
      int othera = a-x;
      auto ai = indexof.find(othera);
      if(othera > x && ai != indexof.end()){
         int other = ai->second;
         adj[i].push_back(other);
         adj[other].push_back(i);
      }
      
      int otherb = b-x;
      auto bi = indexof.find(otherb);
      if(otherb > x && bi != indexof.end()){
         int other = bi->second;
         adj[i].push_back(other);
         adj[other].push_back(i);
      }
   }
   
   //guaranteed to be bipartite because there will be no odd cycles
   color = vector<int>(n+1);
   
   for(int k = 1; k <= n; k++){
      if(color[k] == 0){
         color[k] = 1;
         gencolor(k);
      }
   }
   
   int source = n+1;
   int sink = n+2;
   MaxFlow flow(n+2,source,sink);
   
   for(int k = 1; k <= n; k++){
      if(a2 && k == a2i) continue;
      if(b2 && k == b2i) continue;
      
      if(color[k] == 1){
         flow.addEdge(source,k,1LL);
         for(int nei : adj[k]){
            flow.addEdge(k,nei,1LL);
         }
      } else {
         flow.addEdge(k,sink,1LL);
      }
   }
   
   return flow;
         
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,a,b;
   cin >> n >> a >> b;
   
   vector<int> array(n+1);
   gp_hash_table<int,int,chash> indexof;
   //indexof.reserve(1024);
   //indexof.max_load_factor(0.25);
   for(int k = 1; k <= n; k++){
      cin >> array[k];
      indexof[array[k]] = k;
   }
   
   a2i = -1;
   b2i = -1;
   
   if(a%2 == 0 && indexof.find(a/2) != indexof.end()){
      a2i = indexof[a/2];
   }
   if(b%2 == 0 && indexof.find(b/2) != indexof.end()){
      b2i = indexof[b/2];
   }
   
   for(int ia = 0; ia <= 1; ia++){
   for(int ib = 0; ib <= 1; ib++){
      if((n-ia-ib) % 2 != 0) continue;
      if(ia == 1 && a2i == -1) continue;
      if(ib == 1 && b2i == -1) continue;  
      
      int need = (n-ia-ib)/2;
      
      MaxFlow flow = genflow(n,indexof,a,b,ia==1,ib==1);
      
      long long ret = flow.mfmc();
      
      if(ret == (long long)need){
         vector<int> answer(n+1);
         
         for(int k = 1; k <= n; k++){
            if(ia == 1 && k == a2i){
               answer[k] = 0;
            } else if(ib == 1 && k == b2i){
               answer[k] = 1;
            } else {
               
               for(FlowEdge* e : flow.edges[k]){
                  if(e->isres || e->flow == 0LL || e->to > n) continue;
                  if(array[k] + array[e->to] == a){
                     answer[k] = 0;
                     answer[e->to] = 0;
                  } else {
                     answer[k] = 1;
                     answer[e->to] = 1;
                  }
               }
                    
            }
         }
         
         cout << "YES\n";
         for(int k = 1; k <= n; k++){
            cout << answer[k] << " ";
         }
         return 0;
      }
   }
   }
   
   cout << "NO\n";
   return 0;
}