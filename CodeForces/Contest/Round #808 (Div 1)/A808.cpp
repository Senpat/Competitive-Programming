#include <bits/stdc++.h>

using namespace std;

bool check(vector<int> &array, int x, int n, int m){
   
   int i = m;
   for(int k = x; k < n; k++){
      if(i == 0) return false;
      if(array[k] > i) i--;
   }
   
   return true;
}   

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<int> array(n);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      int l = 0;
      int r = n-1;
      int ans = n;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(check(array,mid,n,m)){
            r = mid-1;
            ans = mid;
         } else {
            l = mid+1;
         }
      }
      
      vector<int> answer(n,0);
      for(int k = 0; k < ans; k++){
         if(array[k] <= m) answer[k] = 1;
      }
      for(int k = ans; k < n; k++) answer[k] = 1;
      
      for(int k = 0; k < n; k++){
         cout << answer[k];
      }
      cout << endl;
         
   
   }
   
   
   
   
   return 0;
}