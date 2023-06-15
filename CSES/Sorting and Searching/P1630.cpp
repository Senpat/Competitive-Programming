#include <bits/stdc++.h>


int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
      
   int n;
   std::cin >> n;
   
   long long sumd = 0LL;
   std::vector<long long> array(n);
   for(int k = 0; k < n; k++){
      long long d;
      std::cin >> array[k] >> d;
      sumd += d;
   }
   
   std::sort(array.begin(),array.end());
   
   long long suma = 0LL;
   long long psum = 0LL;
   for(int k = 0; k < n; k++){
      suma += psum + array[k];
      psum += array[k];
   }
   
   long long answer = sumd - suma;
   std::cout << answer << std::endl;
   
      
   
   
   return 0;
}