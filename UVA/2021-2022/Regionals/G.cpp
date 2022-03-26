#include <bits/stdc++.h>

using namespace std;

struct node{
   int x, y;
   long double d;
};
struct cmp{
   bool operator()(const node &a, const node &b) const{
      return a.d > b.d;
   }
};
bool vis[51][51];
long double adj[51][51][51][51];
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   int r, c; cin >> r >> c;
   vector<string> grid(r);
   for(auto &S : grid)
      cin >> S;
   double INF = 1e9;
   vector<vector<long double>> dist(r + 1, vector<long double>(c + 1, INF));
   
   for(int x1 = 0; x1 <= r; ++x1){
      for(int y1 = 0; y1 <= c; ++y1){
         for(int x2 = 0; x2 <= r; ++x2){
            for(int y2 = 0; y2 <= c; ++y2)
               adj[x1][y1][x2][y2] = INF;
         }
      }
   }
   for(int x1 = 0; x1 <= r; ++x1){
      for(int y1 = 0; y1 <= c; ++y1){
         for(int x2 = 0; x2 <= r; ++x2){
            for(int y2 = 0; y2 <= c; ++y2){
               if(x1 == x2){
                  bool good = 1;
                  for(int i = min(y1, y2); i < max(y1, y2); ++i){
                     bool hascar = 0;
                     if(x1 && grid[x1-1][i] == '#')
                        hascar = 1;
                     if(x1 < r && grid[x1][i] == '#')
                        hascar = 1;
                     if(!hascar){
                        good = 0;
                        break;
                     }
                  }
                  if(good)
                     adj[x1][y1][x2][y2] = min(adj[x1][y1][x2][y2], (long double)max(y1, y2) - min(y1, y2));
               }
               else if(y1 == y2){
                  bool good = 1;
                  for(int i = min(x1, x2); i < max(x1, x2); ++i){
                     bool hascar = 0;
                     if(y1 && grid[i][y1-1] == '#')
                        hascar = 1;
                     if(y1 < c && grid[i][y1] == '#')
                        hascar = 1;
                     if(!hascar){
                        good = 0;
                        break;
                     }
                  }
                  if(good)
                     adj[x1][y1][x2][y2] = min(adj[x1][y1][x2][y2], (long double)max(x1, x2) - min(x1, x2));
               }
               else{
                  bool good = 1;
                  long double d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                  d = sqrt(d);
                  int xp1 = x1, yp1 = y1, xp2 = x2, yp2 = y2;
                  if(x1 > x2){
                     swap(xp1, xp2);
                     swap(yp1, yp2);
                  }
                  int dx = xp2 - xp1;
                  for(int i = xp1 + 1; i <= xp2; ++i){
                     int l = (i - xp1) * (yp2 - yp1);
                     int rt = (i - xp1 - 1) * (yp2 - yp1);
                     if(l > rt)
                        swap(l, rt);
                     l = yp1 + l / dx;
                     rt = yp1 + (rt + dx - 1) / dx;
                     for(int j = l; j < rt; ++j){
                        if(grid[i-1][j] == '#')
                           good = 0;
                     }
                  }
                  if(good)
                     adj[x1][y1][x2][y2] = min(adj[x1][y1][x2][y2], d);
               }
            }
         }
      }
   }
   priority_queue<node, vector<node>, cmp> pq;
   
   dist[0][0] = 0.0;
   pq.push({0, 0, 0.0});
   while(!pq.empty()){
      auto t = pq.top();
      pq.pop();
      if(vis[t.x][t.y])
         continue;
      vis[t.x][t.y] = 1;
      for(int i = 0; i <= r; ++i){
         for(int j = 0; j <= c; ++j){
            long double d = adj[t.x][t.y][i][j];
            if(d + t.d < dist[i][j]){
               pq.push({i, j, d + t.d});
               dist[i][j] = d + t.d;
            }
         }
      }
   }
   cout << fixed << setprecision(30);
   cout << min(dist[r][c], (long double)r + c);
   return 0;
}