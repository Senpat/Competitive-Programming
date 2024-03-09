#include <bits/stdc++.h>
//debug: handle multi edges
using namespace std;

//mat mul exp

vector<vector<long long>> matmul(const vector<vector<long long>>& a, const vector<vector<long long>>& b){
   int len = a.size();
   vector<vector<long long>> ret = vector<vector<long long>>(len,vector<long long>(len,LLONG_MAX));

   for(int k = 0; k < len; k++){
      for(int j = 0; j < len; j++){
         for(int h = 0; h < len; h++){
         	if(a[j][k] != LLONG_MAX && b[k][h] != LLONG_MAX){
               ret[j][h] = min(ret[j][h],a[j][k] + b[k][h]);
            }
         }
      }
   }
   return ret;
}


vector<vector<long long>> matexp(const vector<vector<long long>>& base, long long power){
   int len = base.size();
   if(power == 0){
      //IDENTITY MATRIX OF THE RIGHT SIZE
      vector<vector<long long>> ret(len,vector<long long>(len,LLONG_MAX));
      for(int k = 0; k < len; k++) ret[k][k] = 0LL;
      return ret;
   }
   if(power == 1LL) 
      return base;
   auto ans = matexp(base,power/2LL);
   ans = matmul(ans,ans);
   if(power%2LL == 1LL) ans = matmul(ans, base);
   return ans;
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,x;
   cin >> n >> m >> x;
   
   vector<vector<long long>> adj(n,vector<long long>(n,LLONG_MAX));
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      a--;b--;
      
      adj[a][b] = min(adj[a][b],w);
   }
   
   auto ret = matexp(adj,x);
   if(ret[0][n-1] == LLONG_MAX){
      cout << "-1" << endl;
   } else {
      cout << ret[0][n-1] << endl;
   }
   
   
   return 0;
}