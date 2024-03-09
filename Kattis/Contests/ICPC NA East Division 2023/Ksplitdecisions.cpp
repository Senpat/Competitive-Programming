#include <bits/stdc++.h>

using namespace std;

const long long p26[6] = {1LL,26LL,676LL,17576LL,456976LL,11881376LL};

long long gethash(int a, int b, int c, int d, int e, int f){
   long long h1 = p26[0]*a + p26[1]*b + p26[2]*c + p26[3]*d + p26[4]*e + p26[5]*f;
   long long h2 = p26[2]*a + p26[3]*b + p26[0]*c + p26[1]*d + p26[4]*e + p26[5]*f;
   return min(h1,h2);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<vector<int>> words(n,vector<int>());
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < s.length(); j++){
         words[k].push_back(s[j]-'A');
      }
   }
   
   int N = 12500000;
   unordered_map<long long, int> freq;
   freq.reserve(1024);
   freq.max_load_factor(0.25);
   
   for(int k = 0; k < n; k++){
      for(int j = k+1; j < n; j++){
         if(words[k].size() != words[j].size()) continue;
         
         int curn = words[k].size();
         
         int found = -1;
         bool fail = false;
         
         if(words[k][0] != words[j][0] && words[k][1] == words[j][1]){     //first character is differing by itself
            continue;
         }
         
         for(int h = 1; h < curn; h++){
            if(words[k][h] != words[j][h] && words[k][h-1] != words[j][h-1]){
               if(found != -1){
                  fail = true;
                  break;
               }
               found = h-1;
            } else if(words[k][h] != words[j][h]){
               if(h+1 >= curn || words[k][h+1] == words[j][h+1]){
                  //differing character by itself, fail
                  fail = true;
                  break;
               }
            }
         }
         
         if(!fail && found != -1){
            //cout << k << " " << j << " " << found << endl;
            freq[gethash(words[k][found],words[k][found+1],words[j][found],words[j][found+1],found,curn-1)]++;
         }
      }
   }
   
   int answer = 0;
   for(auto [k,v] : freq){
      if(v == 1) answer++;
   }
   
   cout << answer << endl;
   
   return 0;
}