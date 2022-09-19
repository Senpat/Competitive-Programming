#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      string s;
      cin >> s;
      int n = s.length();
      vector<char> array(n);
      for(int k = 0; k < n; k++){
         array[k] = s[k];
      }
      
      
      vector<char> a2(n);
      for(int k = 0; k < n; k++){
         a2[k] = array[k];
      }
      int l = 0;
      int r = n-1;
      bool fail = false;
      while(l < r){
         //l = '(' and r = ')';
         if(array[l] == ')') {
            a2[l] = array[l];
            l++;
         } else if(array[r] == '('){
            a2[r] = array[r];
            r--;
         } else {
            a2[l] = '(';
            a2[r] = ')';
            l++;
            r--;
         }
      }
      
      if(fail){
         cout << "NO" << endl;
      } else {
         cout << "YES" << endl;
      }
      
   }
   
   
   
   
   return 0;
}