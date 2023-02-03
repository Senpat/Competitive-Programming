#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n,m;
   cin >> n >> m;
   
   vector<vector<char>> board(n,vector<char>(m));
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s.at(j);
      }
   }
   
   vector<vector<char>> answer(n,vector<char>(m,'.'));
   
   
   for(int c = 0; c < m; c++){
      vector<int> anum;
      int curas = 0;
      
      for(int r = n-1; r >= 0; r--){
         if(board[r][c] == '#'){
            anum.push_back(curas);
            curas = 0;
         } 
         else if(board[r][c] == 'a'){
            curas++;
         }
      }  
      anum.push_back(curas);
      
      int i = 0;
      curas = anum[i];
      for(int r = n-1; r >= 0; r--){
         if(board[r][c] == '#'){
            answer[r][c] = '#';
            i++;
            curas = anum[i];
         } 
         else if(curas > 0){
            answer[r][c] = 'a';
            curas--;
         }
            
      }
   }
   
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         cout << answer[k][j];
      }
      cout << "\n";
   }
   
   
   return 0;
}