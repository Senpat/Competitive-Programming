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
      vector<int> array(n,0);
      
      for(int k = 0; k < n; k++){
         cin >> array[k];
      }
      
      vector<pair<int,int>> adds;
      for(int k = 1; k < n; k++){
         if(array[k] < array[k-1]){
            adds.push_back({array[k-1]-array[k],k});
         }
      }
      
      sort(adds.begin(),adds.end());
      
      vector<int> answer(n,1);
      
      for(int k = 0; k < adds.size(); k++){
         answer[n-k-1] = adds[adds.size()-k-1].second+1;
      }
      
      for(int k = 0; k < n; k++){
         cout << answer[k] << " ";
      }
      cout << "\n";
   }
   
   
   return 0;
}