#include <bits/stdc++.h>
//solves D2 hopefully
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      long long x,y;
      cin >> n >> x >> y;
      
      string a,b;
      cin >> a >> b;
      
      int nd = 0;
      vector<int> array(n,0);
      vector<long long> ds;
      for(int k = 0; k < n; k++){
         if(a[k] != b[k]){
            array[k] = 1;
            nd++;
            ds.push_back((long long)k);
         }
         else array[k] = 0;
      }
      
      if(nd % 2 == 1){
         cout << -1 << "\n";
         continue;
      }
      
      if(nd == 0){
         cout << 0 << "\n";
         continue;
      }
      
      if(y <= x){
         long long answer = -1;
         if(nd > 2){
            answer = y*(long long)(nd/2);
         } else {
            //nd == 2
            if(ds[0]+1LL == ds[1]){
               answer = min(x,y*2LL);
            } else {
               answer = y;
            }
         }
         
         cout << answer << "\n";
      
      } else {
         //x < y, try to do operations close to each other
         //nd is at minimum 2
         
         vector<vector<long long>> dp(nd,vector<long long>(nd,LLONG_MAX));
         
         dp[0][1] = 0LL;
         dp[1][0] = min(y,x*(ds[1]-ds[0]));
         
         for(int k = 1; k < nd; k++){
            for(int j = 0; j < nd; j++){
               //don't use
               if(j > 0 && dp[k-1][j-1] != LLONG_MAX){
                  dp[k][j] = min(dp[k][j],dp[k-1][j-1]);
               }
               
               if(k > 1 && dp[k-2][j] != LLONG_MAX){
                  dp[k][j] = min(dp[k][j],dp[k-2][j] + x*(ds[k]-ds[k-1]));
               }
            }
         }
         
         long long answer = LLONG_MAX;
         for(int k = 0; k < nd; k+=2){
            if(dp[nd-1][k] == LLONG_MAX) continue;
            answer = min(answer,dp[nd-1][k] + y*(long long)(k/2));
         }
         
         cout << answer << "\n";
         
         
         
      
      }
            
   
   }
   
   
   return 0;
}