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
      
      //obligation (number of ( you need
      vector<int> ob1(n);
      ob1[n-1] = 1;
      for(int k = n-2; k >= 0; k--){
         if(array[k] == ')'){
            ob1[k] = ob1[k+1]+1;
         } else {
            ob1[k] = max(0,ob1[k+1]-1);
         }
      }
      
      //does ( whenever it can
      vector<char> a1(n);
      int i = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == '?'){
            if(k == n-1){
               a1[k] = ')';
               i--;
            } else if(i-1 >= ob1[k+1]){
               a1[k] = ')';
               i--;
            } else {
               a1[k] = '(';
               i++;
            }
         } else if(array[k] == '('){
            a1[k] = '(';
            i++;
         } else if(array[k] == ')'){
            a1[k] = ')';
            i--;
         }
      }
      
      //does ( whenever it can
      vector<int> ob2(n);
      ob2[n-1] = 1;
      for(int k = n-2; k >= 0; k--){
         if(array[k] != '('){
            ob2[k] = max(0,ob2[k+1]-1);
         } else {
            ob2[k] = ob2[k+1]+1;
         }
      }
      
      vector<char> a2(n);
      i = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == '?'){
            if(k == n-1){
               a2[k] = ')';
               i--;
            } else if(i+1 <= ob2[k+1]){
               a2[k] = '(';
               i++;
            } else {
               a2[k] = ')';
               i--;
            }
         } else if(array[k] == '('){
            a2[k] = '(';
            i++;
         } else if(array[k] == ')'){
            a2[k] = ')';
            i--;
         }
      }
         
      bool fail = false;
      for(int k = 0; k < n; k++){
         if(a1[k] != a2[k]){
            fail = true;
            break;
         }
      }
      
      if(fail){
         cout << "NO" << endl;
      } else {
         cout << "YES" << endl;
      }
      
      for(int k = 0; k < n; k++){
         cout << a1[k];
      }
      cout << endl;
      for(int k = 0; k < n; k++){
         cout << a2[k];
      }
      cout << endl;
      
   }
   
   
   
   
   return 0;
}