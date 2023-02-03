#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   unordered_set<string> dict;
   while(true){
      string s;
      cin >> s;
      if(s == "#") break;
      dict.insert(s);
   }
   
   vector<string> wordsin;
   while(true){
      string s;
      cin >> s;
      if(s == "#") break;
      wordsin.push_back(s);
   }
   
   vector<string> words;
   string cur = wordsin[0];
   for(int k = 1; k < wordsin.size(); k++){
      if(cur.at(cur.length()-1) == '|'){
         words.push_back(cur.substr(0,cur.length()-1));
         cur = wordsin[k];
      } else {
         cur += wordsin[k];
      }
   }
   words.push_back(cur.substr(0,cur.length()-1));
   
   for(int w = 0; w < words.size(); w++){
      int n = words[w].size();
      vector<int> dp(n,0);
      
      for(int k = 0; k < n; k++){
         int prev = 0;
         if(k >= 1){
            prev = dp[k-1];
         }
         
         string s = "";
         for(int j = 0; j < 10; j++){
            s += words[w][k+j];
            if(dict.find(s) != dict.end()){
               dp[k+j] = max(dp[k+j],prev+1);
               break;
            }
         }
         
         dp[k] = max(dp[k],prev);
      }
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         answer = max(answer,dp[k]);
      }
      
      cout << answer << "\n";
         
   }
   
   
   
   
   return 0;
}