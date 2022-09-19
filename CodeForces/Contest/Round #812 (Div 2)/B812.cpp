#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n,0);
      vector<int> order(n,0);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         order[k] = array[k];
      }
      
      sort(order.begin(),order.end());
      
      int l = 0;
      int r = n-1;
      
      bool found = false;
      for(int k = 0; k < n; k++){
         if(array[l] == order[k]){
            l++;
         } else if(array[r] == order[k]){
            r--;
         } else {
            found = true;
            break;
         }
      }
      
      if(found){
         cout << "NO\n";
      } else {
         cout << "YES\n";
      }
   }
   
   
   return 0;
}