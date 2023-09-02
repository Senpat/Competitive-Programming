#include <bits/stdc++.h>
//also P1660
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   long long x;
   cin >> n >> x;
   
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   unordered_map<long long,long long> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   
   hmap[0] = 1LL;
   
   long long psum = 0LL;
   long long answer = 0LL;
   for(int k = 0; k < n; k++){
      psum += array[k];
      
      answer += hmap[psum-x];
      hmap[psum]++;
      
   }
   
   cout << answer << endl;
   
   return 0;
}