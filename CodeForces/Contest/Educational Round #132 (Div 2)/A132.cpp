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
      int a,b,c;
      cin >> a >> b >> c;
      int sum = a + b + c;
      if(a == 0) a = 6-sum;
      if(b == 0) b = 6-sum;
      if(c == 0) c = 6-sum;
      
      if(a == 1 || b == 2 || c == 3) cout << "No" << endl;
      else cout << "Yes" << endl;
   }
   
   
   
   
   return 0;
}