#include <bits/stdc++.h>
//USES SLIDING WINDOW

using namespace std;

#define MAXN 205

pair<int,int> facs[MAXN];
int dp[2][MAXN][MAXN*26];
bool seen[2][MAXN][MAXN*26];

int num2(long long x){
   int ret = 0;
   long long cur = 1;
   while(x % cur == 0){
      cur *= 2;
      ret ++;
   }
   
   return ret-1;
}

int num5(long long x){
   int ret = 0;
   long long cur = 1;
   while(x % cur == 0){
      cur *= 5;
      ret ++;
   }
   
   return ret-1;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   int n,m;
   cin >> n >> m;
   
   
   for(int k = 0; k < n; k++){
      long long a;
      cin >> a;
      
      facs[k] = make_pair(num2(a),num5(a));
      
   }
   
   seen[0][0][0] = true;
   
   for(int k = 0; k <= n; k++){
      for(int j = 0; j <= m; j++){
         for(int h = 0; h < MAXN*26;h++){
            if(seen[0][j][h] || dp[0][j][h] > 0){
               //don't use
               seen[1][j][h] = true;
               dp[1][j][h] = max(dp[1][j][h],dp[0][j][h]);
               //use
               seen[1][j+1][h+facs[k].second] = true;
               dp[1][j+1][h+facs[k].second] = max(dp[1][j+1][h+facs[k].second], dp[0][j][h] + facs[k].first);
            }
         }
      }
      
      //swap - sliding window
      for(int j = 0; j <= m; j++){
         for(int h = 0; h < MAXN*26; h++){
            dp[0][j][h] = dp[1][j][h];
            dp[1][j][h] = 0;
            seen[0][j][h] = seen[1][j][h];
            seen[1][j][h] = false;
          }
       }
   }
   int answer = 0;
   
   for(int k = 0; k < MAXN*26; k++){
      answer = max(answer,min(k,dp[0][m][k]));
   }
   
   cout << answer;
   
   
   
   return 0;
}