#include <bits/stdc++.h>

using namespace std;


int n;
int m;
bool board[500][500];

int total;

bool has(int x, int y){
   return x >= 0 && x < n && y >= 0 && y < m && board[x][y];
}


int calc(int dx, int dy){
   int overlap = 0;
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         if(!board[k][j]) continue;
         bool a = has(k+dx,j+dy);
         bool b = has(k-dx,j-dy);
         
         if(a && b) overlap++;
         if(!a && !b) return -1;
      }
   }
   return (total+overlap)/2;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   total = 0;
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         board[k][j] = s[j] == '#';
         if(board[k][j]) total++;
      }
   }
   
   int answer = total;
   for(int dx = 0; dx < n; dx++){
      for(int dy = -m; dy < m; dy++){
         int i = calc(dx,dy);
         //cout << dx << " " << dy << " " << i << endl;
         if(i != -1){
            answer = min(answer,i);
         }
      }
   }
   
   cout << answer << endl;
   
   
   
   return 0;
}