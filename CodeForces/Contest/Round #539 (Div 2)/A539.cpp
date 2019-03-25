#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,v;
   
   cin >> n >> v;
   
   int total = min(n-1,v);
   int fuel = total;
   if(total == n-1){
      cout << total;
      return 0;
   }
   
   for(int k = 2; fuel < n-1; k++){
      total += k;
      fuel++;
   }
   
   cout << total;
   
   
   
   
   
   return 0;
}