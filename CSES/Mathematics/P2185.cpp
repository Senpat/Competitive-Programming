#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   long long n;
   int m;
   cin >> n >> m;
   
   vector<long long> array(m);
   for(int k = 0; k < m; k++){
      cin >> array[k];
   }
   
   long long answer = 0LL;
   
   for(int mask = 1; mask < (1 << m); mask++){
      int numbits = 0;
      long long prod = 1LL;
      bool fail = false;
      for(int j = 0; j < m; j++){
         if((mask & (1 << j)) != 0){
            numbits++;
            if(array[j] > (n + prod - 1)/prod){
               //will overflow
               fail = true;
               break;
            }
            prod *= array[j];
            if(prod < 0 || prod > n){
               fail = true;
               break;
            }
         }
      }
      
      if(fail) continue;
      //cout << answer << "\n";
      if(numbits%2 == 1){
         answer += n/prod;
      } else {
         answer -= n/prod;
      }
      
   }
   
   cout << answer << endl;
   
   return 0;
}