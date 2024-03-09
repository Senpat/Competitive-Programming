#include <bits/stdc++.h>

using namespace std;

vector<unordered_set<int>> adj;
vector<int> path;

void euler(int v){
   
   while(adj[v].size() > 0){
      auto i = adj[v].begin();
      int nei = *i;
      adj[v].erase(i);
      
      euler(nei);
   }
   
   path.push_back(v);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   if(n == 1){
      cout << "01\n";
      return 0;
   }
   
   //1 << (n-1) total nodes
   int vn = 1 << (n-1);
   
   adj = vector<unordered_set<int>>(vn,unordered_set<int>());
   
   for(int k = 0; k < (1 << n); k++){
      int from = (k >> 1);
      int to = (k & (vn-1));
      
      adj[from].insert(to);
   }
   
   //find euler tour
   path = vector<int>();
   
   euler(0);
   
   stringstream ss;
   for(int i : path) ss << (i&1);
   for(int k = 0; k < n-2; k ++) ss << "0";
   
   cout << ss.str() << endl;
   
   
   return 0;
}