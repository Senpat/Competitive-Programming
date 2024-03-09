#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   vector<vector<int>> next(n,vector<int>(26,-1));
   
   for(int k = n-2; k >= 0; k--){
      for(int j = 0; j < 26; j++){
         next[k][j] = next[k+1][j];
      }
      int c = s[k+1]-'a';
      next[k][c] = k+1;
   }
   
   vector<int> start(26,-1);
   for(int k = 0; k < 26; k++){
      start[k] = next[0][k];
   }
   start[s[0]-'a'] = 0;
   
   vector<long long> dp(n,0LL);
   
   for(int k = 0; k < 26; k++){
      if(start[k] != -1){
         dp[start[k]] = 1LL;
      }
   }
   
   long long answer = 0LL;
   for(int k = 0; k < n; k++){
      answer = (answer + dp[k] + MOD)%MOD;
      
      for(int j = 0; j < 26; j++){
         if(next[k][j] != -1){
            dp[next[k][j]] = (dp[next[k][j]] + dp[k] + MOD)%MOD;
         }
      }
   }
   
   cout << answer << endl;
   
   return 0;
}