#include <bits/stdc++.h>

using namespace std;

char x = 'X';

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   string board[n];
   
   for(int k = 0; k < n; k++){
      cin >> board[k];
   }
   
   int count = 0;
   
   for(int k = 1; k < n-1; k++){
      for(int j = 1; j < n-1; j++){
         if(board[k][j] == x && board[k-1][j-1] == x && board[k-1][j+1] == x && board[k+1][j-1] == x && board[k+1][j+1] == x){
            count++;
         }
      }
   }
   
   cout << count;
   
   
   
   
   return 0;
}