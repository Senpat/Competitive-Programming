#include <bits/stdc++.h>
//stupid matching sol
using namespace std;

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



vector<vector<int>> adj;
vector<int> color;

void dfs(int v, int p){
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      color[nei] = 3-color[v];
      dfs(nei,v);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   color = vector<int>(n+1,0);
   color[1] = 1;
   dfs(1,-1);
   
   int source = n+1;
   int sink = n+2;
   MaxFlow flow(n+2,source,sink);
   
   for(int k = 1; k <= n; k++){
      if(color[k] == 1){
         flow.addEdge(source,k,1LL);
         for(int nei : adj[k]){
            flow.addEdge(k,nei,1LL);
         }
      } else {
         flow.addEdge(k,sink,1LL);
      }
   }
   
   long long ret = flow.mfmc();
   cout << ret << endl;
   
   return 0;
}