#include <bits/stdc++.h>

using namespace std;

//N will be <10^5 so just make a big matrix.
int dp[100005][3];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   /*
   Use this for file input/output (like for USACO),
   and use fin and fout instead of cin and cout
   ifstream fin (".in");
   ofstream fout (".out");
   */
   
   
   int N;
   cin >> N;
   
   //initialize putting a 1 coin, 3 coin, and 5 coin.
   dp[1][0] = 1;
   dp[3][1] = 1;
   dp[5][2] = 1;
   
   for(int k = 1; k < N; k++){
        for(int j = 0; j < 3; j++){
            if(dp[k][j] == 0) continue;
            if(j <= 0 && k+1 <= N) dp[k+1][0] += dp[k][j];
            if(j <= 1 && k+3 <= N) dp[k+3][1] += dp[k][j];
            if(j <= 2 && k+5 <= N) dp[k+5][2] += dp[k][j];
        }
   }
   
   int answer = dp[N][0] + dp[N][1] + dp[N][2];
   cout << answer << endl;
   
   
   return 0;
}