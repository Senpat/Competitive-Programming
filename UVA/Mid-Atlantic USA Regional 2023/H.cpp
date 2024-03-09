#include <bits/stdc++.h>
//upsolve, danny hint
//using namespace std;

std::vector<int> array;
std::vector<std::vector<int>> dp;

int dothing(int l, int r){
   if(dp[l][r] != -1) return dp[l][r];
   
   //get first non-array[l] index
   int nli = -1;
   for(int k = l+1; k <= r; k++){
      if(array[k] != array[l]){
         nli = k;
         break;
      }
   }
   
   if(nli == -1){
      dp[l][r] = 0;
      return 0;
   }
   
   //get all values of array[l] after nli
   int ret = INT_MAX;
   for(int k = nli+1; k <= r; k++){
      if(array[k] == array[l]){
         ret = std::min(ret, 1 + dothing(nli,k-1) + dothing(k,r));
      }
   }
   ret = std::min(ret, 1 + dothing(nli,r));
   
   dp[l][r] = ret;
   return ret;
      
      
   
}

int main(){
   std::ios::sync_with_stdio(false);
   std::cin.tie(0);
   
   int n;
   std::cin >> n;
   
   array = std::vector<int>(n+1,0);
   //append a 0 at the front
   for(int k = 1; k <= n; k++){
      std::cin >> array[k];
   }
   
   //dp[l][r] = min # of operations to make range from l to r equal to array[l]
   dp = std::vector<std::vector<int>>(n+1,std::vector<int>(n+1,-1));
   
   int answer = dothing(0,n);
   std::cout << answer << std::endl;
   
   
   
   return 0;
}