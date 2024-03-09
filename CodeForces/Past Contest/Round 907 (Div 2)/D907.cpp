#include <bits/stdc++.h>

using namespace std;

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;

unsigned hash_f(unsigned x) {
   x = ((x >> 16) ^ x) * 0x45d9f3b;
   x = ((x >> 16) ^ x) * 0x45d9f3b;
   x = (x >> 16) ^ x;
   return x;
}
struct chash {
   int operator()(int x) const { 
      return hash_f(x); }
};


const long long MOD = 1000000007LL;

const int P = 61;
long long powx[P][P];


int getg(long long x, int f){
   int l = 0;
   int r = P-1;
   int ans = -1;
   while(l <= r){
      int mid = l + ((r-l)>>1);
      if(powx[f][mid] <= x){
         ans = mid;
         l = mid+1;
      } else {
         r = mid-1;
      }
   }
   
   return ans;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   long long INF = 2000000000000000000LL; //2e18
   for(int k = 2; k < P; k++){
      long long kl = (long long)k;
      powx[k][0] = 1LL;
      for(int j = 1; j < P; j++){
         if(powx[k][j-1] == LLONG_MAX) powx[k][j] = LLONG_MAX;
         else if(powx[k][j-1] * kl > INF || powx[k][j-1] * kl <= 0) powx[k][j] = LLONG_MAX;
         else powx[k][j] = powx[k][j-1] * kl;
      }
   }
   
   int t;
   cin >> t;
   
   gp_hash_table<long long, long long, chash> mem;
   gp_hash_table<long long, long long, chash> gmem;
   
   for(int q = 1; q <= t; q++){
      long long L,R;
      cin >> L >> R;
      
      long long answer = 0LL;
      
      long long i = L;
      while(i <= R){
         //cout << i << endl;
         if(mem.find(i) == mem.end()){
            int curf = getg(i,2);
            int curg = getg(i,curf);
            long long nextg = powx[curf][curg+1];
            long long nextf = powx[2][curf+1];
            long long last = min(nextg,nextf)-1;
            if(nextg <= 0){
               last = nextf-1;
            }
            
            mem[i] = last;
            gmem[i] = (long long)curg;
         }
         
         
         long long last = min(mem[i],R);
            
         long long len = (last-i+1 + MOD)%MOD;
         long long prod = (len * gmem[i] + MOD)%MOD;
         answer += prod;
         if(answer >= MOD) answer -= MOD;
            
         i = last+1;
      }
         
      cout << answer << "\n";
   }
   
   return 0;
}