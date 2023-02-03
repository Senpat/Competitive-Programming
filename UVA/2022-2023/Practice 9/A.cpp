#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   while(true){
      int n,m;
      cin >> n;
      if(n == 0) break;
      cin >> m;
      
      unordered_set<string> classes;
      
      for(int k = 0; k < n; k++){
         string s;
         cin >> s;
         classes.insert(s);
      }
      
      bool fail = false;
      for(int k = 0; k < m; k++){
         int cn,req;
         cin >> cn >> req;
         int has = 0;
         for(int j = 0; j < cn; j++){
            string s;
            cin >> s;
            if(classes.find(s) != classes.end()){
               has++;
            }
         }
         
         if(has < req){
            fail = true;
         }
      }
      
      if(fail){
         cout << "no\n";
      } else {
         cout << "yes\n";
      }
   }
   
   return 0;
}