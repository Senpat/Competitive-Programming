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
      vector<int> array;
      array.push_back(0);
      for(int k = 0; k < n; k++){
         int i;
         cin >> i;
         array.push_back(i);
      }
      
      
      vector<pair<int,int>> pairs;
      for(int j = 2; j <= n; j++){
         for(int k = 1; k < j; k++){
            if(array[k] == array[j]) pairs.push_back(make_pair(k,j));
         }
      }
      
      long long answer = 0LL;
      
      vector<long long> numfirst(n+1);
      vector<long long> numsecond(n+1);
      
      int i = 0;
      
      for(int k = 2; k <= n; k++){
         unordered_set<int> firstadd;
         
         long long pairsadded = 0LL;
         
         long long psumfirst = 0LL;
         long long psumsecond = 0LL;
         int firsti = 0;
         int secondi = 0;
         
         while(i < pairs.size() && pairs[i].second == k){
            
            while(firsti < pairs[i].first-1){
               psumfirst += numfirst[firsti+1];
               firsti++;
            }
            
            while(secondi < pairs[i].first){
               psumsecond += numsecond[secondi+1];
               secondi++;
            }
            
            answer += psumfirst-psumsecond;
            
            firstadd.insert(pairs[i].first);
            
            pairsadded++;
            i++;
         }
         
         for(auto x : firstadd){
            numfirst[x]++;
         }
         
         numsecond[k] += pairsadded;
      }
      
      cout << answer << endl;
   
      
   
   
   
   
   return 0;
}