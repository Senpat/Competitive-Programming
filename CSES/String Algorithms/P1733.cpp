#include <bits/stdc++.h>

using namespace std;

vector<int> getz(const string& s){
   int n = s.size();
   vector<int> z(n);
   int l = -1;
   int r = -1;
   for(int i = 1; i < n; i++){
      z[i] = i >= r ? 0 : min(r-i, z[i-l]);
      while(i + z[i] < s.size() && s[i+z[i]] == s[z[i]]){
         z[i]++;
      }
      if(i + z[i] > r){
         l = i;
         r = i+z[i];
      }
   }
   
   return z;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   string s;
   cin >> s;
   int n = s.size();
   
   vector<int> z = getz(s);
   
   vector<int> answer;
   for(int k = 0; k < n; k++){
      if(k + z[k] >= n){
         answer.push_back(k);
      }
   }
   answer.push_back(n);
   
   for(int i : answer){
      cout << i << " ";
   }
   
   return 0;
}