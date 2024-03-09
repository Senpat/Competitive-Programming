#include <bits/stdc++.h>
//centroid decomp attempt, calc pairs then subtract paris within subtree
using namespace std;

int m1,m2;

vector<vector<int>> adj;
vector<bool> marked;

long long answer = 0LL;

vector<int> subsize;       //gets overwritten a lot

void dfs_size(int v, int p){
   subsize[v] = 1;
   
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs_size(nei,v);
      subsize[v] += subsize[nei];
   }
}

int dfs_centroid(int v, int p, int sz){
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      if(subsize[nei] > sz/2){
         return dfs_centroid(nei,v,sz);
      }
   }
   
   return v;
}

void dfs_depth(int v, int p, vector<long long>& curdepth, int d){
   while(curdepth.size() <= d) curdepth.push_back(0LL);
   curdepth[d]++;
   
   for(int nei : adj[v]){
      if(nei == p || marked[nei]) continue;
      dfs_depth(nei,v,curdepth,d+1);
   }
}

string printarray(const vector<long long>& array){
   string s = "[";
   for(long long i : array){
      s += to_string(i) + " ";
   }
   s += "]";
   return s;
}

//counts the # of pairs such that the sum of indeces are < x
long long calc(const vector<long long>& array, int x){
   long long ret = 0LL;
   
   long long psum = 0LL;
   int l = 0;
   int r = min(x,(int)array.size()-1);
   while(l < array.size() && l+r <= x){
      psum += array[l];
      l++;
   }
   
   ret += psum * array[r];
   r--;
   
   while(l < array.size() && r >= 0){
      psum += array[l];
      ret += psum * array[r];
      l++;
      r--;
   }
   
   cout << printarray(array) << " " << x << ": " << ret << endl;
   return ret;
   
}

void solve(int v){
   //get subtree sizes
   dfs_size(v,-1);
   
   int centroid = dfs_centroid(v,-1,subsize[v]);
   marked[centroid] = true;
   
   vector<long long> alldepth(1,1LL);
   for(int nei : adj[centroid]){
      if(marked[nei]) continue;
      vector<long long> curdepth;
      dfs_depth(nei,centroid,curdepth,0);
      //cout << centroid << " " << nei << " " << printarray(curdepth) << endl;
      //within subtree
      answer -= calc(curdepth,m2) - calc(curdepth,m1-1);
      
      while(alldepth.size() < curdepth.size()+1) alldepth.push_back(0LL);
      for(int k = 0; k < curdepth.size(); k++){
         alldepth[k+1] += curdepth[k];
      }
   }
   
   answer += calc(alldepth,m2) - calc(alldepth,m1-1);
   
   for(int nei : adj[centroid]){
      if(marked[nei]) continue;
      solve(nei);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n >> m1 >> m2;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   marked = vector<bool>(n+1,false);
   subsize = vector<int>(n+1);
   solve(1);
   
   cout << answer << endl;
   
   return 0;
}