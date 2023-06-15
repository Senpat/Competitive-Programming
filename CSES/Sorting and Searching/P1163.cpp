#include <bits/stdc++.h>

int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
   
   int x,n;
   std::cin >> x >> n;
   
   std::vector<int> array(n);
   for(int k = 0; k < n; k++){
      std::cin >> array[k];
   }
   
   std::set<std::pair<int,int>> tset;       //pair stores length of segment and starting index of segment
   tset.insert(std::make_pair(x,0));
   
   //stores numbers that have been added
   std::set<int> nums;
   nums.insert(0);
   nums.insert(x);
   
   for(int k = 0; k < n; k++){
      auto ub = nums.upper_bound(array[k]);
      int right = *ub;
      ub--;
      int left = *ub;
      
      tset.erase(std::make_pair(right-left,left));
      tset.insert(std::make_pair(array[k]-left,left));
      tset.insert(std::make_pair(right-array[k],array[k]));
      
      nums.insert(array[k]);
      
      std::cout << (*(tset.rbegin())).first << " ";
   }
   
   
   
   return 0;
}