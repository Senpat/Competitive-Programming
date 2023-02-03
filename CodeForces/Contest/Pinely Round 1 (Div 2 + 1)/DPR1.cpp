#include <bits/stdc++.h>

using namespace std;

#define N 2000005

long long MOD = 1000000007LL;

long long fac[N];
long long ifac[N];
long long pow3[N];

long long choose(int n, int r){
   long prod1 = (fac[n] * ifac[r] + MOD)%MOD;
   return (prod1 * ifac[n-r] + MOD)%MOD;
}

        //from geeksforgeeks
long long modInverse(long long a, long long m) 
{ 
   long long m0 = m; 
   long long y = 0LL;
   long long x = 1LL; 
     
   if (m == 1LL) 
      return 0LL; 
     
   while (a > 1LL) 
   { 
           // q is quotient 
      long long q = a / m; 
      long long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
      m = a % m;
      a = t; 
      t = y; 
     
           // Update y and x 
      y = x - q * y; 
      x = t; 
   } 
     
       // Make x positive 
   if (x < 0) 
      x += m0; 
   return x; 
}  



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   fac[0] = 1LL;
   ifac[0] = 1LL;
   pow3[0] = 1LL;
      
   for(int k = 1; k < N; k++){
      fac[k] = ((long long)k * fac[k-1] + MOD)%MOD;
      pow3[k] = (3L * pow3[k-1] + MOD)%MOD;
   }
   
   ifac[N-1] = modInverse(fac[N-1],MOD);
   
   for(int k = N-2; k >= 0; k--){
      ifac[k] = ((long long)(k+1) * ifac[k+1] + MOD)%MOD;
   }
   
   int n,m;
   cin >> n >> m;
   
   if(m == 0){
      cout << pow3[n] << endl;
      return 0;
   }
   
   long long answer = 0LL;
   for(int s = 1; s <= m; s++){
         //case 1: msb is part of a segment
      int rb = m+s-1;         //required bits
      if(rb <= n){
         long long prod = pow3[m-s];
         prod = (prod * pow3[n-rb] + MOD)%MOD;
         prod = (prod * choose(m-1,s-1) + MOD)%MOD;
         prod = (prod * choose(n-rb+s-1,s-1) + MOD)%MOD;
            
         answer = (answer + prod + MOD)%MOD;
      }
         
         //msb is not part of segment
      rb = m+s;
      if(rb <= n){
         long long prod = pow3[m-s];
         prod = (prod * pow3[n-rb] + MOD)%MOD;
         prod = (prod * choose(m-1,s-1) + MOD)%MOD;
         prod = (prod * choose(n-rb+s,s) + MOD)%MOD;
            
         answer = (answer + prod + MOD)%MOD;
      }
      
   }
   
   cout << answer << endl;
      
   
   
   return 0;
}