#include <bits/stdc++.h>

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
   
   int n,m;
   cin >> n >> m;
   
   m++;
   vector<pair<int,int>> array(m);
   array[0] = make_pair(n,n);
   for(int k = 1; k < m; k++){
      int x,y;
      cin >> x >> y;
      array[k] = make_pair(x,y);
   }
   
   sort(array.begin(),array.end());
   
   vector<long long> dp(m,0LL);
   
   for(int k = 0; k < m; k++){
      //if(k > 0 && array[k] == array[k-1]) continue;          //no need for this line because it will get subtracted anyway
      dp[k] = choose(array[k].first-1 + array[k].second-1, array[k].first-1);
      for(int j = 0; j < k; j++){
         if(array[j].first <= array[k].first && array[j].second <= array[k].second){
            int x = array[k].first-array[j].first;
            int y = array[k].second-array[j].second;
            long long prod = (choose(x+y,x) * dp[j] + MOD)%MOD;
            dp[k] = ((dp[k] - prod)%MOD + MOD)%MOD;
         }
      }
   }
   
   cout << dp[m-1] << endl;
   
   
   
   return 0;
}