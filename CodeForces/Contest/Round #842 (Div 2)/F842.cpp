#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int M = 700;
   
   int n;
   cin >> n;
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   vector<long long> dp(n);
   fill(dp.begin(),dp.end(),LLONG_MAX);
   dp[0] = 0LL;
   for(int k = 1; k < n; k++){
      long long curmin = array[k];
      for(int j = 1; j <= M; j++){
         if(k-j+1 < 0) continue;
         curmin = min(curmin,array[k-j]);
         dp[k] = min(dp[k],dp[k-j]+curmin*(long long)(j*j));
      }
   }
   
   for(int k = 0; k < n; k++){
      cout << dp[k] << " ";
   }
   
   
   
   
   return 0;
}