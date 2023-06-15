#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n;
   cin >> n;
   string s;
   cin >> s;
   int m = s.size();
   
   vector<vector<int>> same(m,vector<int>());
   
   for(int k = 0; k < m; k++){
      same[k].push_back(k);
      for(int j = 0; j < k; j++){
         bool fail = false;
         for(int h = j; h >= 0; h--){
            if(s[h] != s[k-(h-j)]){
               fail = true;
               break;
            }  
         }  
         if(!fail){
            same[k].push_back(j);
         }
      }
   }
   
   /*
   for(int k = 0; k < m; k++){
      cout << k << ": ";
      for(int i : same[k]){
         cout << i << " ";
      }
      cout << endl;
   }
   */
   vector<vector<int>> jump(m,vector<int>(26,0));
   
   int s0 = s[0]-'A';
   jump[0][s0] = 1;
   for(int k = 1; k < m; k++){
      for(char c = 'A'; c <= 'Z'; c++){
         for(int i : same[k-1]){
            if(s[i+1] == c){
               jump[k][c-'A'] = i+2;
               break;
            }
         }
         
         if(c == s[0] && jump[k][c-'A'] == 0){
            jump[k][c-'A'] = 1;
         }
         
      }
   }
   
   /*
   for(int k = 0; k < m; k++){
      for(int j = 0; j < 26; j++){
         cout << jump[k][j] << " ";
      }
      cout << endl;
   }
   cout << flush;
   */
   vector<vector<long long>> dp(n,vector<long long>(m+1));
   
   
   dp[0][1] = 1LL;
   dp[0][0] = 25LL;
   
   for(int k = 0; k < n-1; k++){
      for(int j = 0; j < m; j++){
         
         for(int c = 0; c < 26; c++){
            dp[k+1][jump[j][c]] = (dp[k+1][jump[j][c]] + dp[k][j] + MOD)%MOD;
         }
            
      }
      dp[k+1][m] = (dp[k+1][m] + dp[k][m] * 26LL + MOD)%MOD;
   }
   
   cout << dp[n-1][m] << endl;
   
   /*
   for(int k = 0; k < n; k++){
      for(int j = 0; j <= m; j++){
         cout << dp[k][j] << " ";
      }
      cout << endl;
   }
   */
   
   
   return 0;
}