#include <bits/stdc++.h>

using namespace std;

struct alien{
   int l;
   int r;
   int d;
};

int n;
vector<alien> aliens;
vector<vector<int>> dp;

//calculates answer for all intervals that start >= l and end <= r
int dodp(int l, int r){
   if(dp[l][r] != -1) 
      return dp[l][r];

   //get index of vector in the range with highest d
   int maxd = 0;
   int index = -1;
   
   for(int k = 0; k < n; k++){
      if(aliens[k].l >= l && aliens[k].r <= r && aliens[k].d > maxd){
         maxd = aliens[k].d;
         index = k;
      }
   }

   if(index == -1){
      dp[l][r] = 0;
      return 0;
   }
   
   int minadd = INT_MAX;
   
   for(int k = aliens[index].l; k <= aliens[index].r; k++){
      minadd = min(minadd, dodp(l,k-1) + dodp(k+1,r));
   }
   
   dp[l][r] = minadd + aliens[index].d;
   return dp[l][r];
   
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      cin >> n;
      
      unordered_map<int,int> compress;
      vector<int> order;
      aliens = vector<alien>();
      for(int k = 0; k < n; k++){
         int l,r,d;
         cin >> l >> r >> d;
         aliens.push_back({l,r,d});
         
         if(compress.find(l) == compress.end()){
            compress[l] = -1;
            order.push_back(l);
         }
         
         if(compress.find(r) == compress.end()){
            compress[r] = -1;
            order.push_back(r);
         }
         
      }
      
      sort(order.begin(),order.end());
      
      for(int k = 0; k < order.size(); k++){
         compress[order[k]] = k+1;
      }
      
      for(int k = 0; k < n; k++){
         aliens[k].l = compress[aliens[k].l];
         aliens[k].r = compress[aliens[k].r];
      }
      
      int N = order.back();
      
      dp = vector<vector<int>>(N+1,vector<int>(N+1,-1));
      int answer = dodp(1,N);
      
      cout << answer << endl;
      
   }
   
   
   return 0;
}