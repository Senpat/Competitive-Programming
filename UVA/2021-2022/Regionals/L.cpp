#include <bits/stdc++.h>

using namespace std;

bool vis[755][755];
vector<string> grid;
int dx[8] = {1, 2, 1, 2, -1, -2, -1, -2};
int dy[8] = {2, 1, -2, -1, 2, 1, -2, -1};
int att[755][755];
int r, c;
void dfs(int x, int y){
   if(x < 0 || x >= r || y < 0 || y >= c || vis[x][y] || att[x][y])
      return;
   vis[x][y] = 1;
   if(grid[x][y] == 'K'){
      grid[x][y] = '.';
      for(int i = 0; i < 8; ++i){
         int xp = x + dx[i];
         int yp = y + dy[i];
         if(xp < 0 || xp >= r || yp < 0 || yp >= c)
            continue;
         --att[xp][yp];
         if(!att[xp][yp]){
            bool good = 0;
            bool good2 = 1;
            for(int i = 1; i < r; ++i){
               int nextx = xp + i;
               if(good2){
                  if(nextx < r && vis[nextx][y])
                     good = 1;
               }
               if(nextx < r && grid[nextx][y] == 'K')
                  good2 = 0;
            }
            good2 = 1;
            for(int i = 1; i < r; ++i){
               int nextx = xp - i;
               if(good2){
                  if(nextx >= 0 && vis[nextx][y])
                     good = 1;
               }
               if(nextx >= 0 && grid[nextx][y] == 'K')
                  good2 = 0;
            }
            good2 = 1;
            for(int i = 1; i < c; ++i){
               int nexty = yp + i;
               if(good2){
                  if(nexty < c && vis[x][nexty])
                     good = 1;
               }
               if(nexty < c && grid[nexty][y] == 'K')
                  good2 = 0;
            }
            good2 = 1;
            for(int i = 1; i < c; ++i){
               int nexty = yp - i;
               if(good2){
                  if(nexty >= 0 && vis[x][nexty])
                     good = 1;
               }
               if(nexty >= 0 && grid[x][nexty] == 'K')
                  good2 = 0;
            }
            if(good)
               dfs(xp, yp);
         }
      }
   }
   bool good = 1;
   for(int i = 1; i < r; ++i){
      int nextx = x + i;
      if(good){
         dfs(nextx, y);
      }
      if(nextx < r && grid[nextx][y] == 'K')
         good = 0;
   }
   good = 1;
   for(int i = 1; i < r; ++i){
      int nextx = x - i;
      if(good)
         dfs(nextx, y);
      if(nextx >= 0 && grid[nextx][y] == 'K')
         good = 0;
   }
  good = 1;
   for(int i = 1; i < c; ++i){
      int nexty = y + i;
      if(good){
         dfs(x, nexty);
      }
      if(nexty < c && grid[x][nexty] == 'K')
         good = 0;
   }
   good = 1;
   for(int i = 1; i < r; ++i){
      int nexty = y - i;
      if(good)
         dfs(x, nexty);
      if(nexty >= 0 && grid[x][nexty] == 'K')
         good = 0;
   }
}
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   cin >> r >> c;
   grid.resize(r);
   for(auto &S : grid) cin >> S;
   int startx = -1, starty = -1;
   int endx = -1, endy = -1;
   for(int i = 0; i < r; ++i){
      for(int j = 0; j < c; ++j){
         if(grid[i][j] == 'R'){
            startx = i;
            starty = j;
         }
         else if(grid[i][j] == 'T'){
            endx = i;
            endy = j;
         }
         else if(grid[i][j] == 'K'){
            for(int k = 0; k < 8; ++k){
               int xp = i + dx[k];
               int yp = j + dy[k];
               if(xp < 0 || xp >= r || yp < 0 || yp >= c)
                  continue;
               ++att[xp][yp];
            }
         }
      }
   }
   dfs(startx, starty);
   cout << (vis[endx][endy] ? "yes" : "no");
   return 0;
}
/*
6 6
.....T
..K.K.
K.K...
*/