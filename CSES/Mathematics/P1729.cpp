#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   vector<int> array(m);
   for(int k = 0; k < m; k++){
      cin >> array[k];
   }
   
   vector<bool> win(n+1,false);
   win[0] = false;
   for(int k = 0; k < n; k++){
      if(win[k]) continue;
      for(int j = 0; j < m; j++){
         if(k+array[j] <= n){
            win[k+array[j]] = true;
         }
      }
   }
   
   for(int k = 1; k <= n; k++){
      if(win[k]) cout << "W";
      else cout << "L";
   }
   
   
   
   return 0;
}