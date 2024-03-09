#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;

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
   
   long long n;
   cin >> n;
   
   long long quad = (n/2) * (n/2);
   if(n%2 == 1LL) quad += n/2;
   
   long long answer = 0LL;
   //1
   answer = (answer + exp(2LL,quad) + MOD)%MOD;
   //2
   answer = (answer + exp(2LL,quad*2LL) + MOD)%MOD;
   //3
   answer = (answer + exp(2LL,quad) + MOD)%MOD;
   //4
   answer = (answer + exp(2LL,quad*4LL) + MOD)%MOD;
   
   if(n%2 == 1LL) answer = (answer * 2LL + MOD)%MOD;
   
   answer = (answer * exp(4LL,MOD-2LL) + MOD)%MOD;
   
   cout << answer << endl;
   
   
   return 0;
}