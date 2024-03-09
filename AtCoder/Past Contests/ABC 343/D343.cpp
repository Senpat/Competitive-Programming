#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,t;
   cin >> n >> t;
   
   multiset<long long> mset;
   vector<long long> array(n+1,0LL);
   
   for(int k = 1; k <= n; k++) mset.insert(0LL);
   int ans = 1;
   
   for(int k = 0; k < t; k++){
      int a;
      long long b;
      cin >> a >> b;
      mset.erase(mset.find(array[a]));
      if(mset.find(array[a]) == mset.end()){
         ans--;
      }
      
      if(mset.find(array[a]+b) == mset.end()){
         ans++;
      }
      mset.insert(array[a]+b);
      array[a] += b;
      
      cout << ans << endl;
   }
   
   
   
   
   
   return 0;
}