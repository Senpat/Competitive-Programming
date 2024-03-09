#include <bits/stdc++.h>
//https://usaco.guide/problems/cses-1712-exponentiation-ii/solution
using namespace std;

const long long MOD = 1000000007LL;


long long exp(long long a, long long b, long long m){
   if(b == 0L) return 1LL;
   if(b == 1L) return a;
   if(a == 0L) return 0LL;
   
   long long ans = exp(a,b/2LL,m);
   ans = (ans*ans + m)%m;
   if(b % 2LL == 1LL){
      ans = (ans * a + m)%m;
   }
   return ans;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int q;
   cin >> q;
   for(int t = 0; t < q; t++){
      long long a,b,c;
      cin >> a >> b >> c;
      
      //format little theorem, a^(p-1) = 1 mod p, so for the exponent only the remainder when divided by (p-1) matters (so just mod by p-1)
      long long answer = exp(a,exp(b,c,MOD-1),MOD);
      cout << answer << "\n";
   }
   
   return 0;
}