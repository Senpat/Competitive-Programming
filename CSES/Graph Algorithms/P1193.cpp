#include <bits/stdc++.h>

using namespace std;

int n,m;
vector<vector<char>> board;
vector<vector<char>> parent;

bool in(int x, int y){
   return x < n && x >= 0 && y < m && y >= 0;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   board = vector<vector<char>>(n,vector<char>(m));
   
   int sx,sy,ex,ey;
   
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j];
         
         if(board[k][j] == 'A'){
            sx = k;
            sy = j;
         }
         if(board[k][j] == 'B'){
            ex = k;
            ey = j;
         }
      }
   }
   //cout << sx << " " << sy << endl;
   parent = vector<vector<char>>(n,vector<char>(m,'?'));
   parent[sx][sy] = 'A';
   
   queue<pair<int,int>> q;
   q.push(make_pair(sx,sy));
   
   while(!q.empty()){
      auto p = q.front();
      q.pop();
      int x = p.first;
      int y = p.second;
      
      if(in(x+1,y) && parent[x+1][y] == '?' && board[x+1][y] != '#'){
         parent[x+1][y] = 'D';
         q.push(make_pair(x+1,y));
      }
      if(in(x,y+1) && parent[x][y+1] == '?' && board[x][y+1] != '#'){
         parent[x][y+1] = 'R';
         q.push(make_pair(x,y+1));
      }
      if(in(x-1,y) && parent[x-1][y] == '?' && board[x-1][y] != '#'){
         parent[x-1][y] = 'U';
         q.push(make_pair(x-1,y));
      }
      if(in(x,y-1) && parent[x][y-1] == '?' && board[x][y-1] != '#'){
         parent[x][y-1] = 'L';
         q.push(make_pair(x,y-1));
      }
   }
   
   
   if(parent[ex][ey] == '?'){
      cout << "NO" << endl;
   } else {
      int x = ex;
      int y = ey;
      vector<char> array;
      while(parent[x][y] != 'A'){
         //cout << x << " " << y << parent[x][y] << endl;
         array.push_back(parent[x][y]);
         if(parent[x][y] == 'R'){
            y--;
         }
         else if(parent[x][y] == 'L'){
            y++;
         }
         else if(parent[x][y] == 'D'){
            x--;
         }
         else if(parent[x][y] == 'U'){
            x++;
         }
      }
      
      cout << "YES" << endl;
      cout << array.size() << endl;
      for(int k = array.size()-1; k >= 0; k--){
         cout << array[k];
      }
   }
   /*
   cout << endl;
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         cout << parent[k][j];
      }
      
      cout << endl;
   }*/
      
   
   return 0;
}