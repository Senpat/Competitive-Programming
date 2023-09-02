#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<pair<int,int>> array(n);
   
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      array[k] = make_pair(a,b);
      
   }
   
   sort(array.begin(),array.end());
   
   
   multiset<int> endtimes;
   int answer = 0;
   for(int k = 0; k < n; k++){
      auto it = endtimes.lower_bound(array[k].first+1);
      endtimes.erase(endtimes.begin(),it);
      
      if(endtimes.size() < m){
         endtimes.insert(array[k].second);
         answer++;
      } else {
         if(array[k].second < *endtimes.rbegin()){
            endtimes.erase(--endtimes.end());
            endtimes.insert(array[k].second);
         }
      }
   }
   
   cout << answer << endl;
   
      
   
   
   return 0;
}