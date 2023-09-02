#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   int N = 100005;
   vector<bool> dp(N);
   dp[0] = true;
   for(int k = 0; k < n; k++){
      for(int j = N-1; j >= 0; j--){
         if(!dp[j]) continue;
         if(array[k]+j < N){
            dp[array[k]+j] = true;
         }
      }
   }
   
   auto num = count(dp.begin(),dp.end(),true)-1;
   cout << num << endl;
   
   for(int k = 1; k < N; k++){
      if(dp[k]){
         cout << k << " ";
      }
   }
   
   return 0;
}