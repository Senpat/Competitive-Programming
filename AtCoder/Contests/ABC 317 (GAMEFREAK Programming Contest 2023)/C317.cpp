#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> adj(n+1,vector<int>(n+1,-1));
   
   for(int k = 0; k < m; k++){
      int a,b,c;
      cin >> a >> b >> c;
      adj[a][b] = c;
      adj[b][a] = c;
   }
   
   vector<int> p(n);
   for(int k = 0; k < n; k++){
      p[k] = k+1;
   }
   
   int answer = 0;
   do{
      int cur = 0;      
      for(int k = 1; k < n; k++){
         if(adj[p[k-1]][p[k]] != -1){
            cur += adj[p[k-1]][p[k]];
         } else {
            break;
         }
      }
      
      answer = max(answer,cur);
      
   } while (next_permutation(p.begin(),p.end()));
   
   cout << answer << endl;
   
   return 0;
}