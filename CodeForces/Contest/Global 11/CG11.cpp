#include <bits/stdc++.h>

using namespace std;

const int MAX = 100005;
int dp[MAX];



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int r;
   int n;
   cin >> r >> n;
   
   vector<pair<int,pair<int,int>>> array;
   //1 index
   array.push_back(make_pair(0,make_pair(0,0)));
   array.push_back(make_pair(0,make_pair(1,1)));
   for(int k = 0; k < n; k++){
      int t;
      int x;
      int y;
      cin >> t >> x >> y;
      array.push_back(make_pair(t,make_pair(x,y)));
   }
   
   for(int k = 0; k < MAX; k++){
      dp[k] = -1;
   }
   dp[1] = 0;
   
   int answer = 0;
   int maxbefore = -1;
   for(int k = 2; k <= n+1; k++){
      //check first 1000
      int curmax = maxbefore;
      for(int j = max(k-1005,1); j < k; j++){
         // check if eligible
         //cout << j << " " << k << " " << abs(array[k].second.first-array[j].second.first) + abs(array[k].second.second-array[j].second.second) << " " << array[k].first - array[j].first+1 << endl;
         if(abs(array[k].second.first-array[j].second.first) + abs(array[k].second.second-array[j].second.second) <= array[k].first - array[j].first){
            curmax = max(curmax,dp[j]);
         }
      }
      if(k > 1002){
         maxbefore = max(maxbefore,dp[k-1002]);
      }
      if(curmax != -1){
         dp[k] = curmax+1;
      }
      //cout << dp[k] << endl;
      answer = max(answer,dp[k]);
   }
   
   cout << answer << endl;
   
   
   
   
   
   
   
   return 0;
}