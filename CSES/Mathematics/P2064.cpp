#include <bits/stdc++.h>

using namespace std;

const int N = 1000005;
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
   
   int n;
   cin >> n;
   if(n % 2 == 1){
      cout << 0;
   } else {
      //catalan numbers - 1/n * choose(2n,n+1)
      int n2 = n/2;
      long long n2i = exp((long long)n2,MOD-2);
      long long answer = (n2i * choose(2*n2,n2+1) + MOD)%MOD;
      cout << answer << endl;
   }
   
   
   return 0;
}