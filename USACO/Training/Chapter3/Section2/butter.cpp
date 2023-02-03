/*
TASK: butter
LANG: C++
*/

#include <bits/stdc++.h>

using namespace std;





int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   ifstream fin ("butter.in");
   ofstream fout ("butter.out");
   
   int c,n,m;
   fin >> c >> n >> m;
   
   vector<int> cows(n+1,0);
   for(int k = 0; k < c; k++){
      int i;
      fin >> i;
      cows[i]++;
   }
   
   vector<vector<int>> adj(n+1,vector<int>(n+1,INT_MAX));
   for(int k = 0; k < m; k++){
      int a,b,w;
      fin >> a >> b >> w;
      adj[a][b] = w;
      adj[b][a] = w;
   }
   
   vector<vector<int>> dist(n+1,vector<int>(n+1,INT_MAX));
   for(int k = 0; k <= n; k++){
      for(int j = 0; j <= n; j++){
         dist[k][j] = adj[k][j];
      }
      dist[k][k] = 0;
   }
   
   for(int k = 1; k <= n; k++){
      for(int j = 1; j <= n; j++){
         for(int h = 1; h <= n; h++){
            if(dist[j][k] == INT_MAX || dist[k][h] == INT_MAX) 
               continue;
            dist[j][h] = min(dist[j][h],dist[j][k] + dist[k][h]);     
         }
      }
   }
   
   int answer = INT_MAX;
   for(int k = 1; k <= n; k++){
      int cursum = 0;
      for(int j = 1; j <= n; j++){
         cursum += cows[j] * dist[k][j];
      }
      answer = min(answer,cursum);
   }
   
   fout << answer << endl;
   
   
   
   return 0;
}