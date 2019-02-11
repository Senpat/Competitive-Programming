#include <bits/stdc++.h>
//also bad
using namespace std;

#define MAXN 305
#define INF 605

struct hash_pair { 
    template <class T1, class T2> 
    size_t operator()(const pair<T1, T2>& p) const
    { 
        auto hash1 = hash<T1>{}(p.first); 
        auto hash2 = hash<T2>{}(p.second); 
        return hash1 ^ hash2; 
    } 
}; 




int ab[MAXN][MAXN];
int eb[MAXN][MAXN];

int ad[MAXN][MAXN];
int ed[MAXN][MAXN];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> a >> e;
   
   for(int k = 0; k < MAXN; k++){
      for(int j = 0; j < MAXN; j++){
         ab[k][j] = -1;
         eb[k][j] = -1;
      }
   }
   
   for(int k = 0; k < a; k++){
      int x,y;
      cin >> x >> y;
      if(ab[x][y] == -1){
         ab[x][y] = k;
      }
   }
   
   for(int k = 0; k < e; k++){
      int x,y;
      cin >> x >> y;
      if(eb[x][y] == -1){
         eb[x][y] = k;
      }
   }
   
   
   ad[0][0] = INF;
   ed[0][0] = INF;
   
   queue<pair<pair<int,int>,int>> q;
   
   q.push(make_pair(0,0));
   
   int mindis = INF;
   int minid = INF;
   
   unordered_set<pair<int,int>,hash_pair> seen;
   
   while(!q.empty()){
      pair<int,int> p = q.front();
      q.pop();
      
      int x = p.first.first;
      int y = p.first.second;
      
      
      if(seen.find(p.first) == seen.end()){
         continue;
      }
      seen.insert(make_pair(x,y));
      
      if(eb[x][y] != -1){
         value = 0;
      }
      
      if(value < INF){
         if(value < dl[x][y]){
            dl[x][y] = value;
         }
      }
      
      if(ab[x][y] != -1 && value < INF){
         if(mindis > value){
            mindis = value;
            minid = ab[x][y];
         } else if(mindis == value){
            minid = min(minid,ab[x][y]);
         }
      }
      
      if(in(x+1,y)){
         dl[x+1][y] = min(dl[x+1][y],dl[x][y] + 1);
         q.push(make_pair(x+1,y));
      }
      if(in(x,y+1)){
         dl[x][y+1] = min(dl[x][y+1],dl[x][y] + 1);
         q.push(make_pair(x,y+1));
      }
   }
   
   seen.clear();
   
   
   
   
   
   return 0;
}