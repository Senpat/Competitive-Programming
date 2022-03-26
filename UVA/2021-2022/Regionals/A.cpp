#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   int n, s; cin >> n >> s;
   int total = s;
   int ans = 0;
   for(int i = 0; i < n; ++i){
      string shot; cin >> shot;
      int need = shot[0] - '0';
      if((int)shot.size() == 2)
         ++need;
      if(need > s){
         ++ans;
         s = total;
      }
      s -= need;
   }
   cout << ans;
   return 0;
}