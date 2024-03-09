#include <bits/stdc++.h>

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
   
   vector<vector<long long>> mat = {{0,0,0,0,0,1},
                                    {1,0,0,0,0,1},
                                    {0,1,0,0,0,1},
                                    {0,0,1,0,0,1},
                                    {0,0,0,1,0,1},
                                    {0,0,0,0,1,1}};
   
   auto ret = matexp(mat,x);
   
   //initial condition is all 0s plus a 1 in the top right,
   //after multiplying it is the same as just getting element in (5,5)
   cout << ret[5][5] << endl;
   
   
   return 0;
}