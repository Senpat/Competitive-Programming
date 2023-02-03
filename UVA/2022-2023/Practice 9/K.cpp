#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   //monotonic queue
   
   deque<long long> q;
   
   vector<long long> rangemin;
   
   for(int k = 0; k < m; k++){
      while(!q.empty() && q.back() > array[k]){
         q.pop_back();
      }
      q.push_back(array[k]);
   }
   
   for(int k = m; k < n; k++){
      rangemin.push_back(q.front());
      
      while(!q.empty() && q.back() > array[k]){
         q.pop_back();
      }
      q.push_back(array[k]);
      
      if(!q.empty() && q.front() == array[k-m]){
         q.pop_front();
      }
   }
   rangemin.push_back(q.front());
   /*
   for(int k = 0; k < rangemin.size(); k++){
      cout << rangemin[k] << " ";
   }
   cout << endl;
   */
   long long a1 = 0LL;
   //how much was painted
   vector<long long> paint(n,0LL);
   //build sparse table
   int N = rangemin.size();
   vector<int> log(N+1);
   log[1] = 0;
   for(int k = 2; k <= N; k++){
      log[k] = log[k/2]+1;
   }
   
   int K = log[N]+1;
   
   vector<vector<long long>> spt = vector<vector<long long>>(K+1,vector<long long>(N+1,0));
   
   for(int k = 0; k < N; k++){
      spt[0][k] = rangemin[k];
   }
   
   for(int k = 1; k <= K; k++){
      for(int j = 0; j + (1 << k) <= N; j++){
         spt[k][j] = max(spt[k-1][j],spt[k-1][j+(1 << (k-1))]);
      }
   }
   
   for(int k = 0; k < n; k++){
      int l = max(0, k-m+1);
      int r = min((int)rangemin.size()-1,k);
      
      int i = log[r-l+1];
      paint[k] = max(spt[i][l],spt[i][r - (1<<i) +1]);
      
      a1 += array[k]-paint[k];
      //cout << paint[k] << " ";
   }
   //cout << endl;
   
   
   cout << a1 << endl;
   
   int a2 = 0;
   int curnum = paint[0];
   int curstreak = 1;
   for(int k = 1; k < n; k++){
      if(paint[k] == curnum){
         curstreak++;
      } else {
         a2 += (curstreak + m-1)/m;
         curnum = paint[k];
         curstreak = 1;
      }
   }
   
   a2 += (curstreak + m-1)/m;
   
   cout << a2 << endl;
   
   return 0;
}