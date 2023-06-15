#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   vector<int> h;
   map<int,int> tmap;
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      h.push_back(i);
      
      tmap[i]++;
   }
   
   vector<int> t;
   for(int k = 0; k < m; k++){
      int i;
      cin >> i;
      t.push_back(i);
   }
   
   for(int k = 0; k < m; k++){
      auto lb = tmap.upper_bound(t[k]);
      if(lb == tmap.begin()){
         cout << "-1" << endl;
         continue;
      }
      lb--;
      
      int i = lb->first;
      cout << i << endl;
      tmap[i]--;
      if(tmap[i] == 0){
         tmap.erase(i);
      }
   }
   
   
   
   
   return 0;
}