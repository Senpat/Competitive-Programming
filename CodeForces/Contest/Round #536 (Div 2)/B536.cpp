#include <bits/stdc++.h>
//bad
using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   long long remain[n+1];
   
   for(int k = 1; k <= n; k++){
      cin >> remain[k];
   }
   
   vector<pair<long long, int>> c(n+1);          //cost, index
   int c1[n+1];
   
   //int loc[n+1];                         //[index] = location of sorted
   c.push_back(make_pair(-1,-1));
   for(int k = 1; k <= n; k++){
      long long a;
      
      cin >> a;
      
      c.push_back(make_pair(a,k));
      c1[k] = a;
   }
   
   sort(c.begin(),c.end());
   /*
   for(int k = 1; k <= 1; k++){
      loc[c[k].second] = k;
   }*/
   
   for(int k = 0; k < m; k++){
      int t;
      long long d;
      
      cin >> t >> d;
      
      long long cost = 0;
      
      if(remain[t] >= d){
         remain[t] -= t;
         cost = d*c1[t];
      } else {
         
         int need = d-remain[t];
         cost = remain[t]*c1[t];
         cout << cost << endl;
         int index = 1;
         while(need > 0 && index <= n){
            int i = c[index].second;
            if(remain[i] >= need){
               cost += need*c1[i];
               remain[i]-=need;
            } else {
               cost += remain[i]*c[index].first;
               need-=remain[i];
               remain[i] = 0;
            }
            cout << index << " " << need << " " << cost << endl;
            index++;
         }
         
         if(need == 0){
            cost = 0;
         }
         
         
      }
      
      cout << cost << endl;
   }
   
   
   
   
   
   return 0;
}