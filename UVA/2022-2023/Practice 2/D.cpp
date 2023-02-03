#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   vector<int> type(n+1,0);
   vector<int> value(n+1,0);
   
   //1-indexed, first element is (0,0) (to start the increasing sequence)
   for(int k = 1; k <= n; k++){
      cin >> type[k] >> value[k];
   }
   
   vector<int> parent(n+1,-1);
   
   vector<vector<int>> lis;
   
   vector<int> start = {0,-1};
   lis.push_back(start);
   
   for(int k = 1; k <= n; k++){
      int l = 0;
      int r = lis.size()-1;
      int ans = -1;
      int curpar = -1;
      
      while(l <= r){
         int mid = l+(r-l)/2;
         
         if(value[k] > value[lis[mid][0]] && type[k] != type[lis[mid][0]]){
            curpar = lis[mid][0];
            ans = mid;
            l = mid+1;
         } 
         else if(lis[mid][1] != -1 && value[k] > value[lis[mid][1]] && type[k] != type[lis[mid][1]]){
            curpar = lis[mid][1];
            ans = mid;
            l = mid+1;
         } 
         else if(value[k] > value[lis[mid][0]] || (lis[mid][1] != -1 && value[k] > value[lis[mid][1]])){
            curpar = -1;
            ans = mid;
            l = mid+1;
         }
         else {
            r = mid-1;
         }
      }
      
      if(curpar == -1) continue;
      
      parent[k] = curpar;
      
      if(ans == lis.size()-1){
         //increase the sequence
         vector<int> curadd = {k,-1};
         lis.push_back(curadd);
      } 
      else {
         //update ans+1
         if(type[lis[ans+1][0]] == type[k]){
            //update 0
            if(value[k] < value[lis[ans+1][0]]){
               lis[ans+1][0] = k;
            }
         } 
         else if(type[lis[ans+1][1]] == type[k]){
            //update 1
            if(value[k] < value[lis[ans+1][1]]){
               lis[ans+1][1] = k;
            }
            
            //switch 1 and 0 if needed
            if(value[lis[ans+1][0]] > value[lis[ans+1][1]]){
               int temp = lis[ans+1][0];
               lis[ans+1][0] = lis[ans+1][1];
               lis[ans+1][1] = temp;
            }
         } 
         else {
            //update either (maintain order)
            if(value[k] < value[lis[ans+1][0]]){
               lis[ans+1][1] = lis[ans+1][0];
               lis[ans+1][0] = k;
            } 
            else if(lis[ans+1][1] == -1 || value[k] < value[lis[ans+1][1]]){
               lis[ans+1][1] = k;
            }
         }
      }
      
   }
   
   int an = lis.size()-1;
   stack<int> answer;
   int i = lis.back()[0];
   while(i != 0){
      answer.push(i);
      i = parent[i];
   }
   
   cout << an << endl;
   while(!answer.empty()){
      cout << answer.top() << " ";
      answer.pop();
   }
   cout << endl;

   
   
   return 0;
}