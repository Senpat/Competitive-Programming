#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<long long> array(n,0LL);
      vector<long long> maxes(m,0LL);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         maxes[k%m] = max(maxes[k%m],array[k]);
      }
      
      long long answer = 0LL;
      for(int k = 0; k < m; k++){
         answer += maxes[k];
      }
      
      cout << answer << endl;
   }  
   
   
   return 0;
}