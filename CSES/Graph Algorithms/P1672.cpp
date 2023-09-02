#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,q;
   cin >> n >> m >> q;
   
   vector<vector<long long>> dist(n+1,vector<long long>(n+1,LLONG_MAX));
   for(int k = 1; k <= n; k++) dist[k][k] = 0LL;
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      dist[a][b] = min(dist[a][b],w);
      dist[b][a] = min(dist[b][a],w);
   }
   
   for(int k = 1; k <= n; k++){
      for(int j = 1; j <= n; j++){
         for(int h = 1; h <= n; h++){
            if(k == j || j == h || k == h) continue;
            if(dist[j][k] == LLONG_MAX || dist[k][h] == LLONG_MAX) continue;
            dist[j][h] = min(dist[j][h],dist[k][j] + dist[k][h]);
            dist[h][j] = dist[j][h];
         }
      }
   }
   
   for(int t = 0; t < q; t++){
      int a,b;
      cin >> a >> b;
      if(dist[a][b] == LLONG_MAX) cout << "-1\n";
      else cout << dist[a][b] << "\n";
   }
   
   
   return 0;
}