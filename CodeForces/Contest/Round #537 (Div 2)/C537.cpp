#include <bits/stdc++.h>

using namespace std;

#define MAXM 100005

int n,m;
long long A,B;
long long arr[MAXM];


long long dfs(int l, int r){
   int lb = lower_bound(arr,arr+m,l) - arr;
   int lr = lower_bound(arr,arr+m,r+1) - arr;
   //cout << lb << " " << lr << " " << l << " " << r << endl;
   if(l == r){
      //find if there are avengers here
      if(lb == lr){
         //no avengers here
         return A;
      } else {
         return B * (lr-lb);
      }
   }
   //use this segment
   if(lb == lr){  
      return A;
   }
   return min(dfs(l,(l+r)/2) + dfs((l+r)/2+1,r),B * (lr-lb) * (r-l+1));
   
   
}   


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m >> A >> B;
   
   for(int k = 0; k < m; k++){
      cin >> arr[k];
   }
   
   sort(arr,arr+m);
   
   long long answer = dfs(1,1<<n);
   
   cout << answer;
   
   return 0;
}