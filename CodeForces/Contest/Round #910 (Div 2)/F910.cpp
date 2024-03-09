#include <bits/stdc++.h>

using namespace std;

struct state{
   int x;
   int y;
   int d;
   
   //end coordinates that you started from
   int ex;
   int ey;
};

int n,m;
int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};

vector<vector<char>> board;

bool isedge(int x, int y){
   return x == 0 || x == n-1 || y == 0 || y == m-1;
}

bool in(int x, int y){
   return x >= 0 && x < n && y >= 0 && y < m;
}
/*
string ts(const pair<int,int>& p){
   return "" + to_string(p.first) + " " + to_string(p.second);
}*/


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int qq = 0; qq < t; qq++){
      cin >> n >> m;
      
      board = vector<vector<char>>(n,vector<char>(m));
      int sx = -1;
      int sy = -1;
      int numob = 0;
      
      vector<pair<int,int>> starts;
      for(int k = 0; k < n; k++){
         string s;
         cin >> s;
         for(int j = 0; j < m; j++){
            board[k][j] = s[j];
            if(board[k][j] == 'V'){
               sx = k;
               sy = j;
            }
            if(board[k][j] == '#'){
               numob++;
            }
            
            if(isedge(k,j) && board[k][j] != '#'){
               starts.push_back({k,j});
            }
         }
      }
      
      //bfs from v
      vector<vector<int>> vdist(n,vector<int>(m,INT_MAX));
      queue<state> q;
      q.push({sx,sy,1,-1,-1});
      //cout << sx << " " << sy << endl;
      vdist[sx][sy] = 1;
      
      int numend = 0;
      int mind = INT_MAX;
      int mind2 = INT_MAX;
      while(!q.empty()){
         auto s = q.front();
         q.pop();
         //cout << s.x << " " << s.y << endl;
         if(isedge(s.x,s.y)){
            numend++;
            if(s.d < mind){
               mind = s.d;
            } else if(s.d < mind2){
               mind2 = s.d;
            }
         }
         
         for(int d = 0; d < 4; d++){
            int nx = s.x + dx[d];
            int ny = s.y + dy[d];
            if(in(nx,ny) && board[nx][ny] != '#' && vdist[nx][ny] == INT_MAX){
               vdist[nx][ny] = s.d+1;
               q.push({nx,ny,s.d+1,-1,-1});
            }
         }
         
         
      }
      
      if(numend == 0){
         cout << n*m-1 - numob << endl;
         continue;
      }
      if(numend == 1){
         cout << n*m-mind - numob << endl;
         continue;
      }
      
      //multisource bfs to get min and 2nd min path for each node
      vector<vector<vector<int>>> edist = vector<vector<vector<int>>>(n,vector<vector<int>>(m,vector<int>(2,INT_MAX)));
      vector<vector<pair<int,int>>> start = vector<vector<pair<int,int>>>(n,vector<pair<int,int>>(m,{-1,-1}));
      q = queue<state>();
      for(auto [px,py] : starts){
         //cout << "starts: " << px << " " << py << endl;
         edist[px][py][0] = 0;
         start[px][py] = {px,py};
         q.push({px,py,0,px,py});
      }
      
      while(!q.empty()){
         auto s = q.front();
         q.pop();
         //cout << s.x << " " << s.y << endl;
         
         for(int d = 0; d < 4; d++){
            int nx = s.x + dx[d];
            int ny = s.y + dy[d];
            
            if(in(nx,ny) && board[nx][ny] != '#'){
               //cout << s.x << " " << s.y << " " << ts(start[s.x][s.y]) << " " << ts(start[nx][ny]) << endl;
               if(edist[nx][ny][0] == INT_MAX){
                  edist[nx][ny][0] = s.d+1;
                  start[nx][ny] = {s.ex,s.ey};
                  q.push({nx,ny,s.d+1,s.ex,s.ey});
               } else if(edist[nx][ny][1] == INT_MAX && make_pair(s.ex,s.ey) != start[nx][ny]){
                  
                  edist[nx][ny][1] = s.d+1;
                  q.push({nx,ny,s.d+1,s.ex,s.ey});
               }
            }
         }
      }
      
      int min2path = mind+mind2-1;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(vdist[k][j] != INT_MAX && edist[k][j][1] != INT_MAX){
               min2path = min(min2path,vdist[k][j] + edist[k][j][0] + edist[k][j][1]);
               //cout << k << " " << j << " " << vdist[k][j] << " " << edist[k][j][0] << " " << edist[k][j][1] << endl;
            }  
         }
      }
      
      /*
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            cout << start[k][j].first << " " << start[k][j].second << ", ";
         }
         cout << endl;
      }*/
         
      //cout << min2path << endl;
      cout << n*m-min2path-numob << endl;
   }
   
   
   return 0;
}