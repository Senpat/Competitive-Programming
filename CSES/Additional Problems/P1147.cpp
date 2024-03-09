#include <bits/stdc++.h>
//sol from usaco guide
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<char>> board(n, vector<char>(m));
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j];
      }
   }
   
   int answer = 0;
   vector<int> height(m,0);
   
   int N = 1001;
   for(int k = 0; k < n; k++){
      
      vector<vector<int>> heightval(N,vector<int>());
      //update height
      for(int j = 0; j < m; j++){
         if(board[k][j] == '*'){
            height[j] = 0;
         } else {
            height[j]++;
         }
         
         heightval[height[j]].push_back(j);
      }
      
      //find max histogram
      multiset<int> sizes;
      
      set<pair<int,int>> intervals;
      //maintain and update intervals
      for(int h = N-1; h >= 1; h--){
         for(int i : heightval[h]){
            int l = i;
            int r = i;
            if(sizes.size() == 0){
               intervals.insert(make_pair(l,r));
               sizes.insert(1);
               continue;
            }
            
            pair<int,int> p = make_pair(l,r);
            
            auto ri = intervals.lower_bound(p);
            auto li = intervals.lower_bound(p);
            li--;
            
            //ri != intervals.begin() means li is valid
            if(ri != intervals.begin() && (*li).second == i-1){
               //join left
               auto pl = *li;
               l = pl.first;
               sizes.erase(sizes.find(pl.second-pl.first+1));
               intervals.erase(li);
            }
            
            if(ri != intervals.end() && (*ri).first == i+1){
               //join right
               auto pr = *ri;
               r = pr.second;
               sizes.erase(sizes.find(pr.second-pr.first+1));
               intervals.erase(ri);
            }
            
            intervals.insert(make_pair(l,r));
            sizes.insert(r-l+1);
            
            
         }
         
         if(sizes.size() > 0){
            answer = max(answer,h**sizes.rbegin());
         }
      }
      
   }
   
   cout << answer << endl;
   
   
   return 0;
}