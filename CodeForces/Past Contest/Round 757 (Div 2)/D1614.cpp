#include <bits/stdc++.h>
//translation of D1614c.java

using namespace std;

#define N 20000005
int lp[N+1];

vector<int> pr;

int n;
long long freq[N+1];
long long cnt[N+1];

long long dp[N+1];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   fill(begin(lp),end(lp),0);
   fill(begin(freq),end(freq),0LL);
   fill(begin(cnt),end(cnt),0LL);
   
   
   //linear sieve
   for(int k = 2; k <= N; k++){
      if(lp[k] == 0){
         lp[k] = k;
         pr.push_back(k);
      }
      for(int j = 0; j < pr.size() && pr[j] <= lp[k] && k*pr[j] <= N; j++){
         lp[k*pr[j]] = pr[j];
      }
   }
   
   cin >> n;
   vector<int> array;
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      array.push_back(i);
      cnt[array[k]]++;
   }
   
   for(int prime : pr){
      for(int k = N/prime; k >= 1; k--){
         cnt[k] += cnt[k*prime];
      }
   }
   
   for(int k = 1; k <= N; k++){
      dp[k] = (long long)k * cnt[k];
   }
   
   for(int k = N; k >= 1; k--){
      long long kl = (long long)k;
      for(int p : pr){
         if(p*k > N) break;
         dp[k] = max(dp[k],dp[k*p] + kl*(cnt[k]-cnt[k*p]));
      }
   }
   
   long long answer = 0L;
   for(int k = 1; k <= N; k++){
      answer = max(answer,dp[k]);
   }
   
   cout << answer;
   
   return 0;
}