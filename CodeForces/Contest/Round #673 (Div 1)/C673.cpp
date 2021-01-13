#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<int> array;
   for(int k = 0; k < n; k++){
      int x;
      cin >> x;
      array.push_back(x);
   }
   
   long long numinversions = 0LL;
   int num = 0;
   
   for(int i = 30; i >= 1; i--){
      unordered_map<int,vector<int>> hmap;
      int ishifti1 = 1 << (i-1);
      for(int k = 0; k < n; k++){
         int shift = array[k] >> i;
            
         if((array[k] & (ishifti1)) > 0){
            hmap[shift].push_back(1);
         } 
         else {
            hmap[shift].push_back(0);
         }
            
      }
      
      long long flip = 0LL;
      long long noflip = 0LL;
      for(auto x : hmap){
         long long num0 = 0L;
         long long num1 = 0L;
         for(int v : x.second){
            if(v == 1){
               flip += num0;
               num1++;
            } 
            else {
               noflip += num1;
               num0++;
            }
         }
      }
      
      if(flip < noflip){
         num += ishifti1;
      }
         
      numinversions += min(flip,noflip);
   }
   
   cout << numinversions << " " << num << endl;
   
   
   
   
   return 0;
}