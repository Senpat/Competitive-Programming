#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      double r, h1, h2;
      cin >> r >> h1 >> h2;
      
      h1 /= 1000.0;
      h2 /= 1000.0;
      
      double theta1 = acos(r/(r+h1));
      double theta2 = acos(r/(r+h2));
      
      double answer = (theta1+theta2)*r;
      cout << setprecision(10) << answer << "\n";
   }
   
   return 0;
}