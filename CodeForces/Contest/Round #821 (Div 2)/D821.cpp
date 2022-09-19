#include <bits/stdc++.h>
//solves D1
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      long long x,y;
      cin >> n >> x >> y;
      
      //cout << n << " " << x << " " << y << endl;
      /*
      vector<char> a(n);
      vector<char> b(n);
      for(int k = 0; k < n; k++){
         cin >> a[k];
      }
      for(int k = 0; k < n; k++){
         cin >> b[k];
      }*/
      
      string a,b;
      cin >> a >> b;
      
      int nd = 0;
      vector<int> array(n,0);
      vector<int> ds;
      for(int k = 0; k < n; k++){
         if(a[k] != b[k]){
            array[k] = 1;
            nd++;
            ds.push_back(k);
         }
         else array[k] = 0;
      }
      
      if(nd % 2 == 1){
         cout << -1 << "\n";
         continue;
      }
      
      if(nd == 0){
         cout << 0 << "\n";
         continue;
      }
      
      if(y <= x){
         long long answer = -1;
         if(nd > 2){
            answer = y*(long long)(nd/2);
         } else {
            //nd == 2
            if(ds[0]+1 == ds[1]){
               answer = min(x,y*2LL);
            } else {
               answer = y;
            }
         }
         
         cout << answer << "\n";
      
      }
            
   
   }
   
   
   return 0;
}