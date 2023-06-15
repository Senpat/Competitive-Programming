#include <bits/stdc++.h>

int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
   
   int n;
   std::cin >> n;
   std::vector<int> array(n);
   for(int k = 0; k < n; k++){
      std::cin >> array[k];
   }
   
   std::vector<int> towers;
   for(int k = 0; k < n; k++){
      int l = 0;
      int r = towers.size()-1;
      int ans = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         //find first tower > array[k]
         if(towers[mid] > array[k]){
            r = mid-1;
            ans = mid;
         } else {
            l = mid+1;
         }
      }
      
      if(ans == -1) towers.push_back(array[k]);
      else towers[ans] = array[k];
   }
   
   std::cout << towers.size() << std::endl;
   
   
   return 0;
}