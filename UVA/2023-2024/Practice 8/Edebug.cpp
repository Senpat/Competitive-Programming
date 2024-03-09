#include <bits/stdc++.h>

using namespace std;

//const long long MOD = 1000000009LL;
//const long long i2 =   500000005LL;

const long long MOD = 998244353LL;
const long long i2 = 499122177LL;

vector<long long> pow2;
vector<long long> ipow2;

//x,y is lower right corner
long long gethash(int x, int y, int len, const vector<vector<long long>>& psum, int m){
   int sx = x-len+1;
   int sy = y-len+1;
   
   long long ret = psum[x][y];
   if(sx > 0){
      ret -= psum[sx-1][y];
      if(ret < 0) ret += MOD;
   }
   if(sy > 0){
      ret -= psum[x][sy-1];
      if(ret < 0) ret += MOD;
   }
   if(sx > 0 && sy > 0){
      ret += psum[sx-1][sy-1];
      if(ret >= MOD) ret -= MOD;
   }
   
   ret = (ret * ipow2[sx*m + sy] + MOD)%MOD;
   return ret;
}

vector<vector<long long>> getpsum(const vector<vector<int>>& board, int n, int m){
   vector<vector<long long>> psum(n, vector<long long>(m,0LL));
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         if(board[k][j] == 1){
            psum[k][j] = pow2[k*m+j];
         }
      }
      for(int j = 1; j < m; j++){
         psum[k][j] += psum[k][j-1];
         if(psum[k][j] >= MOD) psum[k][j] -= MOD;
      }
   }
   for(int j = 0; j < m; j++){
      for(int k = 1; k < n; k++){
         psum[k][j] += psum[k-1][j];
         if(psum[k][j] >= MOD) psum[k][j] -= MOD;
      }
   }
   return psum;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 100000;
   pow2 = vector<long long>(N);
   ipow2 = vector<long long>(N);
   pow2[0] = 1LL;
   ipow2[0] = 1LL;
   for(int k = 1; k < N; k++){
      pow2[k] = (pow2[k-1] * 2LL + MOD)%MOD;
      ipow2[k] = (ipow2[k-1] * i2 + MOD)%MOD;
   }
   
   //ifstream fin("Etest.txt");
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> board(n,vector<int>(m));
   vector<vector<int>> rboard(n,vector<int>(m));
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j]-'0';
         rboard[n-k-1][m-j-1] = board[k][j];
      }
   }
   
   auto psum = getpsum(board,n,m);
   auto rpsum = getpsum(rboard,n,m);
   /*
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         cout << psum[k][j] << " ";
      }
      cout << endl;
   }
   */
   int answer = -1;
   for(int len = 2; len <= min(n,m); len++){
      for(int k = len-1; k < n; k++){
         for(int j = len-1; j < m; j++){
            
            long long h1 = gethash(k,j,len,psum,m);
            
            //flip
            int k2 = n-k-1;
            int j2 = m-j-1;
            int x = k2+len-1;
            int y = j2+len-1;
            long long h2 = gethash(x,y,len,rpsum,m);
            /*
            if(k == 2 && j == 2 && len == 3){
               cout << x << " " << y << endl;
               cout << h1 << " " << h2 << endl;
            }*/
            if(h1 == h2){
               answer = len;
            }
         }
      }
   }
   
   cout << answer << endl;
   
   
   return 0;
}