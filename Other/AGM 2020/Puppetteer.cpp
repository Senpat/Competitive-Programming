#include <bits/stdc++.h>

using namespace std;

int n;
const int MAXN = 250005;
int arr[MAXN];
int iof[MAXN];
vector<int> adj[MAXN];
int sums[MAXN];
int answer[MAXN];

   //lca and dist stuff
const int MAXD = 18;
int lca[MAXN][MAXD];
int depth[MAXN];
   
void initLCA() {
   for(int d = 1; d < MAXD; d++) {
      for(int i = 0; i < n+1; i++) {
         lca[i][d] = lca[lca[i][d-1]][d-1];
      }
   }
}
   
int lcam(int a, int b){
      
   if(depth[a] < depth[b]){
         //swap a and b
      int temp = a;
      a = b;
      b = temp;
   }
      
   for(int i = MAXD-1; i >= 0; i--){
      if (((depth[a]-depth[b]) & (1<<i)) != 0){
         //if(depth[a] - (1<<i) > depth[b]){
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
      //System.out.println("lca: " + lca(a,b));
   return depth[a] + depth[b] - 2*depth[lcam(a,b)];
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
      


void dfs(int v, int p){
   answer[v] += sums[v];
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      
      dfs(nei,v);
      answer[v] += answer[nei];
   }
}









int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   
   
   while(t--){
      cin >> n;
      
      
      
      for(int k = 1; k <= n; k++){
         cin >> arr[k];
         iof[arr[k]] = k;
      }
      
      
      for(int k = 0; k < n-1; k++){
         int a,b;
         cin >> a >> b;
         adj[a].push_back(b);
         adj[b].push_back(a);
      }
      
      
      initdfs(1,-1);
      initLCA();
      
      for(int k = 1; k <= n; k++){
         sums[k] = 1;
      }
      
      for(int k = 1; k < n; k++){
         int cur = lcam(iof[k],iof[k+1]);
         sums[cur]--;
      }
      
      dfs(1,-1);
      
      for(int k = 1; k <= n; k++){
         cout << answer[k] << " ";
      }
      cout << endl;
      
      for(int k = 1; k <= n; k++){
         adj[k].clear();
         depth[k] = 0;
         answer[k] = 0;
      }
      
   } 
   
   
   
   
   return 0;
}