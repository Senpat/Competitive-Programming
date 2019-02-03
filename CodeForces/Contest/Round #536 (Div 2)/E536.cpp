#include <bits/stdc++.h>

using namespace std;

#define MAXN 100005
#define MAXM 205

struct Envelope{
   int s;
   int t;
   int d;
   long long w;
};

//sort in ascending order by start time
struct CompareE1{
   bool operator()(Envelope e1, Envelope e2){
      return e1.s > e2.s;
   }
};

//sort in descending order by coins, descending order in d for tiebreaker
struct CompareE2{
   bool operator()(Envelope e1, Envelope e2){
      if(e1.w == e2.w) return e1.d < e2.d;
      return e1.w < e2.w;
   }
};



long long dp[MAXN][MAXM];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,e;
   cin >> n >> m >> e;
   
   priority_queue<Envelope,vector<Envelope>,CompareE1> pq;
   
   for(int k = 0; k < e; k++){
      int si,ti,di;
      long long wi;
      
      cin >> si >> ti >> di >> wi;
      
      Envelope cur {si,ti,di,wi};
      //cout << cur.s << " " << cur.t << " " << cur.d << " " << cur.w << endl;
      pq.push(cur);
   }
   
   priority_queue<Envelope,vector<Envelope>,CompareE2> pq2;
   
   for(int k = 0; k < MAXN; k++){
      fill(begin(dp[k]),end(dp[k]),LLONG_MAX);
   }
   
   dp[1][0] = 0;
   //cout << pq.top().s << endl;
   for(int k = 1; k <= n; k++){
      //find which envelope he opens
      while(!pq.empty() && pq.top().s <= k){
         //cout << "pq2 push " << k << " " << pq.top().s << endl;
         pq2.push(pq.top());
         pq.pop();
      }
      
      while(!pq2.empty() && pq2.top().t < k){
         pq2.pop();
      }
      if(pq2.empty()){
         //cout << "empty " << k << endl;
         for(int j = 0; j <= m; j++){
            dp[k+1][j] = min(dp[k+1][j],dp[k][j]);
         }
         continue;
      }
      Envelope cur = pq2.top();
      //pq2.pop();
      //cout << k << " " << cur.s << " " << cur.w << endl;
      for(int j = 0; j <= m; j++){
         if(dp[k][j] == LLONG_MAX) continue;
         //stop him
         if(j + 1 <= m) dp[k+1][j+1] = min(dp[k+1][j+1],dp[k][j]);
         //cout << "\t\t" << k << " " << j << " " << dp[k+1][j+1] << " " << dp[k][j] << endl;
         //doesn't stop him
         
         if(cur.d <= n) {
            dp[k][j] += cur.w;
            dp[cur.d+1][j] = min(dp[cur.d+1][j],dp[k][j]);
         }
         
         
      }
   }
   /*
   for(int k = 0; k <= n+1; k++){
      for(int j = 0; j <= m; j++){
         cout << dp[k][j] << '\t';
      }
      cout << endl;
   }
   */
   
   long long answer = LLONG_MAX;
   
   for(int k = 0; k <= m; k++){
      answer = min(answer,dp[n+1][k]);
   }
   
   cout << answer;
      
   
   
   
   
   
   
   return 0;
}