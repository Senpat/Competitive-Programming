#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   int array[n];
   
   int x = 0;
   
   for(int k = 0; k < n; k++){
      cin >> array[k];
      x = max(x,array[k]);
   }
   
   unordered_set<int> set;
   
   int y = 0;
   for(int k = 0; k < n; k++){
      if(x % array[k] != 0 || set.find(array[k]) != set.end()){
         y = max(y,array[k]);
      }
      set.insert(array[k]);
   }
   
   cout << x << " " << y << endl;
}
   