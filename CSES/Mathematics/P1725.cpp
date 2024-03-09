#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,a,b;
   cin >> n >> a >> b;
   
   vector<vector<double>> dp(n,vector<double>(6*n+1,0.0));
   for(int k = 1; k <= 6; k++){
      dp[0][k] = 1.0/6.0;
   }
   
   for(int k = 0; k < n-1; k++){
      for(int j = 0; j <= 6*n; j++){
         if(dp[k][j] == 0.0) continue;
         for(int h = 1; h <= 6; h++){
            dp[k+1][j+h] += dp[k][j]/6.0;
         }
      }
   }
   
   double answer = 0.0;
   for(int k = a; k <= b; k++){
      answer += dp[n-1][k];
   }
   
   string s = to_string(round(answer*1000000.0)/1000000.0);
   if(s.length() == 1) s += '.';
   while(s.length() < 8) s += '0';
   
   cout << s << endl;
   
   return 0;
}