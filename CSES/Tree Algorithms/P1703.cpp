#include <bits/stdc++.h>
//somehow copied wordpress tutorial wrong, using ucf template instead
using namespace std;

const int MAXN = 100005;
//dominator tree - https://tanujkhattar.wordpress.com/2016/01/11/dominator-tree-of-a-directed-graph/
vector<int> adj[MAXN];        //input graph adjacency matrix
vector<int> radj[MAXN];       //reverse input graph adjacency matrix
vector<int> domadj[MAXN];     //dominator tree adjacency matrix
vector<int> bucket[MAXN];     //for every vertex, stores list of vertices for which that vertex is the sdom

int sdom[MAXN],par[MAXN],idom[MAXN],dsu[MAXN],label[MAXN];
int arr[MAXN],rev[MAXN],T;

void initdfs(int v){
   T++;
   arr[v] = T;
   rev[T] = v;
   label[T] = T, sdom[T] = T, idom[T] = T;
   for(int nei : adj[v]){
      if(arr[nei] == 0){
         initdfs(nei);
         par[arr[nei]] = arr[v];
      }
      radj[arr[nei]].push_back(arr[v]);
   }
}

int dsu_find(int v, int x=0){
   if(v == dsu[v]) return x ? -1 : v;
   int p = dsu_find(dsu[v],x+1);
   
   if(p < 0) return v;
   if(sdom[label[dsu[v]]] < sdom[label[v]]){
      label[v] = label[dsu[v]];
   }
   
   dsu[v] = p;
   return x ? p : label[v];
}

//add an edge a -> b
void dsu_union(int a, int b){
   dsu[b] = a;
}

int parent[MAXN];
void dfs(int v, int p){
   parent[v] = p;
   for(int nei : domadj[v]){
      if(nei == p) continue;
      dfs(nei,v);
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
   }
   
   initdfs(1);
   
   //compute sdoms and idoms
   for(int k = n; k >= 1; k--){
      for(int nei : radj[k]){
         sdom[k] = min(sdom[k],sdom[dsu_find(nei)]);
      }
      
      if(k > 1) bucket[sdom[k]].push_back(k);
      
      for(int i : bucket[k]){
         int v = dsu_find(i);
         if(sdom[v] == sdom[i]) idom[i] = sdom[i];
         else idom[i] = v;
      }
      
      if(k > 1) dsu_union(par[k],k);
   }
   
   for(int k = 2; k <= n; k++){
      if(idom[k] != sdom[k]) idom[k] = idom[idom[k]];
      domadj[rev[k]].push_back(rev[idom[k]]);
      domadj[rev[idom[k]]].push_back(rev[k]);
   }
   
   
   //use dominator tree
   dfs(1,-1);
   vector<int> answer;
   int i = n;
   while(i != 1){
      answer.push_back(i);
      i = parent[i];
   }
   answer.push_back(1);
   
   cout << answer.size() << endl;
   for(int k = answer.size()-1; k >= 0; k--){
      cout << answer[k] << " ";
   }
   
   
   
   
   return 0;
}