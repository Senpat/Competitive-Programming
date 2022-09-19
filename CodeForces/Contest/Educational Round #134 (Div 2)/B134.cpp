#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int x,y,sx,sy,d;
      cin >> x >> y >> sx >> sy >> d;
      
      //up or right
      int i = 0;
      if(sx + d >= x || sy - d <= 1) i++;
      
      if(sx - d <= 1 || sy + d >= y) i++;
      
      if(i == 2){
         cout << -1 << endl;
      } else {
         cout << x+y-2 << endl;
      }
   }
   
   
   
   return 0;
}