#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<vector<int>> input;
   unordered_set<int> uset;
   for(int k = 0; k < n; k++){
      int a,b,c;
      cin >> a >> b >> c;
      
      uset.insert(a);
      uset.insert(b);
      
      input.push_back({a,b,c});
   }
   
   vector<int> vec;
   for(int i : uset){
      vec.push_back(i);
   }
   
   sort(vec.begin(),vec.end());
   
   unordered_map<int,int> compress;
   compress.reserve(1024);
   compress.max_load_factor(0.25);
   
   int N = vec.size();
   for(int k = 0; k < N; k++){
      compress[vec[k]] = k+1;
   }
   
   vector<vector<pair<int,long long>>> starts(N+1,vector<pair<int,long long>>());
   
   for(int k = 0; k < n; k++){
      starts[compress[input[k][1]]].push_back(make_pair(compress[input[k][0]],input[k][2]));
   }
   
   vector<long long> dp(N+1);
   dp[0] = 0LL;
   
   for(int k = 1; k <= N; k++){
      dp[k] = dp[k-1];
      
      for(auto p : starts[k]){
         dp[k] = max(dp[k],dp[p.first-1] + p.second);
      }
   }
   
   cout << dp[N] << endl;
   
   
   
   
   return 0;
}