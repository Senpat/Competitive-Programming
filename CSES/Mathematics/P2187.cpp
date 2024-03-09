#include <bits/stdc++.h>
//overcounts
using namespace std;

const int N = 2000005;
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
   
   string s;
   cin >> s;
   
   int pn = s.length();
   
   int curp = 0;
   bool fail = false;
   for(int k = 0; k < pn; k++){
      if(s[k] == '('){
         curp++;
      } else {
         curp--;
      }
      
      if(curp < 0){
         fail = true;
      }
   }
   
   if(fail || n%2 == 1){
      cout << "0";
      return 0;
   }
   
   //curp is # of ) to add
   
   //length of full bracket sequence
   int full = n-pn-curp;
   if(full < 0){
      cout << "0";
      return 0;
   } else if(full == 0){
      cout << "1";
      return 0;
   }
   
   full >>= 1;
   
   long long answer = (exp((long long)full,MOD-2) * choose(2*full,full+1) + MOD)%MOD;
   
   //add a close at the front or after every open (full+1 places)
   int locs = full+1;
   answer = (answer * choose(locs + curp - 1, locs - 1) + MOD)%MOD;
   
   cout << answer << endl;
   
   
   return 0;
}