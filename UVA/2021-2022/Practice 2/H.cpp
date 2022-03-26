#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   int t; cin >> t;
   while(t--){
      string s; cin >> s;
      int n = s.size();
      bool good = 0;
      for(int start = 0; start < 7; ++start){
         int curr = start;
         unordered_set<char> seen;
         bool flag = 1;
         for(int i = 0; i < n; ++i){
            if(curr % 7 == 0){
               seen.clear();
            }
            if(seen.count(s[i])){
               flag = 0;
               break;
            }
            ++curr;
            seen.insert(s[i]);
         }
         good = good || flag;
      }
      cout << (good ? 1 : 0) << '\n';
   }
   return 0;
}