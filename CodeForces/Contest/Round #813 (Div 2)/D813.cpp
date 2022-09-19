#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
  
   int BIG = 1000000000;    
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<int> array(n,0);
      vector<pair<int,int>> tosort;
      for(int k = 0; k < n; k++){
         cin >> array[k];
         
         tosort.push_back(make_pair(array[k],k));
      }
      
      sort(tosort.begin(),tosort.end());
      
      for(int k = 0; k < m; k++){
         array[tosort[k].second] = BIG;
      }
      
      //answer is min(2*min, max(all adjacent mins))
      
      int getmin = BIG;
      int adjminmax = 0;
      
      for(int k = 0; k < n; k++){
         getmin = min(getmin,array[k]);
         if(k < n-1){
            adjminmax = max(adjminmax,min(array[k],array[k+1]));
         }
      }
      
      int answer = min(2*getmin,adjminmax);
      cout << answer << "\n";
      
   }
   
   return 0;
}