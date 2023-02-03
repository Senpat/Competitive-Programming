#include <bits/stdc++.h>

using namespace std;

int n;

vector<char> chars;

vector<vector<int>> adj;

vector<vector<int>> dp;

//lca stuff
int MAXD = 13;
vector<vector<int>> lca;
vector<int> depth;

void initLCA() {
   for(int d = 1; d < MAXD; d++) {
      for(int i = 0; i < n; i++) {
         lca[i][d] = lca[lca[i][d-1]][d-1];
      }
   }
}

int get_lca(int a, int b){
      
   if(depth[a] < depth[b]){
      //swap a and b
      int temp = a;
      a = b;
      b = temp;
   }
      
   for(int i = MAXD-1; i >= 0; i--){
      if (((depth[a]-depth[b]) & (1<<i)) != 0){
         a = lca[a][i];
      }
   }
   if(a==b) 
      return a;
      
   for(int i = MAXD-1; i >= 0; i--){
      if(lca[a][i] != lca[b][i]){
         a = lca[a][i];
         b = lca[b][i];
      }
   }
   return lca[a][0];
}

int dist(int a, int b){
   return depth[a] + depth[b] - 2*depth[get_lca(a,b)];
}

void initdfs(int v, int p){
      
   for(int nei : adj[v]){
      if(nei == p) 
         continue;
      depth[nei] = depth[v]+1;
      lca[nei][0] = v;
      initdfs(nei,v);
   }
}

struct state {
   int len;
   int a;
   int b;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int qq = 0; qq < t; qq++){
      cin >> n;
      
      string s;
      cin >> s;
      chars = vector<char>(n);
      for(int k = 0; k < n; k++) chars[k] = s.at(k);
      
      adj = vector<vector<int>>(n,vector<int>());
      for(int k = 0; k < n-1; k++){
         int a,b;
         cin >> a >> b;
         a--;
         b--;
         
         adj[a].push_back(b);
         adj[b].push_back(a);
      }
      
      lca = vector<vector<int>>(n,vector<int>(MAXD));
      depth = vector<int>(n);
      depth[0] = 0;
      initdfs(0,-1);
      initLCA();
      
      dp = vector<vector<int>>(n,vector<int>(n,-1));
      
      queue<state> q;
      
      //size 1
      for(int k = 0; k < n; k++){
         dp[k][k] = 1;
         q.push({0,k,k});
      }
      
      int answer = 1;
         
      //size 2
      for(int k = 0; k < n; k++){
         for(int nei : adj[k]){
            if(chars[k] == chars[nei] && k < nei){
               dp[k][nei] = 2;
               dp[nei][k] = 2;
               answer = 2;
               q.push({1,k,nei});
            }
         }
      }
      
      while(!q.empty()){
         auto s = q.front();
         q.pop();
         int a = s.a;
         int b = s.b;
            
         int curdist = s.len;
            
         int ap = -1;
         int bp = -1;
         for(int nei : adj[a]){
            if(ap == -1 && dist(nei,b) < curdist){
               ap = nei;
            } 
            else {
               if(dp[nei][b] == -1) q.push({curdist+1,nei,b});
               if(dp[a][b] > dp[nei][b]){
                  dp[nei][b] = dp[a][b];
                  dp[b][nei] = dp[a][b];
               }
            }
         }
            
         for(int nei : adj[b]){
            if(bp == -1 && dist(nei,a) < curdist){
               bp = nei;
            } 
            else {
               if(dp[a][nei] == -1) q.push({curdist+1,a,nei});
               if(dp[a][b] > dp[a][nei]){
                  dp[a][nei] = dp[a][b];
                  dp[nei][a] = dp[a][b];
               }
            }
         }
            
         for(int na : adj[a]){
            if(ap == na) continue;
            for(int nb : adj[b]){
               if(bp == nb || na == nb) 
                  continue;
                  
               if(chars[na] == chars[nb]){
                  if(dp[na][nb] == -1) q.push({curdist+2,na,nb});
                  if(dp[a][b] + 2 > dp[na][nb]){
                     dp[na][nb] = dp[a][b]+2;
                     dp[nb][na] = dp[a][b]+2;
                     answer = max(answer,dp[na][nb]);
                  }
                     
               }
            }
         }
      }
      
      cout << answer << endl;
      
   }
   
   
   return 0;
}