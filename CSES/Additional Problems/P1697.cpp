#include <bits/stdc++.h>
//greedy solution
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   vector<int> array(n+1);
   for(int k = 1; k <= n; k++){
      cin >> array[k];
   }
   
   set<pair<int,int>> uset;
   for(int k = 1; k <= n; k++){
      uset.insert(make_pair(array[k],k));
   }
   
   bool fail = false;
   
   vector<pair<int,int>> answer;
   
   for(int k = 0; k < n; k++){
      auto curp = *uset.begin();
      uset.erase(curp);
      //cout << curp.first << " " << curp.second << endl;
      int i = 0;
      vector<pair<int,int>> process;
      while(i < curp.first){
         if(uset.empty()){
            fail = true;
            break;
         }
         auto m = *uset.rbegin();
         uset.erase(m);
         i++;
         answer.push_back({curp.second,m.second});
         process.push_back({m.first-1,m.second});
      }
      if(fail){
         break;
      }
      
      for(const auto& p : process){
         uset.insert(p);
      }
   }
   
   if(fail){
      cout << "IMPOSSIBLE\n";
   } else {
      cout << answer.size() << "\n";
      for(const auto& p : answer){
         cout << p.first << " " << p.second << "\n";
      }
   }
   
   return 0;
}