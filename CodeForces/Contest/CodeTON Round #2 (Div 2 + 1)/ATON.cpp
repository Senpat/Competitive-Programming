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
      
      string as,bs;
      cin >> as >> bs;
      
      vector<int> a(n,0);
      vector<int> b(m,0);
      
      for(int k = 0; k < n; k++){
         a[k] = as[k]-'0';
      }
      for(int k = 0; k < m; k++){
         b[k] = bs[k]-'0';
      }
      
      bool fail = false;
      for(int k = 0; k < m-1; k++){
         if(a[n-(m-1) + k] != b[1 + k]){
            fail = true;
            break;
         }
      }
      
      if(fail){
         cout << "NO\n";
         continue;
      }
      
      bool found = false;
      for(int k = 0; k < n-(m-1); k++){
         if(a[k] == b[0]){
            found = true;
            break;
         }
      }
      
      if(found){
         cout << "YES\n";
      } else {
         cout << "NO\n";
      }
   }
   
   
   
   
   return 0;
}