#include <bits/stdc++.h>

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
   
   LineSegment(Point p1_, Point p2_) : p1(p1_), p2(p2_){
      minx = min(p1.x,p2.x);
      maxx = max(p1.x,p2.x);
      miny = min(p1.y,p2.y);
      maxy = max(p1.y,p2.y);
   }
   
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
   
   //1 if p is above the linesegment, -1 if p is below, 0 if p is along the line segment
   //UNDEFINED FOR VERTICAL LINE SEGMENT
   int above(Point p){
      if(p1.x < p2.x)
      return sign(crossproductz(pdiff(p2,p1),pdiff(p,p2)));    
      else
      return sign(crossproductz(pdiff(p1,p2),pdiff(p,p1)));
   }
   
   //1 if p is left of the linesegment, -1 if p is right, 0 if p is along the line segment
   //UNDEFINED FOR HORIZONTAL LINE SEGMENT
   int left(Point p){
      if(p1.y < p2.y)
      return sign(crossproductz(pdiff(p2,p1),pdiff(p,p2)));    
      else
      return sign(crossproductz(pdiff(p1,p2),pdiff(p,p1)));
   
   }
};

struct Polygon{
   int n;
   vector<Point> points;
   vector<LineSegment> segments;
   
   Polygon(const vector<Point>& points_) : points(points_){
      n = points.size();
      for(int k = 0; k < n-1; k++){
         segments.push_back(LineSegment(points[k],points[k+1]));
      }
      segments.push_back(LineSegment(points[n-1],points[0]));
   }
   
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
   
   //returns 1 if inside, 0 if on the boundary, -1 if outside
   int inside(Point p){
      int u = 0;
      int d = 0;
      int r = 0;
      int l = 0;
      
      int lu = 0;
      int ld = 0;
      int ru = 0;
      int rd = 0;
      int ul = 0;
      int ur = 0;
      int dl = 0;
      int dr = 0;
      
      for(int k = 0; k < n; k++){
         //cout << segments[k].p1.x << " " << segments[k].p2.x << " " << segments[k].p1.y << " " << segments[k].p2.y << endl;
         //count line segments
         if(segments[k].minx < p.x && p.x < segments[k].maxx){
            int on = segments[k].above(p);
            if(on > 0) d++;            //if point is above the line then the line is in the down direction
            else if(on < 0) u++;
            else return 0;
         }
         if(segments[k].miny < p.y && p.y < segments[k].maxy){
            int on = segments[k].left(p);
            //cout << on << endl;
            if(on > 0) r++;
            else if(on < 0) l++;
            else return 0;
         }
         
         //account for points
         if(p.x == points[k].x && p.y == points[k].y){
            return 0;
         } else {
            for(int si = -1; si <= 0; si++){
               int s = k+si;
               if(s < 0) s += n;
               
               if(p.x == points[k].x){
                  //see if the other coordinate is on left or right
                  long long other = segments[s].p1.x + segments[s].p2.x - p.x;
                  if(other > p.x){
                     if(points[k].y > p.y) ur++;
                     else dr++;
                  } else if(other < p.x){
                     if(points[k].y > p.y) ul++;
                     else dl++;
                  }
               } else if(p.y == points[k].y){
                  long long other = segments[s].p1.y + segments[s].p2.y - p.y;
                  if(other > p.y){
                     if(points[k].x > p.x) ru++;
                     else lu++;
                  } else if(other < p.y){
                     if(points[k].x > p.x) rd++;
                     else ld++;
                  }
               }
            }
         }  
      }
      
      if((lu&1) == 1 && (ld&1) == 1) l++;
      if((ru&1) == 1 && (rd&1) == 1) r++;
      if((ur&1) == 1 && (ul&1) == 1) u++;
      if((dr&1) == 1 && (dl&1) == 1) d++;
      
      //cout << lu << " " << ld << endl;
      //cout << ru << " " << rd << endl;
      //cout << l << " " << r << " " << u << " " << d << endl;
      if((l&1) == 0 || (r&1) == 0 || (u&1) == 0 || (d&1) == 0){
         return -1;
      } else {
         return 1;
      }
   }
};





int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<Point> points(n);
   for(int k = 0; k < n; k++){
      cin >> points[k].x >> points[k].y;
   }
   
   Polygon polygon = {points};
   
   for(int k = 0; k < m; k++){
      long long x,y;
      cin >> x >> y;
      
      Point point = {x,y};
      
      int ret = polygon.inside(point);
      if(ret == 1){
         cout << "INSIDE\n";
      } else if(ret == -1){
         cout << "OUTSIDE\n";
      } else {
         cout << "BOUNDARY\n";
      }
   }
   
   
   
   return 0;
}