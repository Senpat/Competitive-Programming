#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,xx,yy;
      cin >> n >> xx >> yy;
      
      int x = min(xx,yy);
      int y = max(xx,yy);
      
      if(x != 0 || y == 0){
         cout << -1 << "\n";
         continue;
      }
      
      if((n-1) % y != 0){
         cout << -1 << "\n";
         continue;
      }
      
      vector<int> answer(n,0);
      for(int k = 0; k < n; k++){
         answer[k] = (k/y)*y+2;
      }
      
      for(int k = 0; k < n-1; k++){
         cout << answer[k] << " ";
      }
      cout << "\n";   
   
   }
   
   
   
   return 0;
}