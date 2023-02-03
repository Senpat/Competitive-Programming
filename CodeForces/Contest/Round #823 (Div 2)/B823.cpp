#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int qq;
   cin >> qq;
   for(int q = 0; q < qq; q++){
      int n;
      cin >> n;
      vector<double> x(n);
      vector<double> t(n);
      
      for(int k = 0; k < n; k++){
         cin >> x[k];
      }
      
      for(int k = 0; k < n; k++){
         cin >> t[k];
      }
      
      double l = 0.0;
      double r = 200000000.0;
      double ans = -1.0;
      int T = 50;
      
      for(int bs = 0; bs < T; bs++){
         double mid = l + (r-l)/2.0;
         
         bool fail = false;
         double lmax = 0.0;
         double rmin = 200000000.0;
         for(int k = 0; k < n; k++){
            if(t[k] > mid){
               fail = true;
               break;
            } else {
               double curl = x[k]-(mid-t[k]);
               double curr = x[k]+(mid-t[k]);
               
               lmax = max(lmax,curl);
               rmin = min(rmin,curr);
            }
         }
         
         if(fail || lmax > rmin){
            l = mid;
         } else {
            r = mid;
            ans = lmax;
         }
      }
      
      cout << setprecision(20) << ans << "\n";
      
      
   }   
   
   
   return 0;
}