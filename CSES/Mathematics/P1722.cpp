#include <bits/stdc++.h>
//from https://cp-algorithms.com/algebra/fibonacci-numbers.html

using namespace std;

const long long MOD = 1000000007LL;

//mat mul exp

vector<vector<long long>> matmul(const vector<vector<long long>>& a, const vector<vector<long long>>& b){
   int len = a.size();
   vector<vector<long long>> ret = vector<vector<long long>>(len,vector<long long>(len));

   for(int k = 0; k < len; k++){
      for(int j = 0; j < len; j++){
         for(int h = 0; h < len; h++){
         	ret[k][j] = (ret[k][j] + a[k][h]*b[h][j] + MOD)%MOD;
         }
      }
   }
   return ret;
}


vector<vector<long long>> matexp(const vector<vector<long long>>& base, long long power){
   int len = base.size();
   if(power == 0){
      //IDENTITY MATRIX OF THE RIGHT SIZE
      vector<vector<long long>> ret(len,vector<long long>(len,0));
      for(int k = 0; k < len; k++) ret[k][k] = 1;
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
   
   long long x;
   cin >> x;
   
   
   vector<vector<long long>> mat = {{1LL,1LL},{1LL,0LL}};
   
   auto ret = matexp(mat,x);
   
   cout << ret[0][1] << endl;
   
   
   return 0;
}