#include <bits/stdc++.h>

using namespace std;

const int MAXN = 200001;

int n,m;
int prev1[MAXN];
int cur[MAXN];

priority_queue<int> pql;
priority_queue<int, vector<int>, std::greater<int>> pqr;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   for(int k = 1; k <= n; k++){
      cin >> prev1[k];
   }
   
   fill(begin(cur),end(cur),1);
   
   
   
   int i = 1;
   while( i <= m){
      pql = priority_queue<int>();
      pqr = priority_queue<int,vector<int>, std::greater<int>>();
   
      for(int k = 1; k <= n; k++){
         while(!pqr.empty() && pqr.top() < k) pqr.pop();
         cur[k] += pqr.size();
         pqr.push(k+prev1[k]);
      }
      for(int k = n; k >= 1; k--){
         while(!pql.empty() && pql.top() > k) pql.pop();
         cur[k] += pql.size();
         pql.push(k-prev1[k]);
      }
      
      bool done = true;
      for(int k = 1; k <= n; k++){
         prev1[k] = cur[k];
         cur[k] = 1;
         if(prev1[k] < n) done = false;
      }
      
      if(done) break;
      i++;
   }
   
   for(int k = 1; k <= n; k++){
      cout << prev1[k] << " ";
   }
   
   
   
   return 0;
}