#include <bits/stdc++.h>

using namespace std;

struct Point{
   long long x;
   long long y;
};

long long crossproductz(Point v1, Point v2){
   return v1.x*v2.y - v2.x*v1.y;
}

long long sign(long long x){
   if(x == 0LL) return 0LL;
   return x/abs(x);
}

//p1 - p2
Point pdiff(Point p1, Point p2){
   return {p1.x-p2.x,p1.y-p2.y};
}

struct LineSegment{
   Point p1;
   Point p2;
   
   //see if the endpoints of the line segment are on opposite sides
   bool intersects(LineSegment ls){
      Point ls1 = pdiff(p2,p1);
      Point ls2 = pdiff(ls.p2,ls.p1);
      
      Point v11 = pdiff(ls.p1,p2);
      Point v12 = pdiff(ls.p2,p2);
      
      Point v21 = pdiff(p1,ls.p2);
      Point v22 = pdiff(p2,ls.p2);
      
      long long cp11 = crossproductz(ls1,v11);
      long long cp12 = crossproductz(ls1,v12);
      
      long long cp21 = crossproductz(ls2,v21);
      long long cp22 = crossproductz(ls2,v22);
      
      if(cp11 == 0LL && cp12 == 0LL){
         //all 4 points are collinear
         if(p1.x == p2.x){
            //use y
            return max(p1.y,p2.y) >= min(ls.p1.y,ls.p2.y) && max(ls.p1.y,ls.p2.y) >= min(p1.y,p2.y);
         } else {
            return max(p1.x,p2.x) >= min(ls.p1.x,ls.p2.x) && max(ls.p1.x,ls.p2.x) >= min(p1.x,p2.x);
         }
      }
      
      return sign(cp11) != sign(cp12) && sign(cp21) != sign(cp22);
   }
};


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int q;
   cin >> q;
   
   for(int t = 0; t < q; t++){
      long long x1,y1,x2,y2,x3,y3,x4,y4;
      cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4;
      
      LineSegment ls1 = {{x1,y1},{x2,y2}};
      LineSegment ls2 = {{x3,y3},{x4,y4}};
      
      if(ls1.intersects(ls2)){
         cout << "YES" << endl;
      } else {
         cout << "NO" << endl;
      }
   }
   
   return 0;
}