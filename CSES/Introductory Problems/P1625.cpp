#include <bits/stdc++.h>

using namespace std;


string path;
int answer;
bool board[7][7];

bool in(int x, int y){
   return x >= 0 && x < 7 && y >= 0 && y < 7;
}

bool cango(int x, int y){
   return in(x,y) && !board[x][y];
}

void dothing(int x, int y, int index){
   if(index == 48){
      if(x == 6 && y == 0){
         answer++;
      }
      return;
   }
      
   if(x == 6 && y == 0) 
      return;
   
   board[x][y] = true;
   if(path[index] == 'R' || path[index] == '?'){
      if(in(x,y+1) && !board[x][y+1]){
         if(!(!cango(x,y+2) && cango(x+1,y+1) && cango(x-1,y+1))){
            dothing(x,y+1,index+1);
         }
      }
   }
      
   if(path[index] == 'L' || path[index] == '?'){
      if(in(x,y-1) && !board[x][y-1]){
         if(!(!cango(x,y-2) && cango(x+1,y-1) && cango(x-1,y-1))){
            dothing(x,y-1,index+1);
         }
      }
   }
      
   if(path[index] == 'U' || path[index] == '?'){
      if(in(x-1,y) && !board[x-1][y]){
         if(!(!cango(x-2,y) && cango(x-1,y-1) && cango(x-1,y+1))){
            dothing(x-1,y,index+1);
         }
      }
   }
      
   if(path[index] == 'D' || path[index] == '?'){
      if(in(x+1,y) && !board[x+1][y]){
         if(!(!cango(x+2,y) && cango(x+1,y-1) && cango(x+1,y+1))){
            dothing(x+1,y,index+1);
         }
      }
   }
   board[x][y] = false;
      
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> path;
   
   answer = 0;
   for(int k = 0; k < 7; k++){
      for(int j = 0; j < 7; j++){
         board[k][j] = false;
      }
   }
     
   board[0][0] = true;
   dothing(0,0,0);
   
   cout << answer << endl;
   
   
   return 0;
}