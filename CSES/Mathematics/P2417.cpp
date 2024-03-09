#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 1000005;
   
   vector<bool> isprime(N,true);
   for(int k = 2; k < N; k++){
      if(isprime[k]){
         for(int j = k*2; j < N; j += k){
            isprime[j] = false;
         }
      }
   }
   
   vector<int> primes;
   vector<int> nump(N,0);
   vector<bool> squarep(N,false);
   for(int k = 2; k < N; k++){
      if(!isprime[k]) continue;
      primes.push_back(k);
      
      for(int j = k; j < N; j+=k){
         nump[j]++;
      }
      //but don't count squares
      if(k<10000){
         for(int j = k*k; j < N; j += k*k){
            squarep[j] = true;
         }
      }
   }
   
   int n;
   cin >> n;
   vector<long long> array(N,0LL);
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      array[i]++;
   }
   
   long long answer = 0LL;
   for(int k = 2; k < N; k++){
      if(squarep[k]) continue;
      if(nump[k] >= 1){
         long long cursum = 0LL;
         for(int j = k; j < N; j += k){
            cursum += array[j];
         }
         
         if(nump[k] % 2 == 1){
            answer += cursum * (cursum-1LL) / 2LL;
         } else {
            answer -= cursum * (cursum-1LL) / 2LL;
         }
      }
   }
   
   long long nl = (long long)n;
   answer = nl * (nl-1LL) / 2LL - answer;
   cout << answer << endl;
   
   return 0;
}