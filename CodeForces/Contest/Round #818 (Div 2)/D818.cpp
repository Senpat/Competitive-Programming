#include <bits/stdc++.h>

using namespace std;

long long MOD = 1000000007LL;

long long exp(long long base, int power){
   if(power == 0) return 1;
   if(power == 1) return (base + MOD) % MOD;
   long long ans = exp(base,power/2);
   ans = (ans*ans + MOD) % MOD;
   if(power%2 == 1) ans = (ans*base + MOD) % MOD;
   return (ans + MOD) % MOD;
}

vector<long long> fac;
vector<long long> ifac;

long long choose(int n, int r){
   //cout << "choose " << n << " " << r << " " << fac[n] << " " << ifac[r] << " " << ifac[n-r] << endl;
   long long prod1 = (fac[n] * ifac[r] + MOD)%MOD;
   return (prod1 * ifac[n-r] + MOD)%MOD;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 100005;
   vector<long long> pow2(N,0LL);
   pow2[0] = 1LL;
   for(int k = 1; k < N; k++){
      pow2[k] = (2LL*pow2[k-1] + MOD)%MOD;
   }
   
   fac = vector<long long>(N,0LL);
   ifac = vector<long long>(N,0LL);
   fac[0] = 1LL;
   ifac[0] = 1LL;
   for(int k = 1; k < N; k++){
      fac[k] = ((long long)k * fac[k-1] + MOD)%MOD;
      ifac[k] = exp(fac[k],MOD-2);
   }
   
      
   int n,m;
   cin >> n >> m;
   
   if(m >= n){
      cout << pow2[n] << endl;
      return 0;
   }
   
   long long answer = 0LL;
   for(int k = 0; k <= m; k++){
      long long prod = (pow2[k] * choose(n-k-1,m-k) + MOD)%MOD;
      answer = (answer + prod + MOD)%MOD;
      //cout << pow2[k] << " " << choose(n-k-1,m-k) << " " << answer << endl;
   }
   
   cout << answer << endl;
   
   
   
   return 0;
}