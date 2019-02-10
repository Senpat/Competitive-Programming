#include <bits/stdc++.h>

using namespace std;

#define MAXN 100005

int bitsodd[MAXN];
int bitseven[MAXN];
int bits2[MAXN];
int n;
void updateodd(int i, int x){
   for(; i <= n; i += i&-i){
      bitsodd[i] += x;
   }
}

int psumodd(int i){
   int sum = 0;
   for(; i > 0; i -= i&-i){
      sum += bitsodd[i];
   }
   return sum;
}

void updateeven(int i, int x){
   for(; i <= n; i += i&-i){
      bitseven[i] += x;
   }
}

int psumeven(int i){
   int sum = 0;
   for(; i > 0; i -= i&-i){
      sum += bitseven[i];
   }
   return sum;
}

void update2(int i, int x){
   for(; i <= n; i += i&-i){
      bits2[i] += x;
   }
}

int psum2(int i){
   int sum = 0;
   for(; i > 0; i -= i&-i){
      sum += bits2[i];
   }
   return sum;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n;
   
   string s;
   cin >> s;
   
   int q;
   cin >> q;
   
   //fill bits
   for(int k = 0; k < n; k++){
      if(s[k] == '0') continue;
      if((n-k)%2 == 1){
         update2(k+1,1);
         updateodd(k+1,1);
      } else {
         update2(k+1,2);
         updateeven(k+1,1);
      }
   }
   
   for(int k = 0; k < q; k++){
      int w;
      cin >> w;
      //cout << w << endl;
      if(w == 0){
         int i;
         cin >> i;
         if(s[i] == '1') continue;
         s[i] = '1';
         if((n-i)%2 == 1){
            update2(i+1,1);
            updateodd(i+1,1);
         } else {
            update2(i+1,2);
            updateeven(i+1,1);
         }
      } else {
         int l,r,ans;
         cin >> l >> r;
         ans = psum2(r+1)-psum2(l);
         //cout << k << " " << psum2(r+1) << " " << psum2(l) << endl;
         if((n-r) % 2 == 0){
            ans += psumodd(r+1)-psumodd(l) - (psumeven(r+1)-psumeven(l));
            //cout << k << " " << psumodd(r+1)-psumodd(l) << " " << psumeven(r+1)-psumeven(l) << endl;
         }
         cout << (ans+3)%3 << endl;
      }
   }
   /*
   for(int k = 0; k <= n; k++){
      cout << psum2(k) << " ";
   }
   cout << endl;
   for(int k = 0; k <= n; k++){
      cout << psumodd(k) << " ";
   }
   cout << endl;
   for(int k = 0; k <= n; k++){
      cout << psumeven(k) << " ";
   }
   cout << endl;
   */
   return 0;
}