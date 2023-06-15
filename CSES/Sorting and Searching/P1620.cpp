#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,t;
   cin >> n >> t;
   
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   long long l = 1LL;
   long long r = 1000000000000000000LL;
   long long ans = -1;
   
   while(l <= r){
      long long mid = l + (r-l)/2LL;
      
      long long num = 0LL;
      for(int k = 0; k < n; k++){
         num += mid/array[k];
         if(num >= t) break;
      }
      
      if(num >= t){
         ans = mid;
         r = mid-1;
      } else {
         l = mid+1;
      }
   }
   
   cout << ans << endl;
   
   return 0;
}