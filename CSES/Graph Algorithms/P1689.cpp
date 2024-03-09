#include <bits/stdc++.h>

using namespace std;

int N = 8;
int dx[] = {2,1,-1,-2,-2,-1,1,2};
int dy[] = {1,2,2,1,-1,-2,-2,-1};

vector<vector<int>> board;
vector<vector<int>> moves;

int pi;
int maxpi = 0;

bool success;


bool in(int x, int y){
   return x >= 0 && x < N && y >= 0 && y < N;
}

void editmoves(int x, int y, int dm){
   for(int d = 0; d < 8; d++){
      if(in(x+dx[d],y+dy[d])){
         moves[x+dx[d]][y+dy[d]] += dm;
      }
   }
}

void dfs(int x, int y){
   if(success) return;
   //cout << pi << endl;
   if(pi == N*N+1){
      success = true;
      return;
   }
   
   //go to move with fewest # of moves
   vector<int> ds;
   for(int d = 0; d < 8; d++){
      if(in(x+dx[d],y+dy[d]) && board[x+dx[d]][y+dy[d]] == 0){
         ds.push_back(d);
      }
   }
   
   sort(ds.begin(),ds.end(),[x,y](const int& a, const int&b){
      return moves[x+dx[a]][y+dy[a]] < moves[x+dx[b]][y+dy[b]];
   });
   
   for(int d : ds){
      int nx = x+dx[d];
      int ny = y+dy[d];
      
      board[nx][ny] = pi;
      pi++;
      editmoves(nx,ny,-1);
      dfs(nx,ny);
      if(success) return;
      editmoves(nx,ny,1);
      pi--;
      board[nx][ny] = 0;
   }
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int sx,sy;
   cin >> sy >> sx, sx--, sy--;
   
   board = vector<vector<int>>(N,vector<int>(N,0));
   moves = vector<vector<int>>(N,vector<int>(N,0));
   
   for(int x = 0; x < N; x++){
      for(int y = 0; y < N; y++){   
         for(int d = 0; d < 8; d++){
            if(in(x+dx[d],y+dy[d])) moves[x][y]++;     
         }
      }
   }
   
   pi = 2;
   board[sx][sy] = 1;
   editmoves(sx,sy,-1);
   success = false;
   dfs(sx,sy);
   
   for(int k = 0; k < N; k++){
      for(int j = 0; j < N; j++){
         cout << board[k][j] << " ";
      }
      cout << endl;
   }
   
   
   return 0;
}