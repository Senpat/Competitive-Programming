#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int a;
   int b;
   long long w;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      //cout << n << " " << m;
      
      vector<vector<long long>> d(n+1,vector<long long>(n+1,1000000000000000LL));
      
      vector<Edge> edges;
      
      for(int k = 0; k < m; k++){
         int a,b;
         long long w;
         cin >> a >> b >> w;
         //cout << "edge: " << a << " " << b << " " << w << endl;
         Edge e;
         e.a = a;
         e.b = b;
         e.w = w;
         edges.push_back(e);
         
         d[a][b] = min(d[a][b],w);
         d[b][a] = min(d[b][a],w);
      }
      
      for(int k = 1; k <= n; k++) d[k][k] = 0LL;
      
      for (int k = 1; k <= n; ++k) {
          for (int i = 1; i <= n; ++i) {
              for (int j = 1; j <= n; ++j) {
                  d[i][j] = min(d[i][j], d[i][k] + d[k][j]); 
              }
          }
      }
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            cout << d[k][j] << " ";
         }
         cout << endl;
      }
      
      
      long long answer = LLONG_MAX;
      for(int k = 0; k < m; k++){
         //cout << edges[k].a << " " << edges[k].b << " " << edges[k].w << endl;
         long long move = (long long)min(d[1][edges[k].a]+d[n][edges[k].b],d[1][edges[k].b]+d[n][edges[k].a]);
         answer = min(answer,move*edges[k].w);
      }
      
      cout << answer << "\n";
      
       
      
   }
   
   
   return 0;
}