#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      long long m;
      cin >> n >> m;
      
      vector<pair<long long,long long>> rain(n);
      priority_queue<pair<long long,int>,vector<pair<long long,int>>,std::greater<pair<long long,int>>> pq;            //2nd value is type (beginning, peak, end). //sort by first value
      for(int k = 0; k < n; k++){
         long long x,p;
         cin >> x >> p;
         rain[k] = make_pair(x,p);
         
         pq.push(make_pair(x-p+1,1));
         pq.push(make_pair(x,2));
         pq.push(make_pair(x+p,3));
      }
      
      //stores indeces that go over m - pair(index,value)
      vector<pair<long long,long long>> over;
      
      long long x = 0LL;
      long long y = 0LL;
      long long up = 0LL;
      long long down = 0LL;
      
      //first one
      x = pq.top().first;
      
      long long changeup = 0LL;
      long long changedown = 0LL;
      while(!pq.empty() && pq.top().first == x){
         auto p = pq.top();
         pq.pop();
         
         if(p.second == 1){
            up++;
         }
         
         if(p.second == 2){
            changedown++;
            changeup--;
         } 
         else if(p.second == 3){
            changedown--;
         }
      }
      
      y += up-down;
      up += changeup;
      down += changedown;
      
      if(y > m){
         over.push_back(make_pair(x,y));
      }
      //cout << x << " " << y << endl;
      while(!pq.empty()){
         int curx = pq.top().first;
         
         y += (up-down)*(curx-x);
         
         changeup = 0LL;
         changedown = 0LL;
         while(!pq.empty() && pq.top().first == curx){
            auto p = pq.top();
            pq.pop();
         
            if(p.second == 1){
               y++;
               changeup++;
            }
         
            if(p.second == 2){
               changedown++;
               changeup--;
            } 
            else if(p.second == 3){
               changedown--;
            }
         }
         
         up += changeup;
         down += changedown;
         
         if(y > m){
            over.push_back(make_pair(curx,y));
         }
          
         x = curx;
         
         //cout << x << " " << y << endl;
         //cout << "Up: " << up << " and Down: " << down << endl;
      }
      
      if(over.size() == 0){
         for(int k = 0; k < n; k++){
            cout << "1";
         }
         cout << "\n";
         continue;
      }
      
      long long left = LLONG_MAX;
      long long right = LLONG_MIN;
      for(const auto& p : over){
         left = min(left,p.first-(p.second-m)+1);
         right = max(right, p.first+(p.second-m)-1);
      }
      //cout << left << " " << right << endl;
      vector<int> answer(n);
      for(int k = 0; k < n; k++){
         if(rain[k].first-rain[k].second+1 <= left && rain[k].first+rain[k].second-1 >= right){
            answer[k] = 1;
         } else {
            answer[k] = 0;
         }
      }
      
      for(int k = 0; k < n; k++){
         cout << answer[k];
      }
      cout << "\n";
         
      
      
      
   }
   
   
   
   
   return 0;
}