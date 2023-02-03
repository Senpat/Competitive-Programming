/*
TASK: camelot
LANG: C++
*/

#include <bits/stdc++.h>

#define INF 100000

using namespace std;

int n,m;

vector<int> dx = {2,1,-1,-2,-2,-1,1,2};
vector<int> dy = {1,2,2,1,-1,-2,-2,-1};

int dist[31][31][31][31];

bool in(int x, int y){
   return x >= 1 && x <= n && y >= 1 && y <= m;
}

int chtoi(char ch){
   return ch-'A'+1;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   ifstream fin ("camelot.in");
   ofstream fout ("camelot.out");
   
   string s;
   getline(fin,s);
   
   stringstream ss(s);
   ss >> n >> m;
   
   int kingx = -1;
   int kingy = -1;
   
   vector<pair<int,int>> knights;
   while(getline(fin,s)){
      ss = stringstream(s);
      
      string a;
      string b;
      while(getline(ss,a,' ')){
         getline(ss,b,' ');
         int c = chtoi(a[0]);
         int r = stoi(b);
         
         if(kingx == -1){
            kingx = r;
            kingy = c;
         } 
         else {
            knights.emplace_back(r,c);
         }
      }
      
   }
   
   //precompute distances
   for(int k = 1; k <= n; k++){
      for(int j = 1; j <= m; j++){
         for(int h = 1; h <= n; h++){
            for(int g = 1; g <= m; g++){
               dist[k][j][h][g] = INF;
            }
         }
      }
   }
      
   for(int sx = 1; sx <= n; sx++){
      for(int sy = 1; sy <= m; sy++){
         dist[sx][sy][sx][sy] = 0;
         queue<pair<int,int>> q;
         q.emplace(sx,sy);
            
         while(!q.empty()){
            auto p = q.front();
            q.pop();
               
            for(int d = 0; d < 8; d++){
               int newx = p.first+dx[d];
               int newy = p.second+dy[d];
               if(!in(newx,newy)) 
                  continue;
               if(dist[sx][sy][newx][newy] != INF) 
                  continue;
                  
               dist[sx][sy][newx][newy] = dist[sx][sy][p.first][p.second]+1;
               q.emplace(newx,newy);
            }
         }   
      }
   }
      
   
   int answer = INF;
   //pick ending square
   for(int ex = 1; ex <= n; ex++){
      for(int ey = 1; ey <= m; ey++){
         //initial answer
         int sum = 0;
         for(const auto& knight : knights){
            sum += dist[ex][ey][knight.first][knight.second];
         }
         if(sum >= answer) 
            continue;
         answer = min(answer, sum+max(abs(ex-kingx),abs(ey-kingy)));
            
         //pick which knight to meet king
         for(const auto& knight : knights){
            //pick where to meet knight
            for(int mx = 1; mx <= n; mx++){
               for(int my = 1; my <= m; my++){
                  int curanswer = sum-dist[ex][ey][knight.first][knight.second];
                  //king move to meeting square
                  curanswer += max(abs(kingx-mx),abs(kingy-my));
                   //knight move to meeting square, then move to ending square
                  curanswer += dist[knight.first][knight.second][mx][my] + dist[mx][my][ex][ey];
                  answer = min(answer,curanswer);
               }
            }
         }
      }
   }
   
   fout << answer << endl;
   
   
   return 0;
}