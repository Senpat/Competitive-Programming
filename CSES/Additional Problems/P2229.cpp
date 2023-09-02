#include <bits/stdc++.h>

using namespace std;

long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<long long>> psum(2,vector<long long>(m+2,0LL));
   
   for(int k = 1; k <= m+1; k++){
      psum[0][k] = 1L;
   }
   
   for(int k = 1; k < n; k++){
      for(int j = 0; j <= m; j++){
         int r = j;
         int l = max(0,j-k);
         
         long long cur = psum[0][r+1]-psum[0][l];
         if(cur < 0) cur += MOD;
         psum[1][j+1] = psum[1][j] + cur;
         if(psum[1][j+1] >= MOD) psum[1][j+1] -= MOD;
      }
      
      swap(psum[0],psum[1]);
      //psum[0].swap(psum[1]);
   }
   
   long long answer = ((psum[0][m+1]-psum[0][m])%MOD + MOD)%MOD;
   cout << answer << endl;
   
   return 0;
}