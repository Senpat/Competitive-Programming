#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   map<int,int> mp;
   mp[1] = 1;
   mp[5] = 5;
   mp[10] = 10;
   
   auto q = mp.upper_bound(4);
   q--;
   auto a2 = q->second;
   cout << a2 << endl;
   
   return 0;
}