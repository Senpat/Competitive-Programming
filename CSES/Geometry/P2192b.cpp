#include <bits/stdc++.h>
//errichto youtube implementation
//simpler implementation
using namespace std;

struct Point{
   long long x;
   long long y;
};

//>0 is ccw, <0 is cw, 0 is on the line
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
   
   long long minx;
   long long maxx;
   long long miny;
   long long maxy;
   
   LineSegment(Point p1_, Point p2_) : p1(p1_), p2(p2_),
                                       minx(min(p1_.x,p2_.x)), maxx(max(p1_.x,p2_.x)),
                                       miny(min(p1_.y,p2_.y)), maxy(max(p1_.y,p2_.y)) {}
                                       
   
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
   
   //returns true p is on the line segment
   bool contains(Point p){
      Point v1 = pdiff(p,p1);
      Point v2 = pdiff(p,p2);
      
      if(crossproductz(v1,v2) != 0) return false;
      
      //collinear, check if p is in the bounding box
      return minx <= p.x && p.x <= maxx &&
             miny <= p.y && p.y <= maxy;
   }
   
   //checks if the line segment is "above" p
   //imagine a line going straight up from p, tilted by epsilon to the right
   // so that it doesn't intersect
   //if an odd # of segments in a polygon return true, the point is inside the polygon (use contains to check if it is on the boundary)
   bool aboveeps(Point p){
      if(p1.x <= p.x && p2.x <= p.x) return false;    //left
      if(p1.x > p.x && p2.x > p.x) return false;      //right
      
      if(p1.x < p2.x){
         Point v1 = pdiff(p2,p1);
         Point v2 = pdiff(p,p1);
         return crossproductz(v1,v2) < 0;
      } else {
         Point v1 = pdiff(p1,p2);
         Point v2 = pdiff(p,p2);
         return crossproductz(v1,v2) < 0;
      }
      
   }
};


struct Polygon{
   vector<Point> points;
   
   //prints 2 * the area (guarantees the answer is an integer)
   //points are already sorted
   //shoelace theorem
   long long area2(){
      long long ret = 0LL;
      
      for(int k = 0; k < points.size()-1; k++){
         ret += points[k].x * points[k+1].y;
         ret -= points[k].y * points[k+1].x;
      }
      ret += points[points.size()-1].x * points[0].y;
      ret -= points[points.size()-1].y * points[0].x;
      
      //should divide by 2 to get the actual area
      return abs(ret);
   }
};





int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   vector<Point> points(n);
   vector<LineSegment> ls;
   for(int k = 0; k < n; k++){
      cin >> points[k].x >> points[k].y;
      
      if(k > 0){
         ls.push_back(LineSegment(points[k],points[k-1]));
      }
   }
   ls.push_back(LineSegment(points[0],points[n-1]));
   
   for(int t = 0; t < q; t++){
      long long a,b;
      cin >> a >> b;
      Point p = {a,b};
      
      //pretend there is a line starting from p going straight up, to the right by epsilon (so that there are no collinear points)
      bool boundary = false;
      int count = 0;
      for(int k = 0; k < n; k++){
         if(ls[k].contains(p)){
            boundary = true;
            break;
         }
         
         if(ls[k].aboveeps(p)){
            count++;
         }
      }
      
      if(boundary){
         cout << "BOUNDARY\n";
      } else if(count % 2 == 0){
         cout << "OUTSIDE\n";
      } else {
         cout << "INSIDE\n";
      }
   }
   
   
   
   
   
   return 0;
}