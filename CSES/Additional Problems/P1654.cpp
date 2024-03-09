#include <bits/stdc++.h>
//sos dp
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 20;
   int P = (1 << N);
   
   int n;
   cin >> n;
   
   vector<int> array(n);
   vector<int> a(P,0);
   for(int k = 0; k < n; k++){
      cin >> array[k];
      a[array[k]]++;
   }
   
   vector<vector<int>> answer(n,vector<int>(3));
   
   //part 1, subset
   vector<int> dp1(P);
   for(int k = 0; k < P; k++){
      dp1[k] = a[k];
   }
   
   for(int i = 0; i < N; i++){
      for(int mask = 0; mask < P; mask++){
         if((mask & (1 << i)) != 0){
            dp1[mask] += dp1[mask ^ (1 << i)];
         }
      }
   }
   
   //part 2, superset
   vector<int> dp2(P,0);
   for(int k = 0; k < P; k++){
      dp2[k] = a[k];
   }
   
   for(int i = 0; i < N; i++){
      for(int mask = 0; mask < P; mask++){
         if((mask & (1 << i)) == 0){
            dp2[mask] += dp2[mask ^ (1 << i)];
         }  
      }
   }
   
   int mask = P-1;
   for(int k = 0; k < n; k++){
      answer[k][0] = dp1[array[k]];
      answer[k][1] = dp2[array[k]];
      //subset of negation
      answer[k][2] = n-dp1[mask ^ array[k]];
   }
   
   for(int k = 0; k < n; k++){
      cout << answer[k][0] << " " << answer[k][1] << " " << answer[k][2] << "\n";
   }
   
   
   
   
   
   
   return 0;
}