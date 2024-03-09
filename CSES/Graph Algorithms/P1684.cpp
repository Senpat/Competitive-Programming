#include <bits/stdc++.h>
//2-sat
using namespace std;

vector<vector<int>> adj;
vector<vector<int>> radj;

vector<int> order;
vector<bool> seen;
void dfs1(int v){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(!seen[nei]){
         dfs1(nei);
      }
   }
   
   order.push_back(v);
}

vector<int> comp;
int compid;
void dfs2(int v){
   comp[v] = compid;
   
   for(int nei : radj[v]){
      if(comp[nei] == -1){
         dfs2(nei);
      }
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> m >> n;
   
   //[1,n] is variable, [n+1,2*n] is not of variable
   adj = vector<vector<int>>(2*n+1,vector<int>());
   radj = vector<vector<int>>(2*n+1,vector<int>());
   
   for(int k = 0; k < m; k++){
      char c1,c2;
      int v1,v2;
      
      cin >> c1 >> v1 >> c2 >> v2;
      
      int nv1,nv2;
      
      //from a v b to not a -> b and not b -> a
      
      if(c1 == '+'){
         nv1 = n+v1;
      } else {
         nv1 = v1;
         v1 = n+v1;
      }
      
      if(c2 == '+'){
         nv2 = n+v2;
      } else {
         nv2 = v2;
         v2 = n+v2;
      }
      
      adj[nv1].push_back(v2);
      adj[nv2].push_back(v1);
      
      radj[v2].push_back(nv1);
      radj[v1].push_back(nv2);
   }
   
   //get connected components
   order = vector<int>();
   seen = vector<bool>(2*n+1,false);
   
   for(int k = 1; k <= 2*n; k++){
      if(!seen[k]){
         dfs1(k);  
      }
   }
   
   compid = 0;
   comp = vector<int>(2*n+1,-1);
   for(int o = order.size()-1; o >= 0; o--){
      if(comp[order[o]] == -1){
         dfs2(order[o]);
         compid++;
      }
   }
   
   //comp[a] < comp[b] means a can go to b
   
   vector<bool> answer(n+1);
   bool fail = false;
   for(int k = 1; k <= n; k++){
      if(comp[k] == comp[k+n]){
         fail = true;
         break;
      }
      answer[k] = comp[k] > comp[k+n];          //false can go to true, but true can't go to false
   }
   
   if(fail){
      cout << "IMPOSSIBLE\n";
   } else {
      for(int k = 1; k <= n; k++){
         if(answer[k]){
            cout << "+ ";
         } else {
            cout << "- ";
         }
      }
   
   }
   
   return 0;
}