#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> a(n);
      for(int k = 0; k < n; k++){
         cin >> a[k];
      }
      
      vector<long long> b(n);
      for(int k = 0; k < n; k++){
         cin >> b[k];
      }
      
      vector<int> a0;
      vector<int> a1;
      for(int k = 0; k < n; k++){
         if(a[k] == 0){
            a0.push_back(b[k]);
         } 
         else {
            a1.push_back(b[k]);
         }
      }
      
      sort(a0.begin(),a0.end());
      sort(a1.begin(),a1.end());
      
      int n0 = a0.size();
      int n1 = a1.size();
      
      if(n0 == 0 || n1 == 0){
         long long sum = 0LL;
         for(int k = 0; k < n; k++){
            sum += b[k];
         }
         cout << sum << "\n";
         continue;
      }
      
      //0 1 0 1 ...
      long long sum0 = a0[0];
      long long last = 0;
      
      int i1 = 0;
      int i0 = 0;
      while(i1 < n1 || i0 < n0-1){
         if(last == 0){
            if(i1 < n1){
               sum0 += a1[n1-i1-1]*2;
               last = 1;
               i1++;
            } 
            else {
               sum0 += a0[n0-i0-1];
               i0++;
            }
         } 
         else {
            if(i0 < n0-1){
               sum0 += a0[n0-i0-1]*2;
               last = 0;
               i0++;
            } 
            else {
               sum0 += a1[n1-i1-1];
               i1++;
            }
         }
      }
      
      //1 0 1 0 ...
      long long sum1 = a1[0];
      last = 1;
      
      i1 = 0;
      i0 = 0;
      while(i1 < n1-1 || i0 < n0){
         if(last == 0){
            if(i1 < n1-1){
               sum1 += a1[n1-i1-1]*2;
               last = 1;
               i1++;
            } 
            else {
               sum1 += a0[n0-i0-1];
               i0++;
            }
         } 
         else {
            if(i0 < n0){
               sum1 += a0[n0-i0-1]*2;
               last = 0;
               i0++;
            } 
            else {
               sum1 += a1[n1-i1-1];
               i1++;
            }
         }
      }
      //cout << sum0 << " " << sum1 << "\n";
      cout << max(sum0,sum1) << "\n";
      
   }
   
   return 0;
}