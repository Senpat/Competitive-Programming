#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   string s1;
   getline(cin,s1);
   int n = stoi(s1);
   int M = 200;
   
   vector<pair<int,int>> stations;
   
   while(true){
      string s;
      getline(cin,s);
      
      if(s.empty()) break;
      
      stringstream ss;
      ss << s;
      int a,b;
      ss >> a >> b;
      stations.push_back({a,b});
   }
   
   int g = stations.size();
   
   //for(int k = 0; k < g; k++){
   //   cout << stations[k].first << " " << stations[k].second << endl;
   //}
   
   int gi = 0;
   
   vector<vector<int>> dp(n+1,vector<int>(M+1,INT_MAX));
   
   dp[0][100] = 0;
   
   for(int k = 0; k <= n; k++){
      //cout << dp[k][100] << " ";
      while(gi < g && stations[gi].first == k){
         //cout << "looking at station " << gi << " " << k << endl;
         for(int j = 0; j <= M; j++){
            if(dp[k][j] == INT_MAX) continue;
            for(int h = 1; j+h <= M; h++){
               dp[k][j+h] = min(dp[k][j+h], dp[k][j] + h*stations[gi].second);
            }
         }
         gi++;
      }
      
      //cout << dp[k][100] << endl;
      if(k == n) continue;
      
      for(int j = 1; j <= M; j++){
         if(dp[k][j] == INT_MAX) continue;
         //cout << k << " " << j << " " << dp[k][j] << endl;
         dp[k+1][j-1] = min(dp[k+1][j-1],dp[k][j]);
      }
      
      
   }
   
   //cout << dp[99][1] << " " << dp[100][0] << endl;
   
   int answer = INT_MAX;
   for(int k = 100; k <= M; k++){
      answer = min(answer,dp[n][k]);
   }
   
   if(answer == INT_MAX){
      cout << "Impossible\n";
   } else {
      cout << answer;
   }
   
   return 0;
}