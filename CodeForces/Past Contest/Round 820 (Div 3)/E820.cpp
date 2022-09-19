#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   for(int k = 2; k <= 26; k++){
      long long q1,q2;
      cout << "? 1 " << k << endl;
      cin >> q1;
      cout << "? " << k << " 1" << endl;
      cin >> q2;
      
      if(q1 == -1 || q2 == -1){
         cout << "! " << k-1 << endl;
         return 0;
      } else if(q1 != q2){
         cout << "! " << q1 + q2 << endl;
         return 0;
      }
   }
   
   cout << "fail" << endl;
   
   return 0;
}