#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int q;
   cin >> q;
   
   for(int k = 0; k < q; k++){
      int l1,r1,l2,r2;
      cin >> l1 >> r1 >> l2 >> r2;
      
      if(l1 == l2){
         cout << l1 << " " << r2 << "\n";
      } else {
         cout << l1 << " " << l2 << "\n";
      }
   }
   
   return 0;
}