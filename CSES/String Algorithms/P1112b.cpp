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
   
   //jump[x][y] -> how many correct letters you have if you had m correct letters and put character y
   vector<vector<int>> jump(m,vector<int>(26,0));
   //cout << jump[0].size() << endl << flush;
   jump[0][s[0]-'A'] = 1;
   for(int k = 1; k < m; k++){
      //cout << jump[0].size() << endl << flush;
      for(int j = 0; j < 26; j++){
         if(j == s[k]-'A'){
            jump[k][j] = k+1;
         } else {
            
            for(int ans = k; ans >= 1; ans--){
               bool fail = false;
               
               int start = k+1-ans;
               for(int h = 0; h < ans-1; h++){
                  if(s[h] != s[start+h]){
                     fail = true;
                     break;
                  }
               }
               
               
               if(!fail && s[ans-1]-'A' == j){
                  jump[k][j] = ans;
                  break;
               }
            }
         
         }
      }
   }
   
   //cout << "here" << endl << flush;
   //cout << jump.size() << " " << jump[0].size() << endl << flush;
   
   vector<vector<long long>> dp(n,vector<long long>(m+1,0LL));
   
   
   dp[0][1] = 1LL;
   dp[0][0] = 25LL;
   
   for(int k = 0; k < n-1; k++){
      for(int j = 0; j < m; j++){
         
         for(int c = 0; c < 26; c++){
            //cout << k << " " << j << " " << c << endl << flush;
            //cout << jump[j][c] << endl << flush;
            dp[k+1][jump[j][c]] = (dp[k+1][jump[j][c]] + dp[k][j] + MOD)%MOD;
         }
            
      }
      dp[k+1][m] = (dp[k+1][m] + dp[k][m] * 26LL + MOD)%MOD;
   }
   
   cout << dp[n-1][m] << endl;
   
   
   return 0;
}