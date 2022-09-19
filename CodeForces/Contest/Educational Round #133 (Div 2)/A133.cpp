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
      int answer = (n+2)/3;
      if(n == 1) answer = 2;
      cout << answer << "\n";
   }
   
   
   
   
   return 0;
}