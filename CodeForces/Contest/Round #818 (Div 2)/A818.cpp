#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      int answer = n + n/2 * 2 + n/3 * 2;
      cout << answer << "\n";
   }
   
   return 0;
}