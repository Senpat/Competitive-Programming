#include <bits/stdc++.h>

using namespace std;

int FAIL = 9999999;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   unordered_map<string,int> vars;
   unordered_map<int,string> names;
   
   while(true){
      string s;
      getline(cin,s);
      
      if(s.empty()) break;
      
      stringstream ss(s);
      
      string s1;
      getline(ss,s1,' ');
      
      if(s1 == "def"){
         getline(ss,s1,' ');
         string si;
         getline(ss,si,' ');
         int i = stoi(si);
         if(vars.find(s1) != vars.end()){
            names.erase(vars[si]);
         }
         vars[s1] = i;
         names[i] = s1;
      } else if(s1 == "calc"){
         string eq = s.substr(5);
         
         int val = 0;
         getline(ss,s1,' ');
         if(vars.find(s1) == vars.end()){
            cout << eq << " unknown\n";
            continue;
         }
         
         val = vars[s1];
         
         while(true){
            getline(ss,s1,' ');
            if(s1 == "=") break;
            string s2;
            getline(ss,s2,' ');
            if(vars.find(s2) == vars.end()){
               val = FAIL;
               break;
            }
            int i = vars[s2];
            if(s1 == "+") val += i;
            else if(s1 == "-") val -= i;
            
         }
         
         if(val == FAIL || names.find(val) == names.end()){
            cout << eq << " unknown\n";
         } else {
            cout << eq << " " << names[val] << "\n";
         }
         
         
      } else if(s1 == "clear"){
         vars.clear();
         names.clear();
      } 
   
   
   }
   
   
   
   return 0;
}