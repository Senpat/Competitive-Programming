#include <bits/stdc++.h>

using namespace std;
int n;
vector<double> p;
vector<double> dp;
double memo(int mask){
   if(dp[mask] > -1.0)
      return dp[mask];
   int cnt = 0;
   double b = 1.0, w = 1.0;
   for(int i = 0; i < n; ++i){
      if(mask & (1 << i)){
         b *= p[i];
         w *= (1.0 - p[i]);
         ++cnt;
      }
   }
   if(cnt <= 2)
      return 0.0;
   double stay = 1.0;
   for(int i = 0; i < n; ++i){
      if(mask & (1 << i)){
         stay -= b * (1.0 - p[i]) / p[i];
         stay -= w * p[i] / (1.0 - p[i]);
      }
   }
   double res = 0.0;
   for(int i = 0; i < n; ++i){
      if(mask & (1 << i)){
         double gone = b * (1.0 - p[i]) / p[i] + w * p[i] / (1.0 - p[i]);
         double moves = gone / ((1.0 - stay) * (1.0 - stay));
         res += moves + memo(mask ^ (1 << i));
      }
   }
   dp[mask] = res;
   return dp[mask];
}
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   cin >> n;
   p.resize(n);
   for(auto &P : p) cin >> P;
   dp.resize((1 << n), -1e5);
   cout << fixed << setprecision(10);
   cout << 1.0 / memo((1 << n) - 1);
   return 0;
}