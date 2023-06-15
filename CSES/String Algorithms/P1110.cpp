#include <bits/stdc++.h>

using namespace std;

//booth's algorithm https://en.wikipedia.org/wiki/Lexicographically_minimal_string_rotation
int least_rotation(const string& s){
   int n = s.size();
   vector<int> f(2*n,-1);
   
   int k = 0;
   
   for(int j = 1; j < 2*n; j++){
      int i = f[j-k-1];
      
      while(i != -1 && s[j%n] != s[(k+i+1)%n]){
         if(s[j%n] < s[(k+i+1)%n]){
            k = j-i-1;
         }
         i = f[i];
      }
      if(i == -1 && s[j%n] != s[(k+i+1)%n]){
         if(s[j%n] < s[(k+i+1)%n]){
            k = j;
         }
         f[j-k] = -1;
      } else {
         f[j-k] = i+1;
      }
   }
   
   return k;
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.size();
   
   int i = least_rotation(s);
   
   string answer = "";
   for(int k = 0; k < n; k++){
      answer += s[(k+i)%n];
   }
   
   cout << answer << endl;
   
   
   return 0;
}