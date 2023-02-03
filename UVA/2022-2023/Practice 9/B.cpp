#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   long long n;
   int m;
   cin >> n >> m;
   
   //compress
   unordered_map<long long,int> compress;
   vector<long long> nums;
   vector<pair<long long,long long>> intervals;
   for(int k = 0; k < m; k++){
      long long a,b;
      cin >> a >> b;
      intervals.push_back(make_pair(a,b));
      
      if(compress.find(a) == compress.end()){
         compress[a] = -1;
         nums.push_back(a);
      }
      
      if(compress.find(b) == compress.end()){
         compress[b] = -1;
         nums.push_back(b);
      }
            
   }
   
   sort(nums.begin(),nums.end());
   
   for(int k = 0; k < nums.size(); k++){
      compress[nums[k]] = k;
   }
   
   long long answer = 0LL;
   
   int N = compress[nums[nums.size()-1]];
   vector<long long> dp(N+1,0LL);
   
   sort(intervals.begin(),intervals.end());
   long long curmax = 0LL;
   int i = 0;
   for(int k = 0; k < m; k++){
      while(i < compress[intervals[k].first]){
         curmax = max(dp[i],curmax);
         i++;
      }
      dp[compress[intervals[k].second]] = curmax + intervals[k].second - intervals[k].first+1;
      answer = max(answer,dp[compress[intervals[k].second]]);
   }
   
   cout << n-answer << endl;
   
   
   
   
   return 0;
}