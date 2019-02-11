#include <bits/stdc++.h>

using namespace std;

#define MAXN 205

int board[MAXN][MAXN];
int n,m;
int count1 = 0;


   
bool in(int x, int y){
   return x >= 0 && y >= 0 && x < n && y < n;
}

void dfs(int x, int y, int i){
   if(board[x][y] == i){
      board[x][y] = 0;
      count1--;
      if(in(x+1,y)) dfs(x+1,y,i);
      if(in(x,y+1)) dfs(x,y+1,i);
      if(in(x,y-1)) dfs(x,y-1,i);
      if(in(x-1,y)) dfs(x-1,y,i);
   }
}
   

bool go(int x, int y){
   if(in(x+1,y) && board[x+1][y] == board[x][y]) 
      return true;
   if(in(x,y+1) && board[x][y+1] == board[x][y]) 
      return true;
   if(in(x,y-1) && board[x][y-1] == board[x][y]) 
      return true;
   if(in(x-1,y) && board[x-1][y] == board[x][y]) 
      return true;
   return false;
}

void printboard(int t){
   cout << t << endl;
   for(int k = 0; k < n; k++){
      for(int j = 0; j < n; j++){
         cout << board[k][j];
      }
      cout << endl;
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   cin >> n >>m;
   
   
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < n; j++){
         board[k][j] = s[j]-'0';
         if(board[k][j] != 0) count1++;
      }
   }
   
   if(count1 == 0){
      cout << "YES" << endl;
      return 0;
   }
   
   for(int t = 1; t <= m; t++){
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(board[k][j] != 0 && go(k,j)){
               dfs(k,j,board[k][j]);
            }
         }
      }
      if(count1 == 0){
         cout << "YES" << endl;
         return 0;
      }
      //printboard(t);
      //move
      for(int k = 0; k < n; k++){
         
         if(t % 2 == 0){
            for(int j = 0; j < n; j++){
               //go up
               int index = j;
               while(index > 0 && board[index-1][k] == 0){
                  board[index-1][k] = board[index][k];
                  board[index][k] = 0;
                  index--;
               }
            }
         } 
         else {
            for(int j = n-1; j >= 0; j--){
               //go down;
               int index = j;
               while(index < n-1 && board[index+1][k] == 0){
                  board[index+1][k] = board[index][k];
                  board[index][k] = 0;
                  index++;
               }
            }
            
         }
      }
                     
         
      
      //printboard(t);
   }
   
   cout << "NO" << endl;
   
   
   
   
   
   return 0;
}