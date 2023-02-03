#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   string a,b;
   cin >> a >> b;
   
   int n = a.length();
   
   while(true){
      int i;
      cin >> i;
      if(i == -1) break;
      if(i == 0){
         //swap
         a = "" + a.substr(1,1) + a.substr(0,1) + a.substr(2,n-2);
      } else {
         string temp = b.substr(b.length()-i,i) + a.substr(i,n-i);
         b = b.substr(0,n-i) + a.substr(0,i);
         a = temp;
      }
      
      cout << a << endl;
      cout << b << endl;
   }
   
   
   return 0;
}