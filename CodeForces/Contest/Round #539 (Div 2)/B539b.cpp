#include <bits/stdc++.h>

using namespace std;




int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   int a[n];
   int answer = 0;
   for(int k = 0; k < n; k++){
      cin >> a[k];
      answer += a[k];
   }
   
   int maxsave = 10000000;
   
   for(int k = 2; k <= 100; k++){
      //find smallest number;
      int small = 101;
      int sindex = -1;
      for(int j = 0; j < n; j++){
         if(a[j] < small){
            sindex = j;
            small = a[j];
         }
      }
      
      //find biggest number that divides k;
      int big = -1;
      int bigindex = -1;
      for(int j = 0; j < n; j++){
         if(a[j] >= k && a[j] % k == 0){
            if(a[j] > big){
               big = a[j];
               bigindex = j;
            
            }
         }
      }
      
      if(big == -1) continue;
      
      int save = 0;
      
      //if(small * k * k <= big){
         save = a[sindex] * k - a[sindex];
         save += a[bigindex] / k - a[bigindex];
         //cout << k << " " << save << endl;
      //}
      
      maxsave = min(maxsave,save);
   }
   
   
   cout << min(answer,answer+maxsave);
   
   
   return 0;
}