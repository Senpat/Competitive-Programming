#include <bits/stdc++.h>

using namespace std;


bool has(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3){
   if(x1 == x && y1 == y) return true;
   if(x2 == x && y2 == y) return true;
   if(x3 == x && y3 == y) return true;
   return false;
}

bool corner(int n, int x1, int y1, int x2, int y2, int x3, int y3){
   if(has(1,1,x1,y1,x2,y2,x3,y3) && has(1,2,x1,y1,x2,y2,x3,y3) && has(2,1,x1,y1,x2,y2,x3,y3)) return true;
   if(has(n,1,x1,y1,x2,y2,x3,y3) && has(n,2,x1,y1,x2,y2,x3,y3) && has(n-1,1,x1,y1,x2,y2,x3,y3)) return true;
   if(has(1,n,x1,y1,x2,y2,x3,y3) && has(2,n,x1,y1,x2,y2,x3,y3) && has(1,n-1,x1,y1,x2,y2,x3,y3)) return true;
   if(has(n,n,x1,y1,x2,y2,x3,y3) && has(n-1,n,x1,y1,x2,y2,x3,y3) && has(n,n-1,x1,y1,x2,y2,x3,y3)) return true;
   return false;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      int x1,y1,x2,y2,x3,y3;
      cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
      
      int xt,yt;
      cin >> xt >> yt;
      
      int xsame = -1;
      int ysame = -1;
      
      if(x1 == x2) xsame = x1;
      if(x1 == x3) xsame = x1;
      if(x2 == x3) xsame = x2;
      
      if(y1 == y2) ysame = y1;
      if(y1 == y3) ysame = y1;
      if(y2 == y3) ysame = y2;
      
      if(xsame == xt || ysame == yt){
         cout << "YES\n";
         continue;
      }
      
      if(!corner(n,x1,y1,x2,y2,x3,y3)){
         if(abs(xt-x1)%2 == 0 && abs(yt-y1)%2 == 0) cout << "YES\n";
         else if(abs(xt-x2)%2 == 0 && abs(yt-y2)%2 == 0) cout << "YES\n";
         else if(abs(xt-x3)%2 == 0 && abs(yt-y3)%2 == 0) cout << "YES\n";  
         else cout << "NO\n";
      } else {
         cout << "NO\n";
      }
      
      
      
   }
   
   
   return 0;
}