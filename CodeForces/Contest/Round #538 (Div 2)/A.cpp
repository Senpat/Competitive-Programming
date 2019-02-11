#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int x,y,z;
   cin >> x >> y >> z;
   
   int a,b,c;
   cin >> a >> b >> c;
   
   if(a < x){
      cout << "NO" << endl;
      return 0;
   }
   
   x -= a;
   
   int xy = x + y;
   
   if(b < xy){
      cout << "NO" << endl;
      return 0;
   }
   
   xy -= b;
   
   int xyz = xy + z;
   
   if(c < xyz){
      cout << "NO" << endl;
      return 0;
   }
   cout << "YES" << endl;
   
   
   
   
   return 0;
}