#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 32000;
   
   vector<bool> isprime(N,true);
   for(int k = 2; k < N; k++){
      if(!isprime[k]) 
         continue;
      for(int j = k*2; j < N; j += k){
         isprime[j] = false;
      }
   }
   
   vector<int> primes;
   for(int k = 2; k < N; k++){
      if(isprime[k]) primes.push_back(k);
   }
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n);
      unordered_set<int> hset;
      bool found = false;
      for(int k = 0; k < n; k++){
         cin >> array[k];
         if(array[k] != 1){
            if(hset.find(array[k]) == hset.end()){
               hset.insert(array[k]);
            } 
            else {
               found = true;
            }
         }
      }
      
      if(found){
         cout << "YES\n";
         continue;
      }
      unordered_set<int> seenprimes;
      for(int k = 0; k < n; k++){
         int i = 0;
         while(primes[i] <= array[k] && i < primes.size()){
            if(array[k] % primes[i] == 0){
               if(seenprimes.find(primes[i]) != seenprimes.end()){
                  found = true;
                  break;
               } 
               else {
                  seenprimes.insert(primes[i]);
               }
               
            }
            while(array[k] % primes[i] == 0){
               array[k] /= primes[i];
            }
            i++;   
         }
         
         if(array[k] > 1){
            if(seenprimes.find(array[k]) == seenprimes.end()){
               seenprimes.insert(array[k]);
            } else {
               found = true;
            }
         }
         
         if(found) 
            break;
      }
      
      if(found){
         cout << "YES\n";
      } 
      else {
         cout << "NO\n";
      }
      
      
   }
   
   return 0;
}