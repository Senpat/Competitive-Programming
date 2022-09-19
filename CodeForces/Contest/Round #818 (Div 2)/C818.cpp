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
      vector<int> a(n);
      vector<int> b(n);
      
      for(int k = 0; k < n; k++){
         cin >> a[k];
      }
      for(int k = 0; k < n; k++){
         cin >> b[k];
      }
      
      bool fail = false;
      for(int k = 0; k < n; k++){
         if(a[k] > b[k]) fail = true;
      }
      
      if(fail){
         cout << "NO\n";
         continue;
      }
      
      //every number can get to its target assuming the previous one is at the target
      for(int k = 0; k < n; k++){
         if(a[k] < b[k] && b[(k+1)%n] < b[k]-1) fail = true;
      }
      
      if(fail){
         cout << "NO\n";
      } else {
         cout << "YES\n";
      }
   }
   
   
   return 0;
}