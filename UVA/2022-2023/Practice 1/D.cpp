#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
vector<int> freq(10,0);

vector<bool> seen(10,false);

void dfs(int v, int x, int s){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(nei == s){
         freq[x+1]++;
      }
      if(seen[nei]) continue;
      
      dfs(nei,x+1,s);
   }
   seen[v] = false;
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   for(int k = 0; k < n; k++){
      vector<int> temp;
      adj.push_back(temp);
   }
   
   for(int k = 0; k < m; k++){
      int a,b;
      cin >> a >> b;
      
      adj[a].push_back(b);
   }
   
   for(int k = 0; k < n; k++){
      dfs(k,0,k);
   }
   
   int answer = 0;
   for(int k = 2; k < 10; k++){
      answer += freq[k]/k;
   }
   cout << answer << endl;
   
   
   
   return 0;
}