#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      long long n,m;
      int x;
      cin >> n >> m >> x;
      
      vector<long long> array(x);
      for(int k = 0; k < x; k++){
         cin >> array[k];
      }
      
      sort(array.begin(),array.end());
      
      bool answer1, answer2;
      
      long long cols = 0L;
      long long extra = 0L;
      bool fail = false;
      for(int k = x-1; k >= 0; k--){
         //fill as many cols
         long long maxcols = array[k]/n;
         if(maxcols < 2){
            continue;
         }
         
         if(cols == m-1){
            if(extra > 0){
               cols = m;
            } else {
               fail = true;
            }
            break;
         }
         
         extra += max(0LL,maxcols-2);
         cols = min(m,cols+maxcols);
         
      }
      
      if(fail || cols != m){
         answer1 = false;
      } else {
         answer1 = true;
      }
      
      cols = 0L;
      extra = 0L;
      fail = false;
      for(int k = x-1; k >= 0; k--){
         //fill as many cols
         long long maxcols = array[k]/m;
         if(maxcols < 2){
            continue;
         }
         
         if(cols == n-1){
            if(extra > 0){
               cols = n;
            } else {
               fail = true;
            }
            break;
         }
         
         extra += max(0LL,maxcols-2);
         cols = min(n,cols+maxcols);
         
      }
      
      if(fail || cols != n){
         answer2 = false;
      } else {
         answer2 = true;
      }
      
      if(answer1 || answer2){
         cout << "Yes\n";
      } else {
         cout << "No\n";
      }
   }
   
   
   
   
   return 0;
}