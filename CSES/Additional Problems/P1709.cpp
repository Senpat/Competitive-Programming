#include <bits/stdc++.h>

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
      if(curr == sink) 
         return bottleneck;
      
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

vector<bool> reachable;
void dfs(int v, const vector<vector<FlowEdge*>>& adj){
   reachable[v] = true;
   
   for(auto fe : adj[v]){
      //if(fe->isres) continue;
      if(reachable[fe->to]) continue;
      if(fe->flow == fe->capacity) continue;
      
      //only use edges that are not at full capacity
      dfs(fe->to,adj);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   //[1,n] is row, [n+1,2*n] is col
   int source = 2*n+1;
   int sink = 2*n+2;
   MaxFlow flow(2*n+2,source,sink);
   for(int k = 1; k <= n; k++){
      flow.addEdge(source,k,1LL);
      flow.addEdge(k+n,sink,1LL);
   }
   
   vector<vector<char>> board(n,vector<char>(n));
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < n; j++){
         board[k][j] = s[j];
         if(board[k][j] == 'o'){
            flow.addEdge(k+1,j+1+n,LLONG_MAX/2LL);
         }
      }
   }
   
   long long ret = flow.mfmc();
   
   //find out which ones to cut
   reachable = vector<bool>(2*n+3,false);
   dfs(source,flow.edges);
   
   vector<pair<int,int>> answer;
   for(int k = 1; k <= 2*n+2; k++){
      for(auto fe : flow.edges[k]){
         if(!fe->isres && reachable[fe->from] && !reachable[fe->to]){
            if(fe->to <= n){
               //row
               answer.push_back(make_pair(1,fe->to));
            } else {
               //col
               answer.push_back(make_pair(2,fe->from-n));
            }
         }
      }
   }
   
   cout << answer.size() << "\n";
   for(auto [a,b] : answer){
      cout << a << " " << b << "\n";
   }
   
   
   return 0;
}