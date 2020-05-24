#include <bits/stdc++.h>
//RANDOM ERRORS
using namespace std;

vector<int> values;
vector<int> answer;
vector<vector<int>> adj;
vector<map<int,int>> lis;

void dfs(int v, int p){
   int loc = -1;
   
   if(lis.size() == 0){
      map<int,int> temp;
      lis.push_back(temp);
      lis[0].insert(pair<int,int>(values[v],1));
      loc = 0;
   } 
   else if(values[v] <= lis[0].begin()->first){
          //add to first list
      if(lis[0].find(values[v]) != lis[0].end()){
         lis[0].insert(pair<int,int>(values[v],lis[0][values[v]]+1));
      } 
      else {
         lis[0].insert(pair<int,int>(values[v],1));
      }
      loc = 0;
   } 
   else if(values[v] > lis[lis.size()-1].begin() -> first){
         //add
      map<int,int> temp;
      lis.push_back(temp);
      lis[lis.size()-1].insert(pair<int,int>(values[v],1));
      loc = lis.size()-1;
   } 
   else {
      int l = 0;
      int r = lis.size()-1;
      int ans = r;
         
      while(l <= r){
         int mid = l + (r-l)/2;
            
         if(values[v] <= lis[mid].begin() -> first){
            r = mid-1;
            ans = mid;
         } 
         else {
            l = mid+1;
               
         }
      }
         
      if(lis[ans].find(values[v]) != lis[ans].end()){
         lis[ans].insert(pair<int,int>(values[v],lis[ans][values[v]]+1));
      } 
      else {
         lis[ans].insert(pair<int,int>(values[v],1));
      }
      loc = ans;
   }
   
   answer[v] = lis.size();
      
   for(auto nei : adj[v]){
      if(nei == p) 
         continue;
      dfs(nei,v);
   }
      
      //remove
   if(lis[loc][values[v]] > 1){
      lis[loc].insert(pair<int,int>(values[v],lis[loc][values[v]]-1));
   } 
   else {
      lis[loc].erase(values[v]);
      if(lis[loc].size()==0){
         lis.pop_back();
      }
   }
      
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   cout << "hi" << n << endl;
   values.push_back(0);
   for(int k = 1; k <= n; k++){
      int i;
      cin >> i;
      values.push_back(i);
   }
   
   for(int k = 0; k <= n; k++){
      vector<int> temp;
      adj.push_back(temp);
   }
   
   for(int k = 0; k < n-1; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   dfs(1, -1);
   
   for(int k = 1; k <= n; k++){
      cout << answer[k] << endl;
   }
   
   
   
   
   return 0;
}