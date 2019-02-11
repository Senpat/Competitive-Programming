#include <bits/stdc++.h>
//correct solution
using namespace std;
long long N,B,n,b,sum,c,x,bk;
unordered_set<long long> seen;

long long answer = LLONG_MAX;

long long numin(long long q, long long w){
   long long ret = 0;
   while(q%w == 0 && q >= w){
      q/=w;
      //cout << q << " " << ret << endl;
      ret++;
   }
   return ret;
}

long long exp(long long base, long long power){
   if(power == 0) 
      return 1;
   if(power == 1) 
      return base;
   long long ans = exp(base,power/2);
   ans = (ans * ans);
   if(power%2 == 1) ans = (ans*base);
   
   return ans;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   cin >> N >> B;
   
   b = B;
   n = N;
   
   for(long long k = 2; k*k <= B; k++){
      long long nm = numin(b,k);
      //cout << b << " " << k << " " << nm << endl;
      if(nm == 0) 
         continue;
      long long freqneeded = nm;
      b /= exp(k,nm);
      //cout << b << endl;
      c = 0;
      bk = k;
      x=bk;
      //cout << b << " " << c << " " << bk << endl;
      while(x <= n){
         c += n/x;
         //cout << c << " " << n << " " << x << " " << bk << " " << log10((double)x) << " " << log10((double)x) << endl;
         if(log10((double)x) + log10((double)bk) > 18.1) 
            break;
         
         x *= bk;
      }
      answer = min(answer,c/freqneeded);
         
         
         
         
      
   }
   
   if(b > 1){
      //prime factor > sqrt(b)
      c = 0;
      bk = b;
      x=bk;
      //cout << b << " " << c << " " << bk << endl;
      while(x <= n){
         c += n/x;
         if(log10((double)x) + log10((double)bk) > 18.1) 
            break;
         x *= bk;
      }
      
      answer = min(answer,c);
  }
   if(answer == LLONG_MAX){
      //answer is prime
      c = 0;
      bk = B;
      x=bk;
      //cout << b << " " << c << " " << bk << endl;
      while(x <= n){
         c += n/x;
         if(log10((double)x) + log10((double)bk) > 18.1) 
            break;
         x *= bk;
      }
      
      cout << c;
      return 0;
   }
 
   
   cout << answer;
   
   
   return 0;
}