
#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   unordered_map<int,int> indexof;
   indexof.reserve(1024);
   indexof.max_load_factor(0.25);
   int i21 = -1;
   int i22 = -1;
   for(int k = 1; k <= n; k++){
      int i;
      cin >> i;
      
      if(i == m/2){
         if(i21 == -1) i21 = k;
         else i22 = k;
      }
      
      indexof[i] = k;
      
   }
   
   if(m % 2 == 0 && i22 != -1){
      cout << i21 << " " << i22 << endl;
   } else {
      bool found = false;
      for(auto it : indexof){
         int x = it.first;
         int index = it.second;
         if(m % 2 == 0 && m/2 == x) continue;
         if(indexof.find(m-x) != indexof.end()){
            cout << index << " " << indexof[m-x] << endl;
            found = true;
            break;
         }
      }
      
      if(!found){
         cout << "IMPOSSIBLE" << endl;
      }
   }
   
   return 0;
}