#include <bits/stdc++.h>
//debug: n instead of m when looping through edges
using namespace std;

vector<int> parent;

int getp(int v){
   if(parent[v] == v) return v;
   parent[v] = getp(parent[v]);
   return parent[v];
}

void join(int u, int v){
   int pu = getp(u);
   int pv = getp(v);
   parent[pv] = pu;
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,q;
   cin >> n >> m >> q;
   
   vector<pair<int,int>> pairs;
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      if(a > b) swap(a,b);
      pairs.push_back({a,b});
   }
   
   vector<pair<int,int>> qp;
   set<pair<int,int>> mp;
   for(int k = 0; k < q; k++){
      int a,b;
      cin >> a >> b;
      if(a > b) swap(a,b);
      qp.push_back({a,b});
      mp.insert({a,b});
   }
   
   parent = vector<int>(n+1);
   for(int k = 1; k <= n; k++) parent[k] = k;
   
   int comps = n;
   vector<int> answer(q);
   
   for(int k = 0; k < m; k++){
      if(mp.find(pairs[k]) == mp.end()){
         if(getp(pairs[k].first) != getp(pairs[k].second)){
            comps--;
            join(pairs[k].first,pairs[k].second);
         }
      }
   }
   
   answer[q-1] = comps;
   for(int k = q-1; k >= 1; k--){
      if(getp(qp[k].first) != getp(qp[k].second)){
         comps--;
         join(qp[k].first,qp[k].second);
      }
      answer[k-1] = comps;
   }
   
   for(int k = 0; k < q; k++){
      cout << answer[k] << " ";
   }
   
   
   return 0;
}