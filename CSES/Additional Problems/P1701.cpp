#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;
vector<long long> rands;

void dfs_size(int v, int p, const vector<vector<int>>& adj, vector<int>& subsize){
   subsize[v] = 1;
   for(int nei : adj[v]){
      if(nei == p) 
         continue;
      dfs_size(nei,v,adj,subsize);
      subsize[v] += subsize[nei];
   }
}

int dfs_centroid(int v, int p, int n, const vector<vector<int>>& adj, const vector<int>& subsize){
   
   for(int nei : adj[v]){
      if(nei == p) 
         continue;
      if(subsize[nei] > n/2){
         return dfs_centroid(nei,v,n,adj,subsize);
      }
   }
   
   return v;
}

void getdepth(int v, int p, const vector<vector<int>>& adj, vector<int>& depth){
   depth[v] = 0;
   for(int nei : adj[v]){
      if(nei == p) continue;
      getdepth(nei,v,adj,depth);
      depth[v] = max(depth[v],depth[nei]+1);
   }
}

long long treehash(int v, int p, const vector<vector<int>>& adj, const vector<int>& depth){
   
   long long ret = 1LL;
   for(int nei : adj[v]){
      if(nei == p) continue;
      long long curhash = treehash(nei,v,adj,depth);
      ret = (ret * (curhash + rands[depth[v]]) + MOD)%MOD;
   }
   
   return ret;
   
}

long long gethash(int root, const vector<vector<int>>& adj){
   vector<int> depth(adj.size());
   getdepth(root,-1,adj,depth);
   
   return treehash(root,-1,adj,depth);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   mt19937_64 rng(chrono::steady_clock::now().time_since_epoch().count());
   
   int N = 100005;
   for(int k = 0; k < N; k++){
      rands.push_back(rng()%MOD);
   }

   
   int t;
   cin >> t;
   for(int q = 1; q <= t; q++){
      int n;
      cin >> n;
      
      vector<vector<int>> adj1(n+1,vector<int>());
      vector<vector<int>> adj2(n+1,vector<int>());
      
      for(int k = 0; k < n-1; k++){
         int a,b;
         cin >> a >> b;
         adj1[a].push_back(b);
         adj1[b].push_back(a);
      }
      
      for(int k = 0; k < n-1; k++){
         int a,b;
         cin >> a >> b;
         adj2[a].push_back(b);
         adj2[b].push_back(a);
      }
      
      vector<int> subsize1(n+1);
      vector<int> subsize2(n+1);
      
      dfs_size(1,-1,adj1,subsize1);
      dfs_size(1,-1,adj2,subsize2);
      
      int centroid1 = dfs_centroid(1,-1,n,adj1,subsize1);
      
      //max 2 centroids
      vector<int> centroids;
      for(int v = 1; v <= n; v++){
         int above = n-1;
         bool fail = false;
         for(int nei : adj2[v]){
            if(subsize2[nei] > subsize2[v]) 
               continue;
            above -= subsize2[nei];
            if(subsize2[nei] > n/2){
               fail = true;
            }
         }
         
         if(!fail && above <= n/2){
            centroids.push_back(v);
         }
      }
      
      long long hash1 = gethash(centroid1,adj1);
      //cout << "c1 " << centroid1 << endl;
      bool found = false;
      for(int c2 : centroids){
         //cout << "c2 " << c2 << endl;
         long long hash2 = gethash(c2,adj2);
         
         if(hash1 == hash2){
            found = true;
            break;
         }
      }
      
      if(found){
         cout << "YES\n";
      } else {
         cout << "NO\n";
      }
      
      
   }
   
   
   return 0;
}