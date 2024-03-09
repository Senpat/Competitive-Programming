#include <bits/stdc++.h>
//wrong greedy
using namespace std;

vector<vector<int>> adj;
//lca
const int N = 200005;
const int D = 18;
int n = INT_MIN;
int jump[N][D];
int depth[N];

int in[N];
int t;

void initdfs(int v, int p){
   in[v] = t;
   t++;
   for(int nei : adj[v]){
      if(nei == p) continue;
      depth[nei] = depth[v]+1;
      jump[nei][0] = v;
      initdfs(nei,v);
   }
}

void initLCA() {
   for(int d = 1; d < D; d++) {
      for(int i = 1; i <= n; i++) {
         jump[i][d] = jump[jump[i][d-1]][d-1];
      }
   }
}

int getlca(int a, int b){
   if(depth[a] < depth[b]){
      swap(a,b);
   }
   
   for(int i = D-1; i >= 0; i--){
      if (((depth[a]-depth[b]) & (1<<i)) != 0){
         a = jump[a][i];
      }
   }
   if(a==b) return a;
   
   for(int i = D-1; i >= 0; i--){
      if(jump[a][i] != jump[b][i]){
         a = jump[a][i];
         b = jump[b][i];
      }
   }
   return jump[a][0];
}

int dist(int a, int b){
   return depth[a] + depth[b] - 2 * depth[getlca(a,b)];
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int d;
   cin >> n >> d;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   vector<int> degree(n+1,0);
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
      deg[a]++;
      deg[b]++;
   }
   
   t = 0;
   initdfs(1,-1);
   
   auto cmp = [&](int a, int b){
      return in[a] < in[b];
   };
   
   initLCA();
   
   vector<bool> seen(n+1,false);
   vector<int> d1;
   for(int k = 1; k <= n; k++){
      if(deg[k] == 1) d1.push_back(k);
   }
   
   vector<int> answer;
   while(!d1.empty()){
      sort(d1.begin(),d1.end(),cmp);
      
      answer.push_back(d1[0]);
      seen[d1[0]] = true;
      queue<int> q;
      q.push(d1[0]);
      
      int prev = d1[0];
      for(int k = 1; k < d1.size(); k++){
         if(dist(d1[k],prev) >= d){
            prev = d1[k];
            q.push(d1[k]);
            seen[d1[k]] = true;
         }
      }
      
      
   }
   
   return 0;
}