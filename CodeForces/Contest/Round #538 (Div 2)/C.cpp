#include <bits/stdc++.h>

using namespace std;

long long nums(long long n, long long x){
   long long c = 0;
   while(x <= n){
      c += n/x;
      x *= x;
   }
   //cout << c;
   return c;
}

long long n,b,sum,c,x,bk;
unordered_set<long long> seen;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   cin >> n >> b;
   
   sum = 0;
   //cout << "hi" << endl;
   
   
   
   for(long long k = (long long)sqrt(b); k >= 1; k++){
      
      if(b%k != 0) 
         continue;
      //cout << k << endl;
      
      c = 0;
      x = b/k;
      bk = b/k;
      while(x <= n){
         if(seen.find(n/x)==seen.end()) c += n/x;
         seen.insert(n/x);
         //cout << c << " " << n << " " << x << " " << bk << " " << log10((double)x) << " " << log10((double)x) << endl;
         if(log10((double)x) + log10((double)bk) > 18.1) break;
         x *= bk;
      }
      cout << bk << " " << c << " " << x << endl;
      if(k*k == b){
         sum += c/2;
      } else {
         sum += c;
      }
   }
   //return 0;
   cout << sum;
   
   
   
   return 0;
}