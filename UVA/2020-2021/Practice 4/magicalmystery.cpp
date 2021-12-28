#include <bits/stdc++.h>

using namespace std;

int board[8][8];
int rowsum[8];
int colsum[8];

unordered_set<int> given;

bool solved;

int dx[8] = {2,1,-1,-2,-2,-1,1,2};;
int dy[8] = {1,2,2,1,-1,-2,-2,-1};;

bool in(int x, int y){
   return x >= 0 && x < 8 && y >= 0 && y < 8;
}

void dothing(int x, int y, int i){
   if(solved) 
      return;
   
   rowsum[x] += 65-i;
   colsum[y] += 65-i;
   board[x][y] = i;
   
   if(i == 64){
      bool fail = false;
      for(int k = 0; k < 8; k++){
         if(rowsum[k] != 260 || colsum[k] != 260){
            fail = true;
            break;
         }
      }
      if(!fail) solved = true;
   } 
   else {
      if(given.find(i+1) != given.end()){
         //find it
         for(int k = 0; k < 8; k++){
            if(in(x+dx[k],y+dy[k]) && board[x+dx[k]][y+dy[k]] == i+1 && rowsum[x+dx[k]] + 65-(i+1) <= 260 && colsum[y+dy[k]] + 65-(i+1) <= 260){
               dothing(x+dx[k],y+dy[k],i+1);
            }
         }
      } 
      else {
         for(int k = 0; k < 8; k++){
            if(in(x+dx[k],y+dy[k]) && board[x+dx[k]][y+dy[k]] == -1 && rowsum[x+dx[k]] + 65-(i+1) <= 260 && colsum[y+dy[k]] + 65-(i+1) <= 260){
               dothing(x+dx[k],y+dy[k],i+1);
            }
         }
      }
      
   }
   
   if(!solved){
      //undo
      rowsum[x] -= 65-i;
      colsum[y] -= 65-i;
      if(given.find(i) == given.end()) board[x][y] = -1;
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   //cout << "HI" << endl;
   int startx = -1;
   int starty = -1;
   
   for(int k = 0; k < 8; k++){
      for(int j = 0; j < 8; j++){
         cin >> board[k][j];
         if(board[k][j] != -1) given.insert(board[k][j]);
         if(board[k][j] == 1){
            startx = k;
            starty = j;
         }
      }
      rowsum[k] = 0;
      colsum[k] = 0;
      
   }
   
   solved = false;
   dothing(startx,starty,1);
   
   for(int k = 0; k < 8; k++){
      for(int j = 0; j < 8; j++){
         cout << board[k][j] << " ";
      }
      cout << endl;
   }
   
         
   
   
   
   return 0;
}