#include <bits/stdc++.h>

using namespace std;

int LOG = 42;
int X = 64;

long long MOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 1000005;
   
   vector<bool> isprime(N,true);
   vector<int> primes;
   
   for(int k = 2; k < N; k++){
      if(!isprime[k]) 
         continue;
      primes.push_back(k);
      for(int j = 2*k; j < N; j += k){
         isprime[j] = false;
      }
   }
   
   vector<long long> pow2(N,0LL);
   pow2[0] = 1LL;
   for(int k = 1; k < N; k++){
      pow2[k] = (pow2[k-1]*2LL + MOD)%MOD;
   }
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      long long a,b;
      cin >> a >> b;
      
      int n = (int)(b-a+1);
      
      vector<long long> numfac(LOG,0LL);
      
      vector<long long> nums(n);
      vector<long long> curfac(n,0LL);
      
      for(int k = 0; k < n; k++){
         nums[k] = a+(long long)k;
      }
      
      for(int p : primes){
         //find least
         long long start = (a+p-1)/p*p;
         
         for(int k = (int)(start-a); k < n; k += p){
            while(nums[k] % p == 0){
               nums[k] /= p;
               curfac[k]++;
            }
         }
      }
      
      for(int k = 0; k < n; k++){
         if(nums[k] != 1){
            curfac[k]++;
         }
      }
      
      for(int k = 0; k < n; k++){
         numfac[curfac[k]]++;
      }

      //xor of 0
      vector<vector<long long>> dp(LOG,vector<long long>(X,0LL));
      
      dp[0][0] = 1LL;
      
      for(int k = 0; k < LOG-1; k++){
         for(int j = 0; j < X; j++){
            if(dp[k][j] == 0LL) 
               continue;
            long long mul = 1LL;
            if(numfac[k+1] >= 1){
               mul = pow2[numfac[k+1]-1];
               
               //use
               long long prod1 = (mul*dp[k][j] + MOD)%MOD;
               dp[k+1][(k+1)^j] = (dp[k+1][(k+1)^j] + prod1 + MOD)%MOD;
            }
            
            long long prod2 = (mul*dp[k][j] + MOD)%MOD;
            dp[k+1][j] = (dp[k+1][j] + prod2 + MOD)%MOD;
         }
      }
      
      //dp[LOG-1][0] contains total # of permutations to make the xor 0
      //to account for misere game, subtract permutations with only an even number of 1 groups, add permutations with odd number of 1 groups
      //in all cases except for numfac[1] == 0, those two are equal so no modifications are needed
      //but for numfac[1] == 0, even = 1 and odd = 0, so subtract 1
      
      if(numfac[1] == 0){
         cout << (dp[LOG-1][0]+MOD-1)%MOD << "\n";
      } 
      else {
         cout << dp[LOG-1][0] << "\n";
      }
      
   }
   
   
   return 0;
}