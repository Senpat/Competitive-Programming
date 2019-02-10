#include <bits/stdc++.h>

using namespace std;

#define MAXN 100005

int bits1[MAXN];
int bits2[MAXN];
int n;
void update1(int i, int x){
   for(; i <= n; i += i&-i){
      bits1[i] += x;
   }
}

int psum1(int i){
   int sum = 0;
   for(; i > 0; i -= i&-i){
      sum += bits1[i];
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
   cout << "hi" << endl;
   cin >> n;
   
   string s;
   cin >> s;
   
   int q;
   cin >> q;
   
   //fill bits
   for(int k = 0; k < n; k++){
      if(s[k] == '0') continue;
      if((n-k)%2 == 0){
         update2(k+1,1);
      } else {
         update2(k+1,2);
      }
      update1(k+1,1);
   }
   
   for(int k = 0; k < q; k++){
      cout << k << endl;
      int w;
      cin >> w;
      if(w == 0){
         int i;
         cin >> i;
         if(s[i] == '1') continue;
         update1(i+1,1);
         s[i] = '1';
         if((n-i)%2 == 1) update2(i+1,2);
         else update2(i+1,1);
      } else {
         int l,r,ans;
         cin >> l >> r;
         ans = psum2(r+1)-psum2(l);
         cout << k << " " << psum2(r+1) << " " << psum2(l) << endl;
         if((n-r) % 2 == 1){
            ans -= psum1(r+1)-psum1(l);
            //cout << k << " " << ans << endl;
         }
         cout << ans%3 << endl;
      }
   }
   
   
   
   
   return 0;
}