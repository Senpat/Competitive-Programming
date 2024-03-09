#include <bits/stdc++.h>
//optimize using vector instead of map
//not much faster, still need dumb optimizations
using namespace std;

vector<vector<int>> adj;

int m;
long long answer;

vector<bool> marked;       //in separate centroid component
vector<int> subsize;
void dfs_size(int v, int p){
   subsize[v] = 1;
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs_size(nei,v);
      subsize[v] += subsize[nei];
   }
}

int centroid;
void dfs_cent(int v, int p, int sz){
   
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      if(subsize[nei] > sz/2){
         return dfs_cent(nei,v,sz);
      }
   }
   
   centroid = v;
}

void dfs_dist(int v, int p, int d, vector<long long>& curvec){
   curvec[d]++;
   
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs_dist(nei,v,d+1,curvec);
   }
   
}

void solve(int v){
   dfs_size(v,-1);
   //dumb optimization
   if(subsize[v] <= m) return;
   
   
   int sz = subsize[v];
   centroid = -1;
   dfs_cent(v,-1,sz);
   
   int cur = centroid;
   marked[cur] = true;
   
   dfs_size(cur,-1);
   
   vector<long long> master(sz+1,0LL);
   master[0] = 1LL;
   
   for(int nei : adj[cur]){
      if(marked[nei]) continue;
      vector<long long> curvec(subsize[nei]+1,0LL);
      dfs_dist(nei,cur,1,curvec);
      
      for(int k = 0; k <= subsize[nei]; k++){
         if(m-k >= 0 && m-k <= sz){
            answer += master[m-k] * curvec[k];
         }
      }
      
      for(int k = 0; k <= subsize[nei]; k++){
         master[k] += curvec[k];
      }
   }
   
   for(int nei : adj[cur]){
      if(!marked[nei]){
         solve(nei);
      }
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n >> m;
   
   
   if(m == 1){
      cout << n-1 << endl;
      return 0;
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   answer = 0LL;
   marked = vector<bool>(n+1,false);
   subsize = vector<int>(n+1);            //subsize is overwritten a lot
   solve(1);
   
   cout << answer << endl;
   
   
   return 0;
}