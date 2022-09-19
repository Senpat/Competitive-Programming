#include <bits/stdc++.h>
//binary search
//answer is min(2*min, max(all adjacent mins))
using namespace std;

int BIG = 1000000000;    

//pass by value
bool check(int n, int m, vector<int> array, int x){
   
   int added = 0;
   for(int k = 0; k < n; k++){
      if(2*array[k] < x){
         array[k] = BIG;
         added++;
      }
   }
   
   //get current max of adjacent mins
   int adjminmax = 0;
   bool has1above = false;
   for(int k = 0; k < n; k++){
      if(k < n-1) adjminmax = max(adjminmax,min(array[k],array[k+1]));
      if(array[k] >= x) has1above = true;
   }
   
   if(adjminmax < x){
      if(has1above) added++;
      else added+=2;
   }
   
   return added <= m;      
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
  
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<int> array(n,0);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      int l = 1;
      int r = BIG;
      int ans = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         //see if it's possible to get >= mid
         if(check(n,m,array,mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      cout << ans << "\n";
      
      
   }
   
   return 0;
}