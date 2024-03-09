#include <bits/stdc++.h>
//dumb bug: color array size n instead of n+1
//try some speedups
//TLES: map faster than gp_hash_table (instantiation?) https://atcoder.jp/contests/abc340/submissions/50191522
#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;

/*
const int RANDOM = std::chrono::high_resolution_clock::now().time_since_epoch().count();
struct chash {
    int operator()(int x) const { return x ^ RANDOM; }
};*/
unsigned hash_f(unsigned x) {
    x = ((x >> 16) ^ x) * 0x45d9f3b;
    x = ((x >> 16) ^ x) * 0x45d9f3b;
    x = (x >> 16) ^ x;
    return x;
}
struct chash {
    int operator()(int x) const { return hash_f(x); }
};
using gp_map = gp_hash_table<int, long long, chash>;

using namespace std;

const long long MOD = 998244353LL;

vector<int> color;
vector<vector<int>> adj;

long long answer = 0LL;

vector<bool> seen;
//overridden a lot
vector<int> subsize;
vector<long long> muls;
vector<long long> sums;

void dfs_size(int v, int p){
   subsize[v] = 1;
   
   for(int nei : adj[v]){
      if(nei == p || seen[nei]) continue;
      dfs_size(nei,v);
      subsize[v] += subsize[nei];
   }
}

int dfs_cent(int v, int p, int thresh){
   
   for(int nei : adj[v]){
      if(nei == p || seen[nei]) continue;
      if(subsize[nei] > thresh){
         return dfs_cent(nei,v,thresh);
      }
   }
   
   return v;
}

//mapping from color to # of ways to pick that color in this subtree (including nothing)
//values not in map are 1
gp_map dfs(int v, int p){
   
   gp_map ret;
   
   for(int nei : adj[v]){
      if(nei == p || seen[nei]) continue;
      auto cur = dfs(nei,v);
      if(cur.size() > ret.size()){
         swap(cur,ret);
      }
      
      for(auto [key,mul] : cur){
         if(ret.find(key) == ret.end()) ret[key] = mul;
         else ret[key] = (ret[key] * mul + MOD)%MOD;
      }
   }
   
   //add itself
   if(ret.find(color[v]) == ret.end()) ret[color[v]] = 2LL;
   else{
      ret[color[v]]++;
      if(ret[color[v]] >= MOD) ret[color[v]]-=MOD;
   }
   
   return ret;
         
}

void dothing(int v, int p){
   dfs_size(v,p);
   
   int centroid = dfs_cent(v,p,subsize[v]/2);
   //cout << "centroid: " << centroid << endl;
   gp_map all;
   gp_map multiple;
   for(int nei : adj[centroid]){
      if(seen[nei]) continue;
      auto ret = dfs(nei,centroid);
      
      for(auto [key,mul] : ret){
         //cout << "key " << key << endl;
         muls[key] = (muls[key] * mul + MOD)%MOD;
         //sum of non-empty
         sums[key] += mul-1LL;
         if(sums[key] >= MOD) sums[key] -= MOD;
         if(all.find(key) != all.end()) multiple[key] = 0LL;
         else all[key] = 0LL;
      }
      
   }
   for(auto [c,_] : multiple){
      //cout << c << " " << muls[c] << " " << sums[c] << " " << endl;
      if(c != color[centroid]){
         //can't select 0 or 1
         long long add = muls[c] - sums[c] - 1LL;
         if(add < 0) add += MOD;
         
         answer += add;
         if(answer >= MOD) answer -= MOD;
      }
   }
   
   //add centroid's color (can select any #)
   //cout << "self: " << muls[color[centroid]] << endl;
   answer += muls[color[centroid]];
   if(answer >= MOD) answer -= MOD;
   
   //reset
   for(auto [c,_] : all){
      muls[c] = 1LL;
      sums[c] = 0LL;
   }
   
   seen[centroid] = true;
   for(int nei : adj[centroid]){
      if(nei == p || seen[nei]) continue;
      dothing(nei,centroid);
   }
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   color = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> color[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   answer = 0LL;
   
   seen = vector<bool>(n+1,false);
   subsize = vector<int>(n+1);
   muls = vector<long long>(n+1,1LL);
   sums = vector<long long>(n+1,0LL);
   dothing(1,-1);
   
   cout << answer << endl;
   
   return 0;
}