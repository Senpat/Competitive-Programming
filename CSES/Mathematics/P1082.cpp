#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;
const long long i2 = 500000004LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   long long n;
   cin >> n;
   
   long long answer = 0LL;
   
   long long i = 1LL;
   
   while(i <= n){
      long long freq = n/i;
      long long l = i;
      long long r = n;
      long long ans = i;
      while(l <= r){
         long long mid = l + (r-l)/2L;
         
         if(n/mid == freq){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
   
      long long s1 = (i+r + MOD)%MOD;
      long long s2 = (r-i+1 + MOD)%MOD;
      long long prod = (s1*s2 + MOD)%MOD;
      prod = (prod * i2 + MOD)%MOD;
      prod = (prod * freq + MOD)%MOD;
      answer += prod;
      if(answer >= MOD) answer -= MOD;
      
      i = r+1;
   }
   
   cout << answer << endl;
   
   
   return 0;
}