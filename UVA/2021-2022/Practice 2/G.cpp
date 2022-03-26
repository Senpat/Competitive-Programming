#include <bits/stdc++.h>

using namespace std;
string v;
vector<string> pos;
void backtrack(int curr, string &res){
   if(!curr){
      pos.emplace_back(res);
      return;
   }
   for(int i = 0; i < int(v.size()); ++i){
      res += v[i];
      backtrack(curr - 1, res);
      res.pop_back();
   }
}
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   cin >> v;
   unordered_set<string> subs;
   string s; cin >> s;
   int n = s.size();
   for(int i = 0; i < n; ++i){
      for(int j = 1; j <= 5; ++j){
         if(i + j <= n)
            subs.insert(s.substr(i, j));
      }
   }
   int ans = 0;
   for(int i = 1; i <= 5; ++i){
      string res = "";
      backtrack(i, res);
   }
   for(int i = 0; i < int(pos.size()); ++i){
      if(!subs.count(pos[i])){
         ans = int(pos[i].size());
         break;
      }
   }
   cerr << ans << '\n';
   int q; cin >> q;
   while(q--){
      string t; cin >> t;
      if(int(t.size()) != ans){
         cout << 0 << '\n';
         continue;
      }
      cout << (subs.count(t) ? 0 : 1) << '\n';
   }  
   return 0;
}