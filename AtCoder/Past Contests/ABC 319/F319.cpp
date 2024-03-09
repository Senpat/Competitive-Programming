#include <bits/stdc++.h>
//fail - wrong complexity
using namespace std;

struct Enemy{
   int t;
   long long s;
   long long g;
};

vector<Enemy> enemies;
vector<vector<int>> adj;

vector<int> t2;
vector<int> perm;



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   adj = vector<vector<int>>(n+1,vector<int>());
   enemies = vector<Enemy>(n+1);
   t2 = vector<int>;
   perm = vector<int>;
   
   for(int k = 2; k <= n; k++){
      int p,t;
      long long s,g;
      
      adj[p].push_back(k);
      adj[k].push_back(p);
      
      enemies[k] = {t,s,g};
      
      if(t == 2){
         perm.push_back(perm.size());
         t2.push_back(k);
      }
   }
   
   bool found = false;
   auto compare_enemies = [](const Enemy& e1, const Enemy& e2){
      //sort by s
      return e1.s < e2.s;
   };
   
   do{
      vector<bool> seen(n+1,false);
      vector<bool> defeated(n+1,false);
      priority_queue<Enemy,vector<Enemy>,compare_enemies> pq;
      
      unordered_set<int> t2reached;
      
      queue<int> q;
      seen[1] = true;
      q.push(1);
      
      int ti = 0;
      long long strength = 1LL;
      while(!q.isEmpty()){
         int v = q.front();
         q.pop();
         
         bool expand = false;
         for(int nei : adj[v]){
            if(!seen[nei] && enemies[nei].t == 1
         }
      }
      
      
   } while(next_permutation(perm.begin(),perm.end()));
   
   
   
   return 0;
}