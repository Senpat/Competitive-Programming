#include <bits/stdc++.h>

using namespace std;


      
map<long long, long long> starttoend;
map<long long, long long> endtostart;

void join(long long x){
   //x not in the intervals
   
   auto er = endtostart.lower_bound(x);
   auto el = endtostart.lower_bound(x);
   bool adjl = true;
   if(el == endtostart.begin()) adjl = false;
   else el--;
   
   bool adjr = (er != endtostart.end()) && (x == er->second-1);
   if(adjl){
      adjl = (x == el->first+1);
   }
   //cout << x << " " << adjl << " " << adjr << endl;
   
   if(adjr && adjl){
      er->second = el->second;
      endtostart.erase(el);
   } 
   else if(adjr){
      er->second = x;
   } 
   else if(adjl){
      long long newv = el->second;
      //cout << "newv: " << newv << endl;
      endtostart.erase(el);
      endtostart[x] = newv;
   } 
   else {
      endtostart[x] = x;
   }
   
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<long long> array(n+1);
      for(int k = 1; k <= n; k++){
         cin >> array[k];
         array[k] += (long long)k;
      }
      
      //starttoend = map<long long,long long>();
      endtostart = map<long long,long long>();
      
      vector<long long> answer;
      answer.push_back(array[1]);
      //starttoend[vector[1]] = array[1];
      endtostart[array[1]] = array[1];
      
      for(int k = 2; k <= n; k++){
         auto ei = endtostart.lower_bound(array[k]);
         if(ei != endtostart.end() && ei->second <= array[k]){
            answer.push_back(ei->second-1);
            //join ei->second-1
            join(ei->second-1);
         } 
         else {
            answer.push_back(array[k]);
            //join array[k]
            join(array[k]);
         }
      }
      
      sort(answer.begin(),answer.end());
      
      for(int k = answer.size()-1; k >= 0; k--){
         cout << answer[k] << " ";
      }
      cout << endl;
   }
   
   
   
   return 0;
}