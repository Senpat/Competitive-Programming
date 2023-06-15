#include <bits/stdc++.h>

using namespace std;

struct movie{
   int start;
   int end;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n;
   cin >> n;
   
   unordered_set<int> hset;
   vector<int> alist; 
   vector<movie> movies;
   for(int k = 0; k < n; k++){
      int start,end;
      cin >> start >> end;
      
      movies.push_back({start,end});
      
      if(hset.find(start) == hset.end()){
         hset.insert(start);
         alist.push_back(start);
      }
      
      if(hset.find(end) == hset.end()){
         hset.insert(end);
         alist.push_back(end);
      }
   }
   
   sort(alist.begin(),alist.end());
   
   int N = alist.size();
   unordered_map<int,int> compress;
   for(int k = 0; k < N; k++){
      compress[alist[k]] = k+1;
   }
   
   vector<int> endtimes(N+1);
   for(int k = 0; k < n; k++){
      endtimes[compress[movies[k].end]] = max(endtimes[compress[movies[k].end]],compress[movies[k].start]);
   }
   
   vector<int> dp(N+1);
   dp[0] = -1;
   for(int k = 1; k <= N; k++){
      dp[k] = max(dp[k-1],dp[endtimes[k]]+1);
   }
   
   cout << dp[N] << endl;
   
   
   return 0;
}