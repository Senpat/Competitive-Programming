#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      vector<int> ain(n,0);
      vector<int> bin(n,0);
      for(int k = 0; k < n; k++){
         cin >> ain[k];
      }
      for(int k = 0; k < n; k++){
         cin >> bin[k];
      }
      
      vector<vector<int>> a;
      vector<vector<int>> b;
      a.push_back(ain);
      b.push_back(bin);
      
      int answer = 0;
      for(int i = (1 << 30); i >= 1; i >>= 1){
         bool fail = false;
         vector<vector<int>> anew;
         vector<vector<int>> bnew;
         for(int k = 0; k < a.size(); k++){
            vector<int> a0;
            vector<int> a1;
            vector<int> b0;
            vector<int> b1;
            
            for(int j = 0; j < a[k].size(); j++){
               if((a[k][j] & i) > 0) a0.push_back(a[k][j]);
               else a1.push_back(a[k][j]);
               
               if((b[k][j] & i) > 0) b0.push_back(b[k][j]);
               else b1.push_back(b[k][j]);
            }
            
            if(a1.size() == b0.size() && a0.size() == b1.size()){
               if(a1.size() != 0){
                  anew.push_back(a1);
                  bnew.push_back(b0);
               }
               if(a0.size() != 0){
                  anew.push_back(a0);
                  bnew.push_back(b1);
               }
            } else {
               fail = true;
               break;
            }
         }
         
         if(fail) continue;
         
         answer |= i;
         
         a = move(anew);
         b = move(bnew);
      }
      
      cout << answer << "\n";
   }
   
   
   return 0;
}