#include <bits/stdc++.h>
//small to large merging sol
using namespace std;

int m1,m2;

long long answer;
vector<vector<int>> adj;
vector<int> subsize;

void dfs_size(int v, int p){
   subsize[v] = 1;
   //remove parent
   if(p != -1){
      adj[v].erase(find(adj[v].begin(),adj[v].end(),p));
   }
   
   for(int nei : adj[v]){
      dfs_size(nei,v);
      subsize[v] += subsize[nei];
   }
}

void dfs_hld(int v){
   for(int& nei : adj[v]){
      dfs_hld(nei);
      if(subsize[nei] > subsize[adj[v][0]]){
         swap(nei,adj[v][0]);
      }
   }
}

long long calc(const vector<long long>& ret, const vector<long long>& cur, int x){
   if(x == 0) return 0LL;
   
   long long ans = 0LL;
   
   //account for 1 extra edge from child to current vertex
   x--;
   
   long long psum = 0LL;
   int r = min(x,(int)cur.size()-1);
   int l = 0;
   while(l < ret.size() && l+r <= x){
      psum += ret[l];
      l++;
   }
   ans += psum * cur[r];
   //l+r = x, decrease r and increase l
   //l++;      //already added
   r--;
   while(l < ret.size() && r >= 0){
      psum += ret[l];
      ans += psum * cur[r];
      l++;
      r--;
   }
   
   //do rest of cur
   while(r >= 0){
      ans += psum * cur[r];
      r--;
   }
   
   return ans;
}

void merge(vector<long long>& ret, const vector<long long>& cur){
   //add cur[x] to ret[x+1]
   while(ret.size() < cur.size()+1){
      ret.push_back(0LL);
   }
   
   for(int k = 0; k < cur.size(); k++){
      ret[k+1] += cur[k];
   }
}

//return vector, ret[x] stores # of nodes in subtree that are x away from root
vector<long long> dfs(int v, int p){
   
   vector<long long> ret(1,1LL);
   for(int nei : adj[v]){
      if(nei == p) continue;
      auto cur = dfs(nei,v);
      
      //ret is always bigger because hld order
      
      //cout << v << " " << nei << " " << calc(ret,cur,m2) << endl;
      answer += calc(ret,cur,m2) - calc(ret,cur,m1-1);
      
      merge(ret,cur);
   }
   
   return ret;
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
   
   subsize = vector<int>(n+1);
   dfs_size(1,-1);
   
   dfs_hld(1);
   
   answer = 0LL;
   dfs(1,-1);
   
   cout << answer << endl;
   
   
   return 0;
}