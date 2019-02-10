#include <bits/stdc++.h>

using namespace std;

bool vowel(char c){
   return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string a;
   string b;
   cin >> a >> b;
   
   if((int)a.size() != (int)b.size()){
      cout << "No\n";
      return 0;
   }
   
   for(int k = 0; k < (int)a.size(); k++){
      if(vowel(a[k]) != vowel(b[k])){
         cout << "No\n";
         return 0;
      }
   }
   cout << "Yes\n";
   
   
   
   
   return 0;
}