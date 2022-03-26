#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   int p, q; cin >> p >> q;
   string seq; cin >> seq;
   int a_1 = -1, a_2 = -1;
   for(int i = 0; i < 4; ++i){
      if(seq[i] == 'A'){
         if(a_1 == -1)
            a_1 = i;
         else
            a_2 = i;
      }
   }
   set<pair<int,int>> bval;
   for(int i = 1000; i <= 9999; ++i){
      string s = to_string(i);
      bool good = 1;
      for(int j = 1; j < 4; ++j){
         if(s[j] <= s[j-1])
            good = 0;
      }
      if(s[a_1] - '0' != p || s[a_2] - '0' != q)
         good = 0;
      if(!good)
         continue;
      int fst = -1, sec = -1;
      for(int j = 0; j < 4; ++j){
         if(j != a_1 && j != a_2){
            if(fst == -1)
               fst = s[j] - '0';
            else
               sec = s[j] - '0';
         }
      }
      bval.insert({fst, sec});
   }
   if((int)bval.size() > 1 || !(int)bval.size()){
      cout << -1;
      return 0;
   }
   auto it = *bval.begin();
   cout << it.first << ' ' << it.second;
   return 0;
}