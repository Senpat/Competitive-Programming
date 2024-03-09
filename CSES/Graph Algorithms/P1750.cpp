#include <bits/stdc++.h>
//tle with vector of vector, passes with array
using namespace std;

int jump[31][200005];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   vector<int> array(n+1);
   for(int k = 1; k <= n; k++){
      cin >> array[k];
   }
   
   int D = 29;
   for(int k = 1; k <= n; k++){
      jump[0][k] = array[k];
   }
   
   for(int d = 1; d <= D; d++){
      for(int k = 1; k <= n; k++){
         jump[d][k] = jump[d-1][jump[d-1][k]];  
      }
   }
   
   for(int t = 0; t < q; t++){
      int v,x;
      cin >> v >> x;
      
      int i = 0;
      for(int d = D; d >= 0; d--){
         if(i + (1 << d) <= x){
            v = jump[d][v];
            i += (1 << d);
         }
      }
      
      cout << v << endl;
   }
   
   
   return 0;
}