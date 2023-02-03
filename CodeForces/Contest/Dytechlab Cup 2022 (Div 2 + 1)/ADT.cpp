#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      string s;
      cin >> s;
      
      vector<int> freq(25,0);
      for(int k = 0; k < n; k++){
         freq[s.at(k)-'a']++;
      }
      
      vector<char> answer;
      int l = n/m;
      for(int k = 0; k < m; k++){
         int i = 0;
         for(int j = 0; j < l; j++){
            if(freq[j] == 0) break;
            freq[j]--;
            i++;
         }
         
         answer.push_back('a'+i);
      }
      
      string as(answer.begin(),answer.end());
      cout << as << "\n";
   }
   
   
   return 0;
}