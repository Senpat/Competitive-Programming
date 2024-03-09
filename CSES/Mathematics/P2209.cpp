#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;

int gcd(int a, int b){
   if(a > b){
      if(b == 0) return a;
      return gcd(a%b,b);
   } else if(a < b){
      if(a == 0) return b;
      return gcd(b%a,a);
   }
   return a;
}

long long exp(long long base, long long power){
   if(base >= MOD) base = (base + MOD)%MOD;        //just in case
   
   if(power == 0LL) return 1LL;
   if(power == 1LL) return base;
   long long ans = exp(base,power/2LL);
   ans = (ans*ans + MOD) % MOD;
   if(power%2LL == 1LL) ans = (ans*base + MOD) % MOD;
   return ans;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 1000005;
   
   int n;
   long long m;
   cin >> n >> m;
   
   vector<long long> pow(N);
   pow[0] = 1LL;
   for(int k = 1; k < N; k++){
      pow[k] = (pow[k-1] * m + MOD)%MOD;
   }
   
   long long answer = 0LL;
   for(int k = 1; k <= n; k++){
      int g = gcd(n,k);
      answer = (answer + pow[g] + MOD)%MOD;
   }
   
   answer = (answer * exp((long long)n,MOD-2) + MOD)%MOD;
   
   cout << answer << endl;
   
   
   return 0;
}