#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   
   int O = 100;
   
   for(int q = 0; q < t; q++){
      int n,c;
      cin >> n >> c;
      vector<int> array(n);
      vector<int> freq(O+1,0);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         freq[array[k]]++;
      }
      
      int answer = 0;
      for(int k = 1; k <= O; k++){
         answer += min(c,freq[k]);
      }
      
      cout << answer << "\n";
      
      
   }
   
   return 0;
}