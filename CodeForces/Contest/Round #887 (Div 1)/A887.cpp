#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
   
      int n,m;
      cin >> n >> m;
   
      vector<long long> array(n,0LL);
      set<long long> tset;
      unordered_map<long long,int> indexof;
      indexof.reserve(1024);
      indexof.max_load_factor(0.25);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         tset.insert(array[k]);
         indexof[array[k]] = k;
      }
   
      if(array[0] != 1LL){
         cout << 1 << endl;
         continue;
      }
   
      long long l = 0LL;
      long long r = 40000000005LL;
      long long ans = -1;
      while(l <= r){
         long long mid = l+(r-l)/2;
      
         //check
         long long i = mid;
         for(int k = 0; k < m; k++){
            if(i == 0) 
               break;
            //subtract the # of elements <= i from i
            auto it = tset.upper_bound(i);
            it--;
            long long num = *it;
            long long num_lower = (long long)indexof[num]+1;
            //cout << mid << " " << num << " " << num_lower << endl;
            i -= num_lower;
         }
      
         if(i == 0){
            ans = mid;
            l = mid+1;
         } 
         else {
            r = mid-1;
         }
      }
   
      cout << ans+1 << "\n";
   
   }
   
   return 0;
}