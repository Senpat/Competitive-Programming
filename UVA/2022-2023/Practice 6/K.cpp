#include <bits/stdc++.h>

using namespace std;

//first letter is v
bool startv(int n, int m, bool print){
   if(n <= 0 || m <= 0) return false;
   //if(n == 0 && m == 0) return true;
   /*
   solution of system of equations:
   20c + 4v = m
   c + v = n
   */
   
   if((m-4*n)%16 != 0){
      return false;
   }
   
   int c = (m-4*n)/16;
   int v = n-c;
   
   if(v < 0 || c < 0){
      return false;
   }
   if(v > 2*c+2 || v < c+1){
      return false;
   }
   
   if(!print) 
      return true;
   
   //either 1 or 2 v's in each gap
   int gaps = c+1;
   int twos = v-gaps;
   
   vector<string> vs;
   for(int k = 0; k < twos; k++) vs.push_back("aa");
   for(int k = 0; k < gaps-twos; k++) vs.push_back("a");
   
   for(int k = 0; k < vs.size(); k++){
      cout << vs[k];
      if(k < vs.size()-1){
         cout << "b";
      }
   }
   
   
   return true;
   
   
}

bool solve(int n, int m){
   //v
   if(n == 1){
      if(m == 4){
         cout << "a";
         return true;
      }
      return false;
   }
   //vv or cv
   if(n == 2 && m == 29){
      cout << "aa";
      return true;
   }
   
   //cvvc
   if(startv(n-4,m-48,false)){
      cout << "baab";
      startv(n-4,m-48,true);
      return true;
   } 
   //vc
   else if(startv(n-2,m-24,false)){
      cout << "ab";
      startv(n-2,m-24,true);
      return true;
   }
   //vvc or cvc
   else if(startv(n-3,m-49,false)){
      cout << "aab";
      startv(n-3,m-49,true);
      return true;
   }
   
   return false;
   
   
}




int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   
   int n,m;
   cin >> n >> m;
   
   
   if(!solve(n,m)){
      cout << "IMPOSSIBLE";
   }
   
   cout << endl;
   
   
   return 0;
}