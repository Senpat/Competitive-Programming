#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m,r,c;
      cin >> n >> m >> r >> c;
      
      vector<vector<char>> board(n+1,vector<char>(n+1,'.'));
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            if((k+j)%m == (r+c)%m){
               board[k][j] = 'X';
            }  
         }
      }
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            cout << board[k][j];
         }
         cout << "\n";
      }
   }
   
   return 0;
}