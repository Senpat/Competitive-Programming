#include <bits/stdc++.h>

using namespace std;

#define MAXN 100005

int bits[MAXN];
int n;

int pow2[MAXN];

void update(int i, int x){
   for(; i <= n; i += i&-i){
      bits[i] += x%3;
   }
}

int psum(int i){
   int sum = 0;
   for(; i > 0; i -= i&-i){
      sum += bits[i];
   }
   return sum;
}

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
   
   cin >> n;
   
   string s;
   cin >> s;
   //cout << s << endl;
   pow2[0] = 1;
   for(int k = 1; k < MAXN; k++){
      //if(k%10000 == 0) cout << k << endl;
      pow2[k] = (pow2[k-1] * 2 + 3) % 3;
      //cout << pow2[k] << " ";
   }
   //cout << endl;
   
   //update tree
   for(int k = 0; k < n; k++){
      //cout << k << endl;
      if(s[k] == '1') update(k+1,pow2[n-k-1]);
   }
   //cout << "hi" << endl;
   // for(int k = 1; k <= n; k++){
//       cout << psum(k) << " ";
//    }
//    cout<< endl;
   int q;
   cin >> q;
   
   for(int k = 0; k < q; k++){
      int w;
      cin >> w;
      if(w == 0){
         int i;
         cin >> i;
         
         if(s[i] == '0'){
            update(i+1,pow2[n-i-1]);
            s[i] = '1';
         }
      } 
      else {
         int l,r;
         cin >> l >> r;
         int answer = (psum(r+1)-psum(l));
         //for(int k = 1; k <= n; k++){
         //   cout << psum(k) << " ";
         //}
         //cout<< endl;
         //cout << k << " " << answer << endl;
         answer *= modInverse(pow2[n-r-1],3);
      
         cout << (answer+3)%3 << " ";
      }
   }
   
   
   
   
   
   
   
   
   
   return 0;
}