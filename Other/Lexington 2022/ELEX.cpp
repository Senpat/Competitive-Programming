#include <bits/stdc++.h>

using namespace std;

vector<int> z_function(string s) {
   int n = (int) s.length();
   vector<int> z(n);
   for (int i = 1, l = 0, r = 0; i < n; ++i) {
      if (i <= r)
         z[i] = min (r - i + 1, z[i - l]);
      while (i + z[i] < n && s[z[i]] == s[i + z[i]])
         ++z[i];
      if (i + z[i] - 1 > r)
         l = i, r = i + z[i] - 1;
   }
   return z;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      string ain,bin;
      cin >> ain >> bin;
      
      vector<char> a(ain.begin(),ain.end());
      vector<char> b(bin.begin(),bin.end());
      
      int an = a.size();
      int bn = b.size();
      
      if(an < bn){
         cout << "0\n";
         return 0;
      }
      
      vector<int> zf = z_function(bin);
      vector<int> nextf;
      nextf.push_back(0);
      for(int k = 0; k < bn; k++){
         if(zf[k] == bn-k) nextf.push_back(bn-k);
      }
      
      string binb(bin.rbegin(),bin.rend());
      vector<int> zb = z_function(binb);
      vector<int> nextb;
      nextf.push_back(0);
      for(int k = 0; k < bn; k++){
         if(zb[k] == bn-k) nextb.push_back(bn-k);
      }
      
      //dp[n][l][r] = max # of strings such that you have l letters on the left and r letters on the right after n characters of a
      vector<vector<vector<int>>> dp(an,vector<int>(bn,vector<int>(bn,-1)));
      
      //get initial states (different ways of getting first substring)
      //[string],[string]+garbage, or garbage+string
      
      //check if you can make the string with the first n characters
      //dp1[l][r] = if it's possible to make b[l,r] with the first r-l+1 letters of a
      vector<vector<bool>> dp1(n,vector<bool>(n,false));
      
      //initial
      for(int k = 0; k < bn; k++){
         if(a[0] == b[k]){
            dp1[k][k] = true;
         }
      }
      
      for(int k = 0; k < bn-1; k++){
         char nextch = a[k+1];
         for(int l = 0; l < bn; l++){
            for(int r = l+k; r < bn; r++){
               if(!dp[l][r]) 
                  continue;
               
               if(l > 0){
                  if(b[l-1] == nextch){
                     dp[l-1][r] = true;
                  }
               }
               
               if(r < bn-1){
                  if(b[r+1] == nextch){
                     dp[l][r+1] = true;
                  }
               }
            }
         }
      }
      
      if(dp1[l][r]){
         for(int r : nextf){
            for(int l : nextb){
               dp[an][l][r] = 1;
            }
         }
      }
      
      for(int l = 1; l < bn; 
      
      
      
   }
   
   
   
   
   
   return 0;
}