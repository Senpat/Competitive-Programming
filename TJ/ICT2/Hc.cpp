#include <bits/stdc++.h>
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

int n,e,a;

bool in(int x, int y){
   return x >= 0 && y >= 0 && x < n && y < n;
}


bool seen[MAXN][MAXN];
bool enemy[MAXN][MAXN];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> a >> e;
   
   queue<pair<pair<int,int>,pair<int,int>>> q;              //x - first.first, y - first.second, id - second.first, level - second.second
   
   
   for(int k = 0; k < a; k++){
      int x,y;
      cin >> x >> y;
      if(seen[x][y]) continue;
      q.push(make_pair(make_pair(x,y),make_pair(k,0)));
      seen[x][y] = true;
   }
   
   for(int k = 0; k < e; k++){
      int x,y;
      cin >> x >> y;
      enemy[x][y] = true;
   }
   
   
      
   while(!q.empty()){
      pair<pair<int,int>,pair<int,int>> p = q.front();
      q.pop();
      
      int x = p.first.first;
      int y = p.first.second;
      int id = p.second.first;
      int level = p.second.second;
      
      
      if(enemy[x][y]){
         cout << id << " " << level;
         return 0;
      }
      
      
      
      if(in(x+1,y) && !seen[x+1][y]){
         seen[x+1][y] = true;
         q.push(make_pair(make_pair(x+1,y),make_pair(id,level+1)));
      }
      if(in(x-1,y) && !seen[x-1][y]){
         seen[x-1][y] = true;
         q.push(make_pair(make_pair(x-1,y),make_pair(id,level+1)));
      }
      if(in(x,y+1) && !seen[x][y+1]){
         seen[x][y+1] = true;
         q.push(make_pair(make_pair(x,y+1),make_pair(id,level+1)));
      }
      if(in(x,y-1) && !seen[x][y-1]){
         seen[x][y-1] = true;
         q.push(make_pair(make_pair(x,y-1),make_pair(id,level+1)));
      }
      
      
      
   }
   
   
   
   
   
   
   
   return 0;
}