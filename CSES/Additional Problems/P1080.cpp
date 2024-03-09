#include <bits/stdc++.h>

using namespace std;

const int N = 505;
const long long MOD = 1000000007LL;

long long fac[N];
long long ifac[N];

long long exp(long long base, long long power){
   if(base >= MOD) base = (base + MOD)%MOD;        //just in case
   
   if(power == 0LL) return 1LL;
   if(power == 1LL) return base;
   long long ans = exp(base,power/2LL);
   ans = (ans*ans + MOD) % MOD;
   if(power%2LL == 1LL) ans = (ans*base + MOD) % MOD;
   return ans;
}

long long choose(int n, int r){
   long long prod = (fac[n] * ifac[r] + MOD)%MOD;
   return (prod * ifac[n-r] + MOD)%MOD;
}

long long dp[N][N];
string s;

long long dothing(int l, int r){
   if((r-l+1) % 2 == 1) return 0LL;
   if(l > r) return 1LL;
   if(dp[l][r] != -1LL) return dp[l][r];
   //cout << l << " " << r << endl;
   long long ret = 0LL;
   bool check = true;
   for(int k = l+1; k <= r; k++){
      if(s[k] != s[l]) continue;
      if(check){
         int lsize = k-l+1;
         int rsize = r-k;
         if(lsize % 2 == 0){
            long long left = dothing(l+1,k-1);
            long long right = dothing(k+1,r);
            long long prod = (left * right + MOD)%MOD;
            prod = (prod * choose((r-l+1)/2,lsize/2) + MOD)%MOD;
            ret = (ret + prod + MOD)%MOD;
         }
      }
      check = !check;
   }
   
   dp[l][r] = ret;
   return ret;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   fac[0] = 1LL;
   for(int k = 1; k < N; k++){
      fac[k] = ((long long)k * fac[k-1] + MOD)%MOD;
   }
   ifac[N-1] = exp(fac[N-1],MOD-2);   
   for(int k = N-2; k >= 0; k--){
      ifac[k] = ((long long)(k+1) * ifac[k+1] + MOD)%MOD;
   }
   
   cin >> s;
   int n = s.length();
   
   for(int k = 0; k < n; k++){
      for(int j = 0; j < n; j++){
         dp[k][j] = -1LL;
      }
   }
   
   cout << dothing(0,n-1) << endl;
   
   
   return 0;
}