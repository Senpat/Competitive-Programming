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
      
      vector<vector<long long>> matrix(n,vector<long long>(m,0LL));
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            cin >> matrix[k][j];
         }
      }
      
      long long minavg = LLONG_MAX;
      long long maxavg = LLONG_MIN;
      vector<long long> avgs(n,0LL);
      for(int k = 0; k < n; k++){
         long long avg = 0LL;
         for(int j = 0; j < m; j++){
            avg += matrix[k][j]*(long long)j;
         }
         
         minavg = min(minavg,avg);
         maxavg = max(maxavg,avg);
         
         avgs[k] = avg;
      }
      
      int i = -1;
      for(int k = 0; k < n; k++){
         if(avgs[k] == maxavg){
            i = k;
            break;
         }
      }
      
      cout << (i+1) << " " << maxavg-minavg << "\n";
      
   }
   
   
   
   
   return 0;
}