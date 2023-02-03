#include <bits/stdc++.h>
//tutorial

using namespace std;

#define N 100005

long long fac[N];
long long ifac[N];
long long pow2[N];

long long MOD = 998244353LL;


//from geeksforgeeks
long long modInverse(long long a, long long m) 
{ 
    long long m0 = m; 
    long long y = 0;
    long long x = 1; 
  
    if (m == 1) 
      return 0; 
  
    while (a > 1) 
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

long long choose(int n, int r){
   long long prod1 = (fac[n]*ifac[r] + MOD)%MOD;
   return (prod1 * ifac[n-r] + MOD)%MOD;
}

long long calc(const vector<long long>& array, int l, int r){
   if(l >= r) return 1LL;
   
   //get length of prefix and suffix of 0s
   
   int pref0 = 0;
   int suff0 = 0;
   
   while(pref0+l <= r && array[pref0+l] == 0){
      pref0++;
   }
   
   //all 0s
   if(pref0 == r-l+1){
      return pow2[r-l];
   }
   
   while(r-suff0 >= l && array[r-suff0] == 0){
      suff0++;
   }
   
   //cout << l << " " << r << " " << pref0 << " " << suff0 << endl;
   
   if(pref0 > 0 && suff0 > 0){
      long long prod = 0LL;
      for(int k = 0; k <= min(pref0,suff0); k++){
         long long cur = (choose(pref0,k) * choose(suff0,k) + MOD)%MOD;
         prod = (prod + cur + MOD)%MOD;
      }
      
      return (prod*calc(array,l+pref0,r-suff0) + MOD)%MOD;
   } else {
      //get the first point where the sums are equal
      int ll = l;
      int rr = r;
      
      long long suml = array[l];
      long long sumr = array[r];
      
      while(ll < rr){
         if(suml == sumr){
            break;
         }
         
         if(suml < sumr){
            ll++;
            suml += array[ll];
         } else {
            rr--;
            sumr += array[rr];
         }
      }
      
      if(ll >= rr) return 1LL;
      
      int l0 = 0;
      int r0 = 0;
      
      while(ll+1+l0 <= rr-1 && array[ll+1+l0] == 0){
         l0++;
      }
      
      if(ll+1+l0 > rr-1){
         return pow2[rr-ll];
      }
      
      while(rr-1-r0 >= ll+1 && array[rr-1-r0] == 0){
         r0++;
      }
      
      long long prod = 1LL;
      for(int k = 0; k <= min(l0,r0); k++){
         long long cur = (choose(l0+1,k+1)*choose(r0+1,k+1) + MOD)%MOD;
         prod = (prod + cur + MOD)%MOD;
      }
      
      return (prod*calc(array,ll+l0+1,rr-r0-1) + MOD)%MOD;
      
      
   }
   
   
   
} 




int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   fac[0] = 1LL;
   ifac[0] = 1LL;
   pow2[0] = 1LL;
   
   for(int k = 1; k < N; k++){
      fac[k] = (fac[k-1]*(long long)k + MOD)%MOD;
      ifac[k] = modInverse(fac[k],MOD);
      pow2[k] = (pow2[k-1]*2LL + MOD)%MOD;
   }
   
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      vector<long long> array(n);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      long long answer = calc(array,0,n-1);
      cout << answer << "\n";  
   }
      
   
   
   return 0;
}