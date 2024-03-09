#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;

vector<vector<int>> euler;
int ei;

void dfs(int v, int p){
   euler[v][0] = ei++;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs(nei,v);
   }
   
   euler[v][1] = ei++;
}

vector<long long> bits;

void update(int i, long long x){
   for(; i < ei; i += i&-i){
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
   
   adj = vector<vector<int>>(n+1,vector<int>());
   
   vector<long long> values(n+1);
   for(int k = 1; k <= n; k++){
      cin >> values[k];
   }
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   euler = vector<vector<int>>(n+1,vector<int>(2));
   ei = 1;
   
   dfs(1,-1);
   
   bits = vector<long long>(ei);
   
   for(int k = 1; k <= n; k++){
      update(euler[k][0],values[k]);
   }
   
   for(int t = 0; t < q; t++){
      int qt,i;
      cin >> qt >> i;
      
      if(qt == 1){
         long long x;
         cin >> x;
         
         update(euler[i][0],x-values[i]);
         values[i] = x;
      } else {
         long long answer = psum(euler[i][1]) - psum(euler[i][0]-1);
         cout << answer << endl;
      }
   }
   
   
   
   
   return 0;
}