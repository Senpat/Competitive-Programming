#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      map<int,int> tmap1;
      map<int,int> tmap2;
      
      vector<int> array(n);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         
         tmap1[array[k]]++;
      }
      
      int answer = 0;
      for(int rep = 0; rep < n-1; rep++){
         int last = -1;
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