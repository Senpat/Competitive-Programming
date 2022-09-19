#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,x;
      cin >> n >> x;
      
      vector<int> array(n,0);
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      int l = array[0];
      int r = array[0];
      
      int answer = 0;
      
      for(int k = 1; k < n; k++){
         l = min(l,array[k]);
         r = max(r,array[k]);
         
         if(r-l > 2*x){
            answer++;
            l = array[k];
            r = array[k];
         }
      }
      
      cout << answer << "\n";
   }
   
   
   
   
   return 0;
}