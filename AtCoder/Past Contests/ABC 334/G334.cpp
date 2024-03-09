#include <bits/stdc++.h>

using namespace std;

const long long MOD = 998244353LL;

long long exp(long long base, long long power){
   if(base >= MOD) base = (base + MOD)%MOD;        //just in case
   
   if(power == 0LL) 
      return 1LL;
   if(power == 1LL) 
      return base;
   long long ans = exp(base,power/2LL);
   ans = (ans*ans + MOD) % MOD;
   if(power%2LL == 1LL) ans = (ans*base + MOD) % MOD;
   return ans;
}

int n,m;

int to(int x, int y){
   return x*m+y;
}

void add(long long& a, long long b){
   a += b;
   if(a >= MOD) a -= MOD;
}

vector<vector<int>> adj;

vector<bool> seen;
vector<int> disc;
vector<int> low;
vector<long long> split;
int t;

void dfs(int v, int p){
   seen[v] = true;
   disc[v] = t;
   t++;
   
   long long children = 0LL;
   for(int nei : adj[v]){
      if(nei == p) 
         continue;
      if(seen[nei]){
         low[v] = min(low[v],disc[nei]);
      } 
      else {
         children++;
         dfs(nei,v);
            
         low[v] = min(low[v],low[nei]);
            
         if(low[nei] >= disc[v]){
            split[v]++;
         }
            
      }
   }
   
   //special case: root
   if(p == -1){
      split[v] = children-1;
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   int N = n*m;
   vector<vector<char>> board(n,vector<char>(m));
   
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j];
      }
   }
   
   adj = vector<vector<int>>(N,vector<int>());
   
   vector<bool> green(N,false);
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         if(board[k][j] != '#') 
            continue;
         int v = to(k,j);
         green[v] = true;
         if(k+1 < n && board[k+1][j] == '#'){
            int v2 = to(k+1,j);
            adj[v].push_back(v2);
            adj[v2].push_back(v);
         }
         if(j+1 < m && board[k][j+1] == '#'){
            int v2 = to(k,j+1);
            adj[v].push_back(v2);
            adj[v2].push_back(v);
         }
      }
   }
   
   //find articulation points
   //https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
   seen = vector<bool>(N,false);
   disc = vector<int>(N,0);
   low = vector<int>(N,INT_MAX);
   split = vector<long long>(N,0LL);
   
   long long numcomp = 0L;
   
   for(int k = 0; k < N; k++){
      if(!green[k] || seen[k]) 
         continue;
      t = 0;
      dfs(k,-1);
      numcomp++;
   }
   
   long long numer = 0L;
   long long denom = 0L;
      
   for(int k = 0; k < N; k++){
      if(green[k]){
         denom++;
         add(numer,numcomp);
         add(numer,split[k]);
      }
   }
      
   long long answer = (numer * exp(denom,MOD-2) + MOD)%MOD;
   cout << answer << endl;
   
   return 0;
}