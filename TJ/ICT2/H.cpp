#include <bits/stdc++.h>
using namespace std;

#define MAXN 305

int n,a,e;


struct hash_pair { 
    template <class T1, class T2> 
    size_t operator()(const pair<T1, T2>& p) const
    { 
        auto hash1 = hash<T1>{}(p.first); 
        auto hash2 = hash<T2>{}(p.second); 
        return hash1 ^ hash2; 
    } 
}; 

unordered_map<pair<int,int>,int,hash_pair> enemies;
unordered_map<pair<int,int>,int,hash_pair> allies;        // <x,y>,index




struct State{
   int x;
   int y;
   int level;
};

bool in(int x, int y){
   return x >= 0 && y >= 0 && x < n && y < n;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   cin >> n >> a >> e;
   
   for(int k = 0; k < a; k++){
      int x1,y1;
      cin >> x1 >> y1;
      
      pair<int,int> cur = make_pair(x1,y1);
      if(allies.find(cur) == allies.end()){
         allies[cur] = k;
      }
   }
   
   for(int k = 0; k < e; k++){
      int x1,y1;
      cin >> x1 >> y1;
      pair<int,int> cur = make_pair(x1,y1);
      if(allies.find(cur) == enemies.end()){
         enemies[cur] = k;
      }
   }
      
   int maxanswer = 605;
   int maxid = 305;
   
   for(int k = 0; k < n; k++) for(int j = 0; j < n; j++) if(allies.find(make_pair(k,j)) != allies.end()){
      auto aloc = make_pair(k,j);
      auto id = allies[make_pair(k,j)];
      
      queue<State> q;
      
      State init {aloc.first,aloc.second,0};
      q.push(init);
      unordered_set<pair<int,int>,hash_pair> seen;
      seen.insert(make_pair(aloc.first,aloc.second));
      
      while(!q.empty()){
         State cur = q.front();
         q.pop();
         
         int x = cur.x;
         int y = cur.y;
         
         if(cur.level > maxanswer) break;
         
         //cout << x << " " << y << endl;
         
         pair<int,int> locpair = make_pair(x,y);
         
         //cout << x << " " << y << " " << cur.level << 
         
         if(enemies.find(locpair) != enemies.end()){
            if(maxanswer > cur.level){
               maxanswer = cur.level;
               maxid = id;
               //cout << maxanswer << " " << maxid << " " << x << " " << x << " " << y << endl;
            } else if(maxanswer == cur.level){
               if(maxid > id){
                  maxid = id;
               }
            }
            break;
         }
         
         seen.insert(make_pair(x,y));
         
         if(in(x+1,y) && seen.find(make_pair(x+1,y)) == seen.end()){
            State adds {x+1,y,cur.level+1};
            q.push(adds);
         }
         if(in(x-1,y) && seen.find(make_pair(x-1,y)) == seen.end()){
            State adds {x-1,y,cur.level+1};
            q.push(adds);
         }
         if(in(x,y+1) && seen.find(make_pair(x,y+1)) == seen.end()){
            State adds {x,y+1,cur.level+1};
            q.push(adds);
         }
         if(in(x,y-1) && seen.find(make_pair(x,y-1)) == seen.end()){
            State adds {x,y-1,cur.level+1};
            q.push(adds);
         }
      }
      seen.clear();
   }
   
   cout << maxid << " " << maxanswer << endl;      
   
   return 0;
}