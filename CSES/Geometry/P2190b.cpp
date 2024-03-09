#include <bits/stdc++.h>
//improved code based on errichto youtube video
//not simpler
using namespace std;

struct Point{
   long long x;
   long long y;
};

long long crossproductz(Point v1, Point v2){
   return v1.x*v2.y - v2.x*v1.y;
}

long long sign(long long x){
   if(x == 0LL) 
      return 0LL;
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
      Point pt1 = p1;
      Point pt2 = p2;
      Point pt3 = ls.p1;
      Point pt4 = ls.p2;
      
      Point v1,v2,v3;
      
      v1 = pdiff(pt2,pt1);
      v2 = pdiff(pt3,pt1);
      v3 = pdiff(pt4,pt1);
      if(crossproductz(v1,v2) == 0LL && crossproductz(v1,v3) == 0LL){
         //lines are collinear
         //compare rectangles
         auto r1 = get_rect();
         auto r2 = ls.get_rect();
         
         //rectangles need to intersect
         if((get<1>(r1) < get<0>(r2)) || (get<1>(r2) < get<0>(r1))) 
            return false;
         if((get<3>(r1) < get<2>(r2)) || (get<3>(r2) < get<2>(r1))) 
            return false;
         
         return true;
      }
      
      for(int rep = 0; rep < 2; rep++){
         
         v1 = pdiff(pt2,pt1);
         v2 = pdiff(pt3,pt1);
         v3 = pdiff(pt4,pt1);
         
         long long s1 = crossproductz(v1,v2);
         long long s2 = crossproductz(v1,v3);
         
         if((s1 < 0LL && s2 < 0LL) || (s1 > 0LL && s2 > 0LL)){
            return false;
         }
         
         swap(pt1,pt3);
         swap(pt2,pt4);
      }
      
      return true;
   }
   
   tuple<long long, long long, long long, long long> get_rect() const{
      return {min(p1.x,p2.x),max(p1.x,p2.x),min(p1.y,p2.y),max(p1.y,p2.y)};
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
      } 
      else {
         cout << "NO" << endl;
      }
   }
   
   return 0;
}