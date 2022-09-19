#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<long long> array(n);
   vector<long long> diffr(n,0LL);
   vector<long long> diffl(n,0LL);
   for(int k = 0; k < n; k++){
      cin >> array[k];
      
      if(k >= 1){
         diffr[k] = max(0LL,array[k-1]-array[k]);
         diffl[k] = max(0LL,array[k]-array[k-1]);
      }
   }
   
   vector<long long> psumr(n,0LL);
   vector<long long> psuml(n,0LL);
   for(int k = 1; k < n; k++){
      psumr[k] = diffr[k] + psumr[k-1];
      psuml[k] = diffl[k] + psuml[k-1];
   }
   
   for(int k = 0; k < m; k++){
      int l,r;
      cin >> l >> r;
      l--,r--;
      
      if(l < r){
         long long answer = psumr[r]-psumr[l];
         cout << answer << endl;
      } else {
         long long answer = psuml[l]-psuml[r];
         cout << answer << endl;
      }
   }
      
   
   
   
   
   
   
   
   return 0;
}