#include <bits/stdc++.h>

using namespace std;

struct Circle{
   double x,y,r;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   vector<Circle> circle;
   for(int k = 0; k < n; k++){
      double x,y,r;
      cin >> x >> y >> r;
      circle.push_back({x,y,r});
   }
   
   double STEP = 0.01;
   double answer = 0.0;
   for(double x = -10.0; x <= 20.0; x += STEP){
      for(double y = -10.0; y <= 20.0; y += STEP){
         bool found = false;
         for(int k = 0; k < n; k++){
            double d = (x-circle[k].x)*(x-circle[k].x) + (y-circle[k].y)*(y-circle[k].y);
            if(d <= circle[k].r*circle[k].r){
               found = true;
               break;
            }
         }
         
         if(found){
            answer += STEP*STEP;
         }
      }
   }
   
   cout << answer << endl;
   
   return 0;
}