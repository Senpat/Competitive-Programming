#include <bits/stdc++.h>

using namespace std;

long long fac[1005];
int freq[26];
long long MOD = 1000000007;

//from geeksforgeeks
int modInverse(int a, int m) 
{ 
    int m0 = m; 
    int y = 0, x = 1; 
  
    if (m == 1) 
      return 0; 
  
    while (a > 1) 
    { 
        // q is quotient 
        int q = a / m; 
        int t = m; 
  
        // m is remainder now, process same as 
        // Euclid's algo 
        m = a % m, a = t; 
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
   
   int n;
   cin >> n;
   
   string s;
   cin >> s;
   
   fac[0] = 1;
   
   for(int k = 1; k <= 1000; k++){
      fac[k] = (k * fac[k-1] + MOD) % MOD;
   }
   
   for(int k = 0; k < n; k++){
      freq[s[k]-'a']++;
   }
   
   long long answer = fac[n];
   
   for(int k = 0; k < 26; k++){
      if(freq[k] <= 1) continue;
      answer = (answer * modInverse(fac[freq[k]],MOD) + MOD) % MOD;
   }
   
   cout << answer;
   
   
   
   
   
   
   return 0;
}