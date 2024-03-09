#include <bits/stdc++.h>
//MAJOR BUG: set equal to 1 in initial states instead of +=1
//because mod, some initial states could be the same
using namespace std;

const long long MOD = 1000000007LL;

int add(int a, int b, int mod){
   int ret = a+b;
   while(ret >= mod) ret -= mod;
   return ret;
}

void add(long long& a, long long b){
   a += b;
   if(a >= MOD) a -= MOD;
}

int sub(int a, int b, int mod){
   int ret = a-b;
   if(ret < 0) ret += mod;
   return ret;
}

int mul(int a, int b, int mod){
   return (a*b + mod)%mod;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 1; q <= t; q++){
      string lin,rin;
      cin >> lin >> rin;
      
      int n = rin.length();
      
      vector<int> l(n);
      vector<int> r(n);
      
      for(int k = 0; k < n; k++){
         if(k+lin.length() < n) l[k] = 0;
         else l[k] = lin[k-(n-lin.length())]-'0';
         r[k] = rin[k]-'0';
      }
      
      int mod;
      cin >> mod;
      
      vector<int> pow10(n);
      pow10[n-1] = 1;
      for(int k = n-2; k >= 0; k--){
         pow10[k] = mul(10,pow10[k+1],mod);
      }
      
      vector<vector<vector<long long>>> dp(n,
             vector<vector<long long>>(mod,
                    vector<long long>(mod,0LL)));
      
      int minsum = 0;
      int maxsum = 0;
      int minmod = 0;
      int maxmod = 0;
      bool minmaxsame = false;
      if(r[0] == l[0]){
         minsum = r[0]%mod;
         maxsum = minsum;
         minmod = sub(0,mul(pow10[0],r[0],mod),mod);
         maxmod = minmod;
         minmaxsame = true;
      } 
      else {
         minsum = l[0]%mod;
         maxsum = r[0]%mod;
         minmod = sub(0,mul(pow10[0],l[0],mod),mod);
         maxmod = sub(0,mul(pow10[0],r[0],mod),mod);
         for(int k = l[0]+1; k <= r[0]-1; k++){
            dp[0][k%mod][sub(0,mul(pow10[0],k,mod),mod)] += 1LL;
         }
      }
      
      for(int d = 0; d < n-1; d++){
         for(int s = 0; s < mod; s++){
            for(int m = 0; m < mod; m++){
               for(int x = 0; x <= 9; x++){
                  int ns = add(s,x,mod);
                  
                  int m1 = mul(s,x,mod);
                  int a1 = add(m,m1,mod);
                  int m2 = mul(pow10[d+1],x,mod);
                  int nm = sub(a1,m2,mod);
                  add(dp[d+1][ns][nm],dp[d][s][m]);
                  
               }
               
            }
         }
         
         if(minmaxsame){
            for(int x = l[d+1]+1; x <= r[d+1]-1; x++){
               int ns = add(minsum,x,mod);
               int nm = ((minmod + minsum*x - pow10[d+1]*x)%mod + mod)%mod;
               
               add(dp[d+1][ns][nm],1LL);
            }
         } else {
            for(int x = l[d+1]+1; x <= 9; x++){
               int ns = add(minsum,x,mod);
               int nm = ((minmod + minsum*x - pow10[d+1]*x)%mod + mod)%mod;
               
               add(dp[d+1][ns][nm],1LL);
            }
            for(int x = r[d+1]-1; x >= 0; x--){
               int ns = add(maxsum,x,mod);
               int nm = ((maxmod + maxsum*x - pow10[d+1]*x)%mod + mod)%mod;
               
               add(dp[d+1][ns][nm],1LL);
            }
         }
            
         //add l to min
         int m1 = mul(minsum,l[d+1],mod);
         int a1 = add(minmod,m1,mod);
         int m2 = mul(pow10[d+1],l[d+1],mod);
         minmod = sub(a1,m2,mod);
         minsum = add(minsum,l[d+1],mod);
         
         m1 = mul(maxsum,r[d+1],mod);
         a1 = add(maxmod,m1,mod);
         m2 = mul(pow10[d+1],r[d+1],mod);
         maxmod = sub(a1,m2,mod);
         maxsum = add(maxsum,r[d+1],mod);
         
         if(l[d+1] != r[d+1]) minmaxsame = false;   
      }
         
      long long answer = 0L;
      for(int s = 0; s < mod; s++){
         add(answer,dp[n-1][s][0]);
      }
      
      if(minmaxsame && minmod == 0) add(answer,1LL);
      else{
         if(minmod == 0) add(answer,1LL);
         if(maxmod == 0) add(answer,1LL);
      }
      
      cout << answer << endl;
      
   }
   
   
   return 0;
}