#include <bits/stdc++.h>

using namespace std;

int n;
vector<vector<int>> adj;

vector<int> subsize;

void dfs1(int v, int p){
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs1(nei,v);
      subsize[v] += subsize[nei];
   }
   subsize[v]++;
}

int centroid;
void dfs2(int v, int p){
   for(int nei : adj[v]){
      if(nei == p) continue;
      if(subsize[nei] > n/2){
         dfs2(nei,v);
         return;
      }
   }
   
   centroid = v;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   subsize = vector<int>(n+1,0);
   dfs1(1,-1);
   
   centroid = -1;
   dfs2(1,-1);
   
   cout << centroid << endl;
   
   return 0;
}