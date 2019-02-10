#include <bits/stdc++.h>

using namespace std;

long long dp[2][1<<22];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   dp[0][100000] = 1;
   
   cout << (sizeof(dp)) / 1048576.0;
   
   
   return 0;
}