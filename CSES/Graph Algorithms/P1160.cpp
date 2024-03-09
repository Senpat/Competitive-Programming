#include <bits/stdc++.h>
//BUG: make sure that self loops are handled correctly
using namespace std;

#define D 18

vector<int> adj;

vector<int> cyclesize;        //cyclesize[i] stores size of ith cycle
vector<int> cycleindexof;
vector<int> cycle;            //cycle[i] stores which cycle node i is in, or -1 if it isn't in any
vector<int> cycledist;        //distance from nearest cycle
vector<int> cyclehead;        //closest element in a cycle

vector<bool> curpath;
vector<bool> seen;

bool incycle;
int stopcycle;
int cyclenum;
int curcyclesize;

void dfs(int v){
   seen[v] = true;
   curpath[v] = true;
   
   if(curpath[adj[v]]){
      //found cycle
      stopcycle = adj[v];
      cycle[v] = cyclenum;
      curcyclesize++;
      
      if(v == adj[v]){
         cyclenum++;
         cyclesize.push_back(curcyclesize);
      } else {
         incycle = true;
      }
   } 
   else if(!seen[adj[v]]){
      dfs(adj[v]);
      
      if(incycle){
         cycle[v] = cyclenum;
         curcyclesize++;
         
         if(v == stopcycle){
            incycle = false;
            cyclesize.push_back(curcyclesize);
            cyclenum++;
         }
      }
   }
   
   curpath[v] = false;
}

void dfs2(int v){
   if(seen[v]) 
      return;
   seen[v] = true;
   
   if(cycle[adj[v]] != -1){
      cycledist[v] = 1;
      cyclehead[v] = adj[v];
   } 
   else {
      dfs2(adj[v]);
      
      cycledist[v] = cycledist[adj[v]]+1;
      cyclehead[v] = cyclehead[adj[v]];
   }
}

int get_cycle_dist(int from, int to){
   if(cycleindexof[to] >= cycleindexof[from]){
      return cycleindexof[to]-cycleindexof[from];
   } 
   else {
      return cyclesize[cycle[from]] - (cycleindexof[from]-cycleindexof[to]);
   }
}

int jump[200005][D];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   adj = vector<int>(n+1);
   for(int k = 1; k <= n; k++){
      cin >> adj[k];
   }
   
   cyclesize = vector<int>();
   cycleindexof = vector<int>(n+1,-1);
   cycle = vector<int>(n+1,-1);
   cycledist = vector<int>(n+1,-1);
   cyclehead = vector<int>(n+1,-1);
   
   seen = vector<bool>(n+1,false);
   curpath = vector<bool>(n+1,false);
   
   cyclenum = 0;
   for(int k = 1; k <= n; k++){
      if(!seen[k]){
         curcyclesize = 0;
         stopcycle = -1;
         incycle = false;
         
         dfs(k);
      }
   }
   
   //calculate cycleindexof, cycledist, and cyclehead
   seen = vector<bool>(n+1,false);
   for(int k = 1; k <= n; k++){
      if(seen[k]) 
         continue;
      if(cycle[k] == -1){
         dfs2(k);
      } 
      else {
         cycleindexof[k] = 0;
         int i = 1;
         int v = adj[k];
         seen[k] = true;
         while(v != k){
            seen[v] = true;
            cycleindexof[v] = i;
            i++;
            v = adj[v];
         }
      }
   }
   
   for(int k = 1; k <= n; k++){
      jump[k][0] = adj[k];
   }
   
   for(int d = 1; d < D; d++){
      for(int k = 1; k <= n; k++){
         jump[k][d] = jump[jump[k][d-1]][d-1];
      }
   }
   
   /*
   for(int k = 1; k <= n; k++){
      cout << cycle[k] << " " << cycledist[k] << " " << cyclehead[k] << endl;
   }*/
      
   for(int t = 0; t < q; t++){
      int from,to;
      cin >> from >> to;
      
      if(from == to){
         cout << "0\n";
         continue;
      }
      
      if(cycle[from] == -1 && cycle[to] == -1){
         //non cycle to non cycle, must be in the same "tree"
         if(cyclehead[from] != cyclehead[to] || cycledist[from] <= cycledist[to]){
            cout << "-1\n";
         } else {
            int v = from;
            int i = cycledist[to];
            
            for(int d = D-1; d >= 0; d--){
               if(i + (1 << d) <= cycledist[from]){
                  i += (1 << d);
                  v = jump[v][d];
               }
            }
            
            if(v == to){
               cout << cycledist[from] - cycledist[to]<< endl;
            } else {
               cout << "-1\n";
            }
         }
      } 
      else if(cycle[from] != -1 && cycle[to] == -1){
         //cycle to non cycle (impossible)
         cout << "-1\n";
      } 
      else if(cycle[from] == -1 && cycle[to] != -1){
         if(cycle[cyclehead[from]] == cycle[to]){
            cout << cycledist[from] + get_cycle_dist(cyclehead[from],to) << endl;
         } 
         else {
            cout << "-1\n";
         }
      } 
      else {
         //cycle to cycle
         if(cycle[from] == cycle[to]){
            cout << get_cycle_dist(from,to) << endl;  
         }
         else {
            cout << "-1\n";
         }
      }
   }
   
   
   return 0;
}