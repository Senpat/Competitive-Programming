#include <bits/stdc++.h>
//upsolve - use a lot of long double and 
using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   long double i;
   cin >> n >> i >> m;
   
   long long array[n];
   
   long long sum = 0;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      sum += array[k];
   }
   
   sort(array,array+n);
   
   long double answer = 0.0;
   for(int k = 0; k < n && k <= m; k++){
      
      long double cur = 1.0*(sum + (long double)min(i*(long double)(n-k),(long double)(m-k)))/((long double)(n-k));
      //cout << k << " " << cur << endl;
      answer = max(cur,answer);
      sum -= array[k];
   }
   
   
   
   
   cout << setprecision(10) << answer;
   
   return 0;
}