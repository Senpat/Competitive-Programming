#include <bits/stdc++.h>
//https://codeforces.com/blog/entry/53170
using namespace std;

vector<vector<int>> adj;
vector<int> sz;
vector<int> parent;
vector<vector<int>> pos;         //in at pos[v][0], out at pos[v][1]
//subtree queries are [in[v], out[v]), path queries are [in[head[v]], in[v]]
int curpos;
vector<int> head;

void dfs_sz(int v, int p){
   //remove parent
   //cout << "sz " << v << endl;
   parent[v] = p;
   if(p != -1) adj[v].erase(find(adj[v].begin(),adj[v].end(),p));
   
   sz[v] = 0;
   
   for(int &nei : adj[v]){
      dfs_sz(nei,v);
      sz[v] += sz[nei];
      if(sz[nei] > sz[adj[v][0]]){
         swap(nei,adj[v][0]);
      }
   }
}

void dfs_hld(int v){
   //cout << "hld " << v << endl;
   pos[v][0] = curpos++;
   
   for(int nei : adj[v]){
      head[nei] = (nei == adj[v][0] ? head[v] : nei);
      dfs_hld(nei);
   }
   
   pos[v][1] = curpos;
}

//fenwick tree
vector<long long> bits;

void update(int i, long long x){
   for(; i < bits.size(); i += i&-i){
      bits[i] += x;
   }
}

long long psum(int i){
   long long cursum = 0LL;
   for(; i > 0; i -= i&-i){
      cursum += bits[i];
   }
   return cursum;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   vector<long long> values(n+1);
   for(int k = 1; k <= n; k++){
      cin >> values[k];
   }
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   parent = vector<int>(n+1);
   sz = vector<int>(n+1);
   dfs_sz(1,-1);
   
   pos = vector<vector<int>>(n+1,vector<int>(2));
   curpos = 1;
   head = vector<int>(n+1);
   head[1] = 1;
   dfs_hld(1);
   
   bits = vector<long long>(curpos+1,0LL);
   
   for(int k = 1; k <= n; k++){
      update(pos[k][0],values[k]);
   }
   
   for(int t = 0; t < q; t++){
      int qt,i;
      cin >> qt >> i;
      
      if(qt == 1){
         long long x;
         cin >> x;
         
         update(pos[i][0],x-values[i]);
         values[i] = x;
      } else {
         //path from i to 1
         
         long long answer = 0LL;
         while(i != -1){
            //cout << i << endl;
            answer += psum(pos[i][0]) - psum(pos[head[i]][0]-1);
            i = parent[head[i]];
         }
         
         cout << answer << "\n";
         
         
      }
   }
   
   
   return 0;
}