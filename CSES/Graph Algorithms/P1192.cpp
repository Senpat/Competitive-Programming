#include <bits/stdc++.h>

using namespace std;


int n,m;
vector<vector<bool>> seen;
vector<vector<char>> board;

   
bool in(int x, int y){
   return x < n && x >= 0 && y < m && y >= 0;
}   

void ff(int x, int y){
   seen[x][y] = true;
      
   if(in(x+1,y) && !seen[x+1][y] && board[x+1][y] == '.'){
      ff(x+1,y);
   }
   if(in(x,y+1) && !seen[x][y+1] && board[x][y+1] == '.'){
      ff(x,y+1);
   }
   if(in(x-1,y) && !seen[x-1][y] && board[x-1][y] == '.'){
      ff(x-1,y);
   }
   if(in(x,y-1) && !seen[x][y-1] && board[x][y-1] == '.'){
      ff(x,y-1);
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   board = vector<vector<char>>(n,vector<char>(m));
   seen = vector<vector<bool>>(n,vector<bool>(m,false));
   
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j];
      }
   }
   
   int answer = 0;
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         if(seen[k][j] || board[k][j] == '#') continue;
         answer++;
         ff(k,j);
      }
   }
   
   cout << answer << endl;
      
   
   
   return 0;
}