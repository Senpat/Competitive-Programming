#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int N = 10;
   
   vector<vector<int>> board(N,vector<int>(N,0));
   
   for(int k = 0; k < N; k++){
      string s;
      cin >> s;
      for(int j = 0; j < N; j++){
         board[k][j] = s.at(j)-'0';
      }
   }
   
   vector<vector<bool>> stars(N,vector<bool>(N,false));
   
   vector<int> rows(N,0);
   vector<int> cols(N,0);
   vector<int> regions(N,0);
   
   for(int k = 0; k < N; k++){
      string s;
      cin >> s;
      for(int j = 0; j < N; j++){
         if(s.at(j) == '*'){
            rows[k]++;
            cols[j]++;
            regions[board[k][j]]++;
            stars[k][j] = true;  
         }
      }
   }
   
   bool fail = false;
   for(int k = 0; k < N; k++){
      if(rows[k] != 2 || cols[k] != 2 || regions[k] != 2) fail = true;
      
      for(int j = 0; j < N; j++){
         if(k < N-1 && stars[k][j] && stars[k+1][j]) fail = true;
         if(j < N-1 && stars[k][j] && stars[k][j+1]) fail = true;
         if(k < N-1 && j < N-1 && stars[k][j] && stars[k+1][j+1]) fail = true;
         if(k > 0 && j < N-1 && stars[k][j] && stars[k-1][j+1]) fail = true;
      }
      
   }
   
   
   if(fail){
      cout << "invalid\n";
   } else {
      cout << "valid\n";
   }
   
   
   return 0;
}