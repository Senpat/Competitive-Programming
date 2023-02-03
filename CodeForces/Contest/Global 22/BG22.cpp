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
      
      vector<long long> array(m);
      for(int k = 0; k < m; k++){
         cin >> array[k];
      }
      
      if(m == 1){
         cout << "Yes\n";
         continue;
      }
      
      bool fail = false;
      vector<long long> cur;
      for(int k = m-2; k >= 0; k--){
         cur.push_back(array[k+1]-array[k]);
         if(cur.size() > 1){
            if(cur[cur.size()-1] > cur[cur.size()-2]){
               fail = true;
               break;
            }
         }
      }
      
      if(!fail){
         if(array[0] < 0){
            long long next = -array[0]/(n-m+1);
            if(-next > cur[cur.size()-1]){
               fail = true;
            }
         } 
         else {
            long long next = (array[0]+n-m+1-1)/(n-m+1);
            if(next > cur[cur.size()-1]){
               fail = true;
            }
         }
         
      }
      
      if(fail){
         cout << "No\n";
      } 
      else {
         cout << "Yes\n";
      }
            
   }
   
   
   return 0;
}