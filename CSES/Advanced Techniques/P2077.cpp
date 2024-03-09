#include <bits/stdc++.h>
//find articulation points
using namespace std;


int n; // number of nodes
vector<vector<int>> adj; // adjacency list of graph

vector<bool> visited;
vector<int> tin, low;
int timer;

unordered_set<int> answer;

void dfs(int v, int p = -1) {
    visited[v] = true;
    tin[v] = low[v] = timer++;
    int children=0;
    for (int to : adj[v]) {
        if (to == p) continue;
        if (visited[to]) {
            low[v] = min(low[v], tin[to]);
        } else {
            dfs(to, v);
            low[v] = min(low[v], low[to]);
            if (low[to] >= tin[v] && p!=-1)
                answer.insert(v);
            ++children;
        }
    }
    if(p == -1 && children > 1)
        answer.insert(v);
}

void find_cutpoints() {
    timer = 0;
    visited.assign(n, false);
    tin.assign(n, -1);
    low.assign(n, -1);
    
    dfs(0);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int m;
   cin >> n >> m;
   
   adj = vector<vector<int>>(n,vector<int>());
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b, a--,b--;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   visited = vector<bool>(n, false);
   tin = vector<int>(n, -1);
   low = vector<int>(n, -1);
   
   find_cutpoints();
   
   cout << answer.size() << endl;
   for(int i : answer){
      cout << i+1 << " ";
   }
   
   
   return 0;
}