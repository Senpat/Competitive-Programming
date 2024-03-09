#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int r,c,d,n;
   cin >> r >> c >> d >> n;
   
   vector<pair<int,int>> circles(n);
   for(int k = 0; k < n; k++){
      cin >> circles[k].first >> circles[k].second;
   }
   
   if(r > n*(d*2+1)){
      //too many rows
      cout << 0 << endl;
      return 0;
   }
   
   vector<int> width(d+1);
   for(int x = 0; x <= d; x++){
      //max integer y such that x^2 + y^2 <= d^2
      int sq = d*d - x*x;
      int y = 0;
      while((y+1)*(y+1) <= sq) y++;
      width[x] = y;
   }
   
   vector<vector<pair<int,int>>> intervals(r+1,vector<pair<int,int>>());
   vector<set<int>> cset(r+1);
   
   for(int k = 0; k < n; k++){
      for(int x = 0; x <= d; x++){
         pair<int,int> p = {max(1,circles[k].second - width[x]), min(c,circles[k].second + width[x])};
         
         if(circles[k].first+x <= r){
            intervals[circles[k].first + x].push_back(p);
         }
         if(x > 0){
            if(circles[k].first - x >= 1){
               intervals[circles[k].first - x].push_back(p);
            }
         }
      }
      
      cset[circles[k].first].insert(circles[k].second);
   }
   
   int answer = INT_MAX;
   for(int row = 1; row <= r; row++){
      priority_queue<int,vector<int>,greater<int>> add;
      priority_queue<int,vector<int>,greater<int>> sub;
      
      for(auto [l,r] : intervals[row]){
         add.push(l);
         if(r+1 <= c){
            sub.push(r+1);
         }
      }
      
      //do 1
      int curin = 0;
      while(!add.empty() && add.top() == 1){
         curin++;
         add.pop();
      }
      while(!sub.empty() && sub.top() == 1){
         curin--;
         sub.pop();
      }
      
      if(cset[row].find(1) != cset[row].end()){
         //taken
         if(2 <= c){
            add.push(2);
            sub.push(2);
         }
      } else {
         answer = min(answer,curin);
      }
      while(!add.empty() || !sub.empty()){
         int t = -1;
         if(add.empty()){
            t = sub.top();
         } else if(sub.empty()){
            t = add.top();
         } else {
            t = min(add.top(),sub.top());
         }
         
         while(!add.empty() && add.top() == t){
            curin++;
            add.pop();
         }
         
         while(!sub.empty() && sub.top() == t){
            curin--;
            sub.pop();
         }
         
         if(cset[row].find(t) != cset[row].end()){
            //someone is sitting here, add in add and sub to trigger next one
            if(t+1 <= c){
               add.push(t+1);
               sub.push(t+1);
            }
         } else {
            //cout << row << " " << t << " " << curin << endl;
            answer = min(answer,curin);
         }
      }
      
   }
   
   cout << answer << endl;
   
   
   return 0;
}