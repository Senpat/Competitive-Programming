#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> w;
   
   int pisum = 0;
   int backsum = 0;
   for(int k = 0; k < n; k++){
      int pi;
      cin >> pi;
      pisum += pi;
      
      vector<int> p(pi+1);
      for(int j = 1; j <= pi; j++){
         cin >> p[j];
      }
      
      backsum += p.back();
      
      w.push_back(p);
   }
   
   //forced to use all of the power
   if(pisum <= m){
      cout << backsum << endl;
      return 0;
   }
   
   //0 -> have not used non-pi, 1 -> used pi
   vector<vector<vector<int>>> dp(n,vector<vector<int>>(m+1,vector<int>(2,-1)));
   
   //do nothing
   dp[0][0][0] = 0;
   
   if(w[0].size()-1 <= m){
      dp[0][w[0].size()-1][0] = w[0].back();
   }
   for(int k = 1; k < w[0].size()-1 && k <= m; k++){
      dp[0][k][1] = w[0][k];
   }
   
   for(int k = 0; k < n-1; k++){
      for(int j = 0; j <= m; j++){
         //don't add anything
         dp[k+1][j][0] = max(dp[k+1][j][0],dp[k][j][0]);
         dp[k+1][j][1] = max(dp[k+1][j][1],dp[k][j][1]);
         
         //add pi
         int npim = j + w[k+1].size()-1;
         if(npim <= m){
            if(dp[k][j][0] != -1) dp[k+1][npim][0] = max(dp[k+1][npim][0], dp[k][j][0] + w[k+1].back());
            if(dp[k][j][1] != -1) dp[k+1][npim][1] = max(dp[k+1][npim][1], dp[k][j][1] + w[k+1].back());
         }
         
         //add non-pi
         if(dp[k][j][0] != -1){
            for(int h = 1; h < w[k+1].size()-1 && j+h <= m; h++){
               dp[k+1][j+h][1] = max(dp[k+1][j+h][1], dp[k][j][0] + w[k+1][h]);
            }
         }
      }
   }
   
   int answer = max(dp[n-1][m][0],dp[n-1][m][1]);
   /*
   //need to use all the power possible
   for(int k = 0; k <= m; k++){
      answer = max(answer,dp[n-1][k][0]);
      answer = max(answer,dp[n-1][k][1]);
   }*/
   cout << answer << endl;
   
   /*
   if(n == 1){
      //full upgrade
      if(m >= w[0].size()-1){
         cout << w[0].back() << endl;
      } else {
         cout << w[0][m] << endl;
      }
      return 0;
   }
   
   int answer = 0;
   
   for(int k = 0; k < n; k++){
      for(int j = 0; j < w[k].size(); j++){
         //use j+1
         int rem = m - (j+1);
         
         //see max sum you can make using only whole power upgrades
         vector<vector<int>> dp(n,vector<int>(rem+1,INT_MAX));
         
         int start = 0;
         if(k == 0){
            start = 1;
            dp[1][0] = 0;
            if(w[1].size()-1 <= rem) dp[1][w[1].size()-1] = w[1].back();
         } else {
            dp[0][0] = 0;
            if(w[0].size()-1 <= rem) dp[0][w[0].size()-1] = w[0].back();
         }
         
         for(int h = start; h < n-1; h++){
            
         }
      }
   }
   
   */
   
   return 0;
}