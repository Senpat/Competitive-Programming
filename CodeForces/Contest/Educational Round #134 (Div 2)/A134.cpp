#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      char a,b,c,d;
      cin >> a >> b >> c >> d;
      unordered_set<char> uset;
      uset.insert(a);
      uset.insert(b);
      uset.insert(c);
      uset.insert(d);
      
      if(uset.size() == 1) cout << 0 << endl;
      else if(uset.size() == 2) cout << 1 << endl;
      else if(uset.size() == 3) cout << 2 << endl;
      else cout << 3 << endl;
   }
   
   
   return 0;
}