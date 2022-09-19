#include <bits/stdc++.h>

using namespace std;

//from here: https://cp-algorithms.com/combinatorics/bracket_sequences.html#finding-the-lexicographical-next-balanced-sequence
bool next_balanced_sequence(string & s) {
    int n = s.size();
    int depth = 0;
    for (int i = n - 1; i >= 0; i--) {
        if (s[i] == '(')
            depth--;
        else
            depth++;

        if (s[i] == '(' && depth > 0) {
            depth--;
            int open = (n - i - 1 - depth) / 2;
            int close = n - i - 1 - open;
            string next = s.substr(0, i) + ')' + string(open, '(') + string(close, ')');
            s.swap(next);
            return true;
        }
    }
    return false;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      string s;
      cin >> s;
      
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
      
      if(next_balanced_sequence(s)){
         cout << "NO" << endl;
      } else {
         cout << "YES" << endl;
      }
   }
   
   
   
   
   return 0;
}