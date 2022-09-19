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
      
      int xp = 0;
      int xn = 0;
      int yp = 0;
      int yn = 0;
      
      for(int k = 0; k < n; k++){
         int x,y;
         cin >> x >> y;
         
         if(x > 0) xp = max(xp,x);
         if(x < 0) xn = max(xn,-x);
         if(y > 0) yp = max(yp,y);
         if(y < 0) yn = max(yn,-y);
      }
      
      int answer = 2*(xp+xn+yp+yn);
      cout << answer << endl;  
   }  
   
   
   return 0;
}