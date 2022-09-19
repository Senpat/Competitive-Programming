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
      vector<int> array(n+1,0);
      for(int k = 1; k <= n; k++){
         cin >> array[k];
      }
      
      vector<pair<int,int>> answer;
      
      int first = array[1] % 2;
      //get last index where %2 == first
      int lastfirst = 1;
      for(int k = 2; k <= n; k++){
         if(array[k]%2 == first) lastfirst = k;
      }
      
      for(int k = lastfirst-1; k >= 1; k--){
         if(array[k] % 2 == first){
            answer.push_back(make_pair(k,lastfirst));
         }
      }
         
      for(int k = 2; k <= n; k++){
         if(array[k] % 2 != first){
            answer.push_back(make_pair(1,k));
         }
      }
      
      cout << answer.size() << "\n";
      for(int k = 0; k < answer.size(); k++){
         cout << answer[k].first << " " << answer[k].second << "\n";
      }
   }
   
   
   return 0;
}