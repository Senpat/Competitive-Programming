#include <bits/stdc++.h>

using namespace std;

struct state{
   int x;
   int y;
   int dir;
   
   bool operator<(const state& rhs) const {
      if(x == rhs.x && y == rhs.y) return dir < rhs.dir;
      if(x == rhs.x) return y < rhs.y;
      return x < rhs.x;
   }
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,c;
   cin >> n >> m >> c;
   int sx,sy,tx,ty;
   cin >> sx >> sy >> tx >> ty;
   
   vector<vector<vector<int>>> times(n+1,vector<vector<int>>(m+1,{3,1,2}));
   times[1][1] = {0,0,0};
   
   for(int k = 0; k < c; k++){
      int nx,ny,t,r,l;
      cin >> nx >> ny >> t >> r >> l;
      times[nx][ny] = {l,t,r};
   }
   
   auto in = [n,m](int x, int y){
      return x >= 1 && y >= 1 && x <= n && y <= m;
      };
   
   //dirs u-0 d-1 l-2 r-3
   vector<vector<vector<int>>> dist(n+1,vector<vector<int>>(m+1,vector<int>(4,INT_MAX)));
   
   //l s r for each initial dir
   vector<vector<int>> dirs = {{2,0,3},{3,1,2},{1,2,0},{0,3,1}};
   
   vector<int> dx = {-1,1,0,0};
   vector<int> dy = {0,0,-1,1};
   
   dist[1][1] = {0,0,0,0};
   dist[2][1][1] = 1;
   dist[1][2][3] = 1;
   
   priority_queue<pair<int,state>,vector<pair<int,state>>,greater<pair<int,state>>> pq;
   
   pq.push({1,{2,1,1}});
   pq.push({1,{1,2,3}});
   
   //first rep: go to sx,sy
   //second rep: go to tx,ty
   //third rep: go to 1,1
   for(int djik = 0; djik < 3; djik++){
      int rep = 0;
      int prevdist = 0;
      while(!pq.empty()){
         auto p = pq.top();
         pq.pop();
      
         int d = p.first;
         state s = p.second;
      
         if(d != dist[s.x][s.y][s.dir]) 
            continue;
      //transitions for each turn (l, s, r)
         for(int t = 0; t < 3; t++){
            int nd = dirs[s.dir][t];
            if(!in(s.x+dx[nd],s.y+dy[nd])) 
               continue;
            int newdist = d + times[s.x][s.y][t]+1;
            if(dist[s.x+dx[nd]][s.y+dy[nd]][nd] > newdist){
               dist[s.x+dx[nd]][s.y+dy[nd]][nd] = newdist;
               pq.push({newdist,{s.x+dx[nd],s.y+dy[nd],nd}});
            }
         }
      
      
      }
      if(djik == 0){
      /*
         for(int t = 0; t < 4; t++){
            cout << t << ":\n";
            for(int k = 1; k <= n; k++){
               for(int j = 1; j <= m; j++){
                  cout << dist[k][j][t] << " ";
               }
               cout << endl;
            }
            cout << endl;
         }
      */
         for(int k = 1; k <= n; k++){
            for(int j = 1; j <= m; j++){
               if(k == sx && j == sy) 
                  continue;
               for(int h = 0; h < 4; h++){
                  dist[k][j][h] = INT_MAX;
               }
            }
         }
         //pq is empty
         for(int sd = 0; sd < 4; sd++){
            if(dist[sx][sy][sd] == INT_MAX) 
               continue;
            //cout << sd << ": " << dist[sx][sy][sd] << endl;
            pq.push({dist[sx][sy][sd],{sx,sy,sd}});
         }
      } 
      else if(djik == 1){
         for(int k = 1; k <= n; k++){
            for(int j = 1; j <= m; j++){
               if(k == tx && j == ty) 
                  continue;
               for(int h = 0; h < 4; h++){
                  dist[k][j][h] = INT_MAX;
               }
            }
         }
         for(int sd = 0; sd < 4; sd++){
            if(dist[tx][ty][sd] == INT_MAX) 
               continue;
            pq.push({dist[tx][ty][sd],{tx,ty,sd}});
         }
      }
   }
   
   int answer = INT_MAX;
   for(int k = 0; k < 4; k++){
      answer = min(answer,dist[1][1][k]);
   }
   cout << answer << endl;
   
   return 0;
}