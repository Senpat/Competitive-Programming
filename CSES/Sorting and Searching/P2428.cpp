#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,x;
   cin >> n >> x;
   
   vector<int> array(n);
   unordered_map<int,int> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   int cn = 0;
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      if(hmap.find(i) == hmap.end()){
         hmap[i] = cn;
         cn++;
      }
      
      array[k] = hmap[i];
   }
   
   vector<int> freq(cn);
   freq[array[0]] = 1;
   int distinct = 1;
   
   long long answer = 0LL;
   
   int r = 0;
   for(int l = 0; l < n; l++){
      while(r+1 < n && (distinct+1 <=x || freq[array[r+1]] > 0)){
         freq[array[r+1]]++;
         if(freq[array[r+1]] == 1){
            distinct++;
         }
         r++;
      }
      
      answer += (long long)(r-l+1);
      
      
      freq[array[l]]--;
      if(freq[array[l]] == 0){
         distinct--;
      }
   }
   
   cout << answer << endl;
   
   
   
   return 0;
}