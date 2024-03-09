#include <bits/stdc++.h>

using namespace std;

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;

const int RANDOM = chrono::high_resolution_clock::now().time_since_epoch().count();
struct chash {
    int operator()(int x) const { return x ^ RANDOM; }
};

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

void dfs_dist(int v, int p, int d, gp_hash_table<int,long long,chash>& curmap){
   curmap[d]++;
   
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs_dist(nei,v,d+1,curmap);
   }
   
}

void solve(int v){
   dfs_size(v,-1);
   if(subsize[v] <= m) return;
   
   centroid = -1;
   dfs_cent(v,-1,subsize[v]);
   
   int cur = centroid;
   marked[cur] = true;
   
   gp_hash_table<int,long long,chash> master;
   master[0] = 1LL;
   
   for(int nei : adj[cur]){
      if(marked[nei]) continue;
      gp_hash_table<int,long long,chash> curmap;
      dfs_dist(nei,cur,1,curmap);
      
      for(auto [d,freq] : curmap){
         if(master.find(m-d) != master.end()){
            answer += master[m-d] * freq;
         }
      }
      
      for(auto [d,freq] : curmap){
         master[d] += freq;
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