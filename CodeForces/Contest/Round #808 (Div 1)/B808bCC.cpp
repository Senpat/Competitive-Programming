#include <bits/stdc++.h>
//edited to work for https://www.codechef.com/problems/ARRAY_OPS
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      map<long long,int> tmap1;
      map<long long,int> tmap2;
      
      vector<long long> array(n);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         
         tmap1[array[k]]++;
      }
      
      if(n == 1){
         cout << array[0] << endl;
         continue;
      }
      
      long long answer = 0;
      for(int rep = 0; rep < n-1; rep++){
         long long last = -1;
         for(const auto& p : tmap1){
            if(last == -1){
               if(p.second-1 > 0) tmap2[0] += p.second-1;
               last = p.first;
            } 
            else {
               tmap2[p.first-last] += 1;
               if(p.second-1 > 0) tmap2[0] += p.second-1;
               last = p.first;
            }
         }
      
         if(rep == n-2){
            answer = (*tmap2.rbegin()).first;
         }
         
         swap(tmap1,tmap2);
         tmap2.clear();
      }
      
      cout << answer << endl;
   }  
   
   
   return 0;
}