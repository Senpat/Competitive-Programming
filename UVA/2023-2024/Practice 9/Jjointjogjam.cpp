#include <bits/stdc++.h>

using namespace std;

double dist(double x1, double y1, double x2, double y2){
   return sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   double sx1,sy1,sx2,sy2;
   double ex1,ey1,ex2,ey2;
   cin >> sx1 >> sy1 >> sx2 >> sy2 >> ex1 >> ey1 >> ex2 >> ey2;
   
   double answer = max(dist(sx1,sy1,sx2,sy2),dist(ex1,ey1,ex2,ey2));
   cout << setprecision(12) << answer << endl;
   
   
   return 0;
}