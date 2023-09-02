#include <bits/stdc++.h>

using namespace std;


int N = 1000000;
int D = 20;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   vector<int> rightmin(N+1,INT_MAX);
   
   for(int k = 0; k < n; k++){
      int l,r;
      cin >> l >> r;
      rightmin[l] = min(rightmin[l],r);
   }
   
   vector<vector<int>> jump(N+1,vector<int>(D,INT_MAX));
   int next = INT_MAX;
   for(int k = N; k >= 1; k--){
      next = min(next,rightmin[k]);
      jump[k][0] = next;
   }
   
   for(int d = 0; d < D-1; d++){
      for(int k = 1; k <= N; k++){
         if(jump[k][d] != INT_MAX){
            jump[k][d+1] = jump[jump[k][d]][d];
         }
      }
   }
   
   for(int t = 0; t < q; t++){
      int l,r;
      cin >> l >> r;
      
      int answer = 0;
      int cur = l;
      for(int d = D-1; d >= 0; d--){
         if(jump[cur][d] <= r){
            cur = jump[cur][d];
            answer += (1 << d);
         }
      }
      
      cout << answer << endl;
   }
   
   return 0;
}