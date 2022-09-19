#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   unordered_set<int> squares;
   for(int k = 0; k <= 1000; k++){
      squares.insert(k*k);
   }
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n,-1);
      
      int i = n-1;         //value
      int x = n-1;         //index
      
      while(i >= 0){
         //find a new segment
         int prevx = x;
         while(i >= 0 && squares.find(i+x) == squares.end()){
            x--;
         }
         
         for(int k = x; k <= prevx; k++){
            array[k] = i;
            i--;
         }
         x--;
      }
      
      for(int k = 0; k < n; k++){
         cout << array[k] << " ";
      }
      cout << "\n";
   }
   
   return 0;
}