#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int a;
   int b;
   long long w;
};

vector<int> parent;

int query(int x){
   if(parent[x] == x) return x;
   parent[x] = query(parent[x]);
   return parent[x];
}

void dsu(int x, int y){
   int xp = query(x);
   int yp = query(y);
   if(xp != yp){
      parent[xp] = yp;
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<Edge> edges(m);
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      edges[k] = {a,b,w};
   }
   
   sort(edges.begin(),edges.end(),[](const auto& lhs, const auto& rhs){
      return lhs.w < rhs.w;
   });
   
   long long answer = 0LL;
   
   parent = vector<int>(n+1);
   for(int k = 1; k <= n; k++) parent[k] = k;
   
   int numadded = 0;
   for(int k = 0; k < m; k++){
      if(query(edges[k].a) != query(edges[k].b)){
         answer += edges[k].w;
         numadded++;
         dsu(edges[k].a,edges[k].b);
      }
   }
   
   if(numadded != n-1){
      cout << "IMPOSSIBLE\n";
   } else {
      cout << answer << endl;
   }
   
   
   
   return 0;
}