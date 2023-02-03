#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   long long MAX = 1000000000000000000LL;
   
   long long i = 1;
   vector<long long> same;
   while(i <= MAX){
      same.push_back(i);
      //cout << i << endl;
      i = i*3 - 1;
   }
   same.push_back(i);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
   
      long long n,m;
      cin >> n >> m;
   
      vector<pair<long long,long long>> intervals;
      
      long long maxi = 0;
      for(int k = 0; k < same.size(); k++){
         if(same[k+1] > n){
            maxi = same[k];
            break;
         }
         
         intervals.push_back({1,same[k]});
      
         long long a = 2*same[k];
         long long b = same[k+1]-1;
      
         if(a <= b){
            intervals.push_back({a,b});
         }
         
         
      }
      
      intervals.push_back({maxi-(n-maxi)/2,maxi});
      if(n > maxi){
         intervals.push_back({maxi*2,maxi*2+(n-maxi-1)/2});
      }
      /*
      for(const auto& p : intervals){
         cout << p.first << " " << p.second << endl;
      }*/
   
      long long l = 1LL;
      long long r = 2LL*MAX;
      long long ans = 1LL;
      
      while(l <= r){
         long long mid = l + (r-l)/2;
         
         long long under = 0;
         for(const auto& p : intervals){
            under += max(0LL,min(p.second,mid)-p.first+1);
         }
         
         if(under >= m){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
         
      
      }
      
      cout << ans << "\n";
      
   
   
   }
   
   
   return 0;
}