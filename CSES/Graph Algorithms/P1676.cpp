#include <bits/stdc++.h>

using namespace std;

vector<int> parent;
vector<int> compsize;

int query(int x){
   if(parent[x] == x) return x;
   parent[x] = query(parent[x]);
   return parent[x];
}

void dsu(int x, int y){
   int px = query(x);
   int py = query(y);
   int sx = compsize[px];
   int sy = compsize[py];
   if(px != py){
      if(sx < sy){
         parent[px] = py;
         compsize[py] += compsize[px];
      } else {
         parent[py] = px;
         compsize[px] += compsize[py];
      }
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   parent = vector<int>(n+1);
   compsize = vector<int>(n+1,1);
   multiset<int> mset;
   
   for(int k = 1; k <= n; k++){
      parent[k] = k;
      mset.insert(1);
   }

   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      int pa = query(a);
      int pb = query(b);
      if(pa != pb){
         mset.erase(mset.find(compsize[pa]));
         mset.erase(mset.find(compsize[pb]));
         
         dsu(a,b);
         
         mset.insert(compsize[query(a)]);
      }
      
      cout << mset.size() << " " << *(mset.rbegin()) << endl;
   }
   
   
   
   return 0;
}