#include <bits/stdc++.h>

using namespace std;

class Segtree{
   private:
   vector<long long> a;
   int sz;
   long long mod;
   
   public:
   Segtree(int size, long long m) : sz(size), mod(m){
      a = vector<long long>(4*size);
   }
   
   //build with array + 1
   void build(int v, int l, int r, const vector<long long>& array){
      if(l == r){
         a[v] = array[l]+1LL;
      } else {
         int mid = (l+r)/2;
         
         build(2*v+1,l,mid,array);
         build(2*v+2,mid+1,r,array);
         
         a[v] = (a[2*v+1] * a[2*v+2] + mod)%mod;
      }
   }
   
   long long mul(int v, int l, int r, long ql, long qr){
      //if(r < 0 || l >= sz) return 0LL;
      if(l >= ql && r <= qr){
         return a[v];
      } else if(r < ql || l > qr){
         return 1LL;
      } else {
         int mid = (l+r)/2;
         
         return (mul(2*v+1,l,mid,ql,qr) * mul(2*v+2,mid+1,r,ql,qr) + mod)%mod;
      }
   }
};

const long long MOD = 1000000007LL;

long long exp(long long base, long long p, long long mod){
   if(p == 0LL) return 1LL;
   if(p == 1LL) return base;
   long long ans = exp(base,p/2LL,mod);
   ans = (ans * ans + mod)%mod;
   if(p%2LL == 1LL){
      ans = (ans * base + mod)%mod;
   }
   return ans;
}

long long modInverse(long long x, long long m){
   return exp(x,m-2LL,m);
}

//calculates x*(x+1)/2 % m, without division since division by m is impossible
long long summation(long long x, long long m){
   if(x == 1LL) return 1LL;
   if(x%2 == 1LL) return (x + summation(x-1,m) + m)%m; 
   long long half = x/2LL;
   long long summ = summation(half,m);
   summ = (summ * 2LL + (half*half) + m)%m;
   return summ;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<long long> x(n);
   vector<long long> m(n);
   
   vector<long long> exps(n);
   //num divisors
   long long a1 = 1LL;
   for(int k = 0; k < n; k++){
      cin >> x[k] >> m[k];
      a1 = (a1 * (m[k]+1LL) + MOD)%MOD;
      
      exps[k] = exp(x[k],m[k],MOD);
      //prod = (prod * exps[k] + MOD)%MOD;
   }
   
   Segtree segtree(n,MOD-1);
   segtree.build(0,0,n-1,m);
   
   //sum of divisors
   long long a2 = 1LL;
   for(int k = 0; k < n; k++){
      //get sum of 1+x+x^2 ... x^m
      
      long long s1 = modInverse(x[k]-1LL,MOD);
      long long numer = (exps[k] * x[k] - 1 + MOD)%MOD;
      long long prod = (numer * s1 + MOD)%MOD;
      a2 = (a2 * prod + MOD)%MOD;
   }
   
   
   //product of divisors
   long long a3 = 1LL;
   for(int k = 0; k < n; k++){
      long long other = (segtree.mul(0,0,n-1,0,k-1) * segtree.mul(0,0,n-1,k+1,n-1) + MOD-1)%(MOD-1);
      long long e1 = exp(x[k],summation(m[k],MOD-1),MOD);        //mod summation by MOD-1
      
      long long e2 = exp(e1,other,MOD);
      //cout << other << " " << e1 << " " << e2 << endl;
      a3 = (a3 * e2 + MOD)%MOD;
   }
   
   cout << a1 << " " << a2 << " " << a3 << endl;
   
   
   
   
   return 0;
}