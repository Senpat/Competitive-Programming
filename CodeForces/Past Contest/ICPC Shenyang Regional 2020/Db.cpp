#include <bits/stdc++.h>

//tutorial
//even big n have a lot of random cases, no pattern
//consider prefix sum (odd path matches 0s to 1s)

using namespace std;

int n;

vector<string> answer;

vector<char> path;

void dfs(int x, int num0, int pref){
   if(answer.size() >= 100) return;
   
   //total 0s must be between (n+1)/2 and (n+2)/2
   if(num0 + n-x < (n+1)/2) return;
   if(num0 > (n+2)/2) return;
   
   if(x == n){
      answer.push_back(string(path.begin(),path.end()));
      return;
   }
   
   //add b first
   path.push_back('b');
   dfs(x+1,num0 + 1-pref, pref);
   path.pop_back();
   
   path.push_back('r');
   dfs(x+1,num0 + pref, 1-pref);
   path.pop_back();
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n;
   
   path.push_back('b');
   dfs(1,2,0);
   path.pop_back();
   
   path.push_back('r');
   dfs(1,1,1);
   path.pop_back();
   
   long long nl = (long long)n;
   cout << ((nl+1)/2L)*((nl+2)/2L) << endl;
   for(const string& s : answer){
      cout << s << endl;
   }
   
   
   
   return 0;
}