#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   vector<pair<char,int>> array(n);
   int poundindex = -1;
   for(int k = 0; k < n; k++){
      if(s[k] == '#'){
         poundindex = k;
      }
      array[k] = make_pair(s[k],k);
   }
   
   sort(array.begin(),array.end());
   
   int i = poundindex;
   string answer = "";
   for(int k = 0; k < n-1; k++){
      answer += array[i].first;
      i = array[i].second;
   }
   
   cout << answer << endl;
   
   
   return 0;
}