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
      vector<int> a(n,0);
      vector<int> b(n,0);
      for(int k = 0; k < n; k++){
         cin >> a[k];
      }
      for(int k = 0; k < n; k++){
         cin >> b[k];
      }
      
      vector<int> dmin(n,0);
      vector<int> dmax(n,0);
      
      int i = 0;
      
      int i2 = 0;
      for(int k = 0; k < n; k++){
         while(b[i] < a[k]) i++;
         dmin[k] = b[i]-a[k];
         
         if(k == n-1) dmax[k] = b[n-1]-a[k];
         else {
            i2 = max(i2,k);
            while(i2 < n-1 && b[i2] >= a[i2+1]) i2++; 
            dmax[k] = b[i2]-a[k];
         }
      }
      
      for(int k = 0; k < n; k++){
         cout << dmin[k] << " ";
      }
      cout << "\n";
      for(int k = 0; k < n; k++){
         cout << dmax[k] << " ";
      }
      cout << "\n";
   }
   
   
   return 0;
}