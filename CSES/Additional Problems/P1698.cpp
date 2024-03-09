#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<int> array(n+1);
   for(int k = 1; k <= n; k++){
      cin >> array[k];
   }
   
   vector<vector<pair<int,int>>> answer;
   
   while(true){
      //find cycles
      vector<pair<int,int>> cur;
      
      vector<bool> seen(n+1,false);
      for(int k = 1; k <= n; k++){
         if(array[k] == k) continue;
         if(seen[k]) continue;
         
         vector<int> path;
         path.push_back(k);
         seen[k] = true;
         int i = array[k];
         while(i != k){
            seen[i] = true;
            path.push_back(i);
            i = array[i];
         }
         
         if(path.size() <= 3){
            cur.push_back(make_pair(path[0],path[1]));
         } else {
            for(int k = 1; path.size()-k > k; k++){
               cur.push_back(make_pair(path[k],path[path.size()-k]));
            }
         }
      }
      
      if(cur.size() == 0) break;
      
      //make switches
      for(auto [a,b] : cur){
         int temp = array[a];
         array[a] = array[b];
         array[b] = temp;
      }
      
      //update answer
      answer.push_back(cur);
   }
   
   cout << answer.size() << endl;
   for(int k = 0; k < answer.size(); k++){
      cout << answer[k].size() << endl;
      for(auto [a,b] : answer[k]){
         cout << a << " " << b << endl;
      }
   }
         
   
   
   return 0;
}