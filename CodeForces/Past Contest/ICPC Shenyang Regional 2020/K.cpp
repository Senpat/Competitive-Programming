#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   set<int> seen;
   vector<int> nums;
   
   map<int,int> pos;
   map<int,int> neg;
   
   double totpos = 0.0;
   double totneg = 0.0;
   
   for(int k = 0; k < n; k++){
      char c;
      int i;
      cin >> c >> i;
      
      if(seen.find(i) == seen.end()){
         seen.insert(i);
         nums.push_back(i);
      }
      
      if(c == '+'){
         pos[i]++;
         totpos++;
      } else {
         neg[i]++;
         totneg++;
      }
   }
   
   sort(nums.begin(),nums.end());
   
   double answer = 0.0;
   
   int prevp = -1;
   int prevn = -1;
   
   int seenp = 0;
   int seenn = 0;
   
   for(int k = nums.size()-1; k >= 0; k--){
      if(neg.find(nums[k]) != neg.end()){
         if(prevn != -1){
            double pd = (double)prevp/totpos;
            double nd = (double)(seenn-prevn)/totneg;
            
            answer += pd*nd;
         }
         prevp = seenp;
         prevn = seenn;
      }
      
      seenp += pos[nums[k]];
      seenn += neg[nums[k]];
      //cout << answer << endl;
   }
   
   double pd = (double)prevp / totpos;
   double nd = (totneg - (double)prevn)/totneg;
   answer += pd*nd;
   
   
   cout << setprecision(15) << answer << endl;
   
   return 0;
}