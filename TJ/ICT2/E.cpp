#include <bits/stdc++.h>

using namespace std;

long long MOD = 1000000007;

long long exp(int base, int power){
   if(power == 0) return 1;
   if(power == 1) return (base + MOD) % MOD;
   long long ans = exp(base,power/2);
   ans = (ans * ans + MOD) % MOD;
   if(power%2 == 1) ans = (ans*base + MOD) % MOD;
   return (ans + MOD) % MOD;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   long long sum = 0;
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      
      sum += exp(a,b);
      sum = (sum + MOD)%MOD;
   }
   
   
   cout << sum;
   
   
   return 0;
}