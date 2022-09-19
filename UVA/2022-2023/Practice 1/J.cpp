#include <bits/stdc++.h>
//wa tc 6
using namespace std;

int N = 1000005;

int B = 2;
vector<long long> BASE = {29LL,31LL};
vector<long long> MOD = {1000000007LL,1000000009LL};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   vector<vector<long long>> pow(B,vector<long long>(N));
   for(int b = 0; b < B; b++){
      pow[b][0] = 1LL;
      for(int k = 1; k < N; k++){
         pow[b][k] = (pow[b][k-1] * BASE[b] + MOD[b])%MOD[b];
      }
   }
   
   int n;
   cin >> n;
   
   vector<string> array(n);
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      reverse(s.begin(),s.end());
      array[k] = s;
   }
   
   //sort by string length (biggest to smallest)
   sort(array.begin(),array.end(), []
      (const string& first, const string& second){
         if(first.length() == second.length()){
            return first < second;
         }
         return first.length() > second.length();
      });
   
   //get hashes of all the strings
   vector<vector<vector<long long>>> hashes(B,vector<vector<long long>>(n));
   
   vector<unordered_set<long long>> allhashes(B,unordered_set<long long>());
   unordered_set<int> skip;
   for(int k = 0; k < n; k++){
      
      bool curskip = true;
      vector<vector<long long>> curhash(B,vector<long long>(array[k].length()));
      for(int b = 0; b < B; b++){
      
         curhash[b][0] = (long long)(array[k][0]-'a'+1);
         for(int j = 1; j < array[k].length(); j++){
            long long prod = (((long long)(array[k][j]-'a'+1))*pow[b][j] + MOD[b])%MOD[b];
            curhash[b][j] = (curhash[b][j-1] + prod + MOD[b])%MOD[b];
         }
      
      //if hash of full string has been seen before, skip it
         if(allhashes[b].find(curhash[b][curhash[b].size()-1]) == allhashes[b].end()){
            curskip = false;
         }
      }
      
      if(curskip){
         skip.insert(k);
      } else {
         for(int b = 0; b < B; b++){
            for(int j = 0; j < array[k].length(); j++){
               allhashes[b].insert(curhash[b][j]);
            }
            
            
            hashes[b][k] = curhash[b];
         }
      }
   }
   
   int l = 1;
   int r = N;
   int ans = 0;
   while(l <= r){
      int mid = l + (r-l)/2;
      
      bool found = false;
      
      
      vector<unordered_set<long long>> uset(B,unordered_set<long long>());
      for(int k = 0; k < n; k++){
         if(skip.find(k) != skip.end()) 
            continue;
         if(array[k].length() <= mid) 
            continue;
         
         bool curfound = true;
         for(int b = 0; b < B; b++){
            long long curhash = hashes[b][k][mid-1];
            
            if(uset[b].find(curhash) == uset[b].end()){
               curfound = false;
            }
            
            uset[b].insert(curhash);
         }
         
         if(curfound){
            found = true;
            break;
         }
      }
      
      if(found){
         l = mid+1;
         ans = mid;
      } 
      else {
         r = mid-1;
      }
   }
   
   cout << ans << endl;
   
   return 0;
}