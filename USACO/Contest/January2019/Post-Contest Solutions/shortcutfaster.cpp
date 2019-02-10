//if you have any questions, you can pm me on codeforces (username: golions)
//Uses O(N) backtracking
#include <bits/stdc++.h>

using namespace std;

#define MAXN 10005

struct State{
   int v;                                 //vertex
   long long w;                           //current distance
};
//custom comparator for State
struct CompareState{
   bool operator()(State s1, State s2){
      return s1.w > s2.w;
   }
};

struct Edge{
   int to;                                //other vertex
   int w;                                 //weight
};



int par[MAXN];
long long c[MAXN];
long long djik[MAXN];                           //shortest distance from vertex 1
long long nums[MAXN];                           //number of cows that pass through that vertex

vector<vector<int>> spadj(MAXN);                //edge list for the shortest path tree

//use a dfs to fill nums (the array storing the number of cows that pass through that field
void dfs(int v, int p){
   long long sum = c[v];
   
   for(auto nei : spadj[v]){
      if(nei == p) continue;
      dfs(nei,v);
      sum += nums[nei];
   }
   
   nums[v] = sum;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   ifstream fin ("shortcut.in");
   ofstream fout ("shortcut.out");
   
   int n,m;
   long t;
   fin >> n >> m >> t;
   
   for(int k = 1; k <= n; k++){
      fin >> c[k];
   }
   
   //build edge list for every vertex
   vector<vector<Edge>> adj(n+1);
   
   for(int k = 0; k < m; k++){
      int a,b;
      long w;
      
      fin >> a >> b >> w;
      
      Edge ea {b,w};
      Edge eb {a,w};
      
      adj[a].push_back(ea);
      adj[b].push_back(eb);
   }
   
   //basic djikstra's algorithm while storing parents
   fill(begin(djik),end(djik),LONG_MAX);
   djik[1] = 0;
   
   fill(begin(par),end(par),-1);
   
   priority_queue<State,vector<State>,CompareState> pq;
   State s {1,0};
   pq.push(s);
   
   unordered_set<int> seen;
   
   seen.insert(1);
   
   while(!pq.empty()){
      State cur = pq.top();
      pq.pop();
      
      int u = cur.v;
      
      seen.insert(u);
      
      for(Edge e : adj[u]){
         int v = e.to;
         if(seen.find(v) != seen.end()) continue;
         long long newdis = djik[u] + e.w;
         if(newdis < djik[v]){
            djik[v] = newdis;
            par[v] = u;
            State next {v,newdis};
            pq.push(next);
         } else if(newdis == djik[v]){                      //ensures lexicographically shortest path
            if(u < par[v]){
               djik[v] = newdis;
               par[v] = u;
               State next {v,newdis};
               pq.push(next);
            }
         }
      }
   }
   
   //backtrack to fill nums
   
   for(int k = 2; k <= n; k++){
      //construct edge list using the parent array
      spadj[k].push_back(par[k]);
      spadj[par[k]].push_back(k);
   }
   
   dfs(1,-1);

   
   long long answer = 0;
   for(int k = 1; k <= n; k++){
      //nums[k] * (djik[k] - t) is the distance saved
      answer = max(answer,nums[k]*(djik[k]-t));
   }
   
   cout << answer << endl;
   fout << answer << endl;
      
      
   
   return 0;
}