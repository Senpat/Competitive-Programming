#include <bits/stdc++.h>

using namespace std;

#define N 754


char board[N][N];
int sx,sy,tx,ty;
int n,m;
int numattack[N][N];

int kdx[8] = {1,2,2,1,-1,-2,-2,-1};
int kdy[8] = {2,1,-1,-2,-2,-1,1,2};

bool seen[N][N];


bool in(int x, int y){
   return x >= 0 && x < n && y >= 0 && y < m;
}

bool reachable(int x, int y){
        
      //up
   int yi = y+1;
   while(yi < m){
      if(board[x][yi] == 'K') 
         break;
      if(seen[x][yi]) 
         return true;
      yi++;
   }
      
      //down
   yi = y-1;
   while(yi >= 0){
      if(board[x][yi] == 'K') 
         break;
      if(seen[x][yi]) 
         return true;
      yi--;
   }
      
      //right
   int xi = x+1;
   while(xi < n){
      if(board[xi][y] == 'K') 
         break;
      if(seen[xi][y]) 
         return true;
      xi++;
   }
      
      //left
   xi = x-1;
   while(xi >= 0){
      if(board[xi][y] == 'K') 
         break;
      if(seen[xi][y]) 
         return true;
      xi--;
   }
      
   return false;
}

void dfs(int x, int y);

   //returns false if you should break (if you hit a knight)
bool process(int x, int y){
   if(seen[x][y]) 
      return true;
      
   if(board[x][y] == 'K'){
      if(numattack[x][y] > 0) 
         return false;
         
      board[x][y] = '.';
         
      for(int k = 0; k < 8; k++){
         if(in(x+kdx[k],y+kdy[k])){
            numattack[x+kdx[k]][y+kdy[k]]--;
            if(numattack[x+kdx[k]][y+kdy[k]] == 0){
                  //go in all directions to see if there is a seen node
               if(!seen[x+kdx[k]][y+kdy[k]] && reachable(x+kdx[k],y+kdy[k])){
                  seen[x+kdx[k]][y+kdy[k]] = true;
                  dfs(x+kdx[k],y+kdy[k]);
               }
            }
         }
      }
   }
      
   if(numattack[x][y] == 0){
      if(!seen[x][y]){
         seen[x][y] = true;
         dfs(x,y);
      }
   }
      
   return true;
}

void dfs(int x, int y){
      //System.out.println(x + " " + y);
      //up
   int yi = y+1;
   while(yi < m){
      if(!process(x,yi)) 
         break;
      yi++;
   }
      
      //down
   yi = y-1;
   while(yi >= 0){
      if(!process(x,yi)) 
         break;
      yi--;
   }
      
      //right
   int xi = x+1;
   while(xi < n){
      if(!process(xi,y)) 
         break;
      xi++;
   }
      
      //left
   xi = x-1;
   while(xi >= 0){
      if(!process(xi,y)) 
         break;
      xi--;
   }
   
   
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j];
         
         if(board[k][j] == 'R'){
            board[k][j] = '.';
            sx = k;
            sy = j;
         }
         if(board[k][j] == 'T'){
            board[k][j] = '.';
            tx = k;
            ty = j;
         }
         if(board[k][j] == 'K'){
            for(int h = 0; h < 8; h++){
               if(in(k+kdx[h],j+kdy[h])) numattack[k+kdx[h]][j+kdy[h]] += 1;
            }
         }
            
      }
   }
   
   seen[sx][sy] = true;
   dfs(sx,sy);
   
   if(seen[tx][ty]){
      cout << "yes" << endl;
   } 
   else {
      cout << "no" << endl;
   }

   
   
   
   
   return 0;
}