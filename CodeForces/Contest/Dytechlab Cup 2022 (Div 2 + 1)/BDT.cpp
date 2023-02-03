#include <bits/stdc++.h>

using namespace std;


long long getsqrt(long long x){
   long long l = 1LL;
   long long r = 1000000000LL;
   long long ans = -1;
   
   while(l <= r){
      long long mid = l + (r-l)/2;
      if(mid*mid <= x){
         ans = mid;
         l = mid+1;
      } else {
         r = mid-1;
      }
   }
   
   return ans;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      long long l,r;
      cin >> l >> r;
      
      //binary search for floor of square root
      long long lsqrt = getsqrt(l);
      long long rsqrt = getsqrt(r);
      
      long long answer = 0LL;
      for(int k = 0; k < 3; k++){
         long long curl = lsqrt*lsqrt + lsqrt*k;
         long long curr = rsqrt*rsqrt + rsqrt*k;
         if(curl >= l && curl <= r) answer++;
         if(rsqrt != lsqrt && curr >= l && curr <= r) answer++;
      }
      
      answer += max(0LL, (rsqrt-lsqrt-1)*3);
      
      cout << answer << "\n";
   }
   
   
   return 0;
}