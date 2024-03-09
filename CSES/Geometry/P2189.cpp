#include <bits/stdc++.h>

using namespace std;

struct Point{
   long long x;
   long long y;
};

long long crossproductz(Point v1, Point v2){
   return v1.x*v2.y - v2.x*v1.y;
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int q;
   cin >> q;
   
   for(int t = 0; t < q; t++){
      long long x1,y1,x2,y2,x3,y3;
      cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
      
      Point v1 = {x2-x1,y2-y1};
      Point v2 = {x3-x1,y3-y1};
      
      long long cp = crossproductz(v1,v2);
      if(cp > 0) cout << "LEFT" << endl;
      else if(cp < 0) cout << "RIGHT" << endl;
      else cout << "TOUCH" << endl;
   }
   
   return 0;
}