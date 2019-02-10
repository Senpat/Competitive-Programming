#include <bits/stdc++.h>
//absolutely awful - hacked
using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,i,m;
   
   cin >> n >> i >> m;
   
   long long array[n];
   
   long long maxi = 0;
   
   long long sum = 0;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      maxi = max(maxi,array[k]);
      sum += array[k];
   }
   
   sort(array,array+n);
   
   int index = 0;
   while(m > 0 && array[index] != maxi){
      
      sum -= array[index];
      m--;
      index++;
   }
   long double answer = 0.0;
   if(m == 0){
      answer = 1.0*sum/(n-index);
      //cout << sum << " " << index;
      cout << setprecision(10) << answer;
      return 0;
   }
   
   //now we have n-index number of equal elements
   int num = n-index;
   while(m > 1 && num > 1){
      m--;
      num--;
   }
   
   if(m == 1){
      answer = 1.0*(num * maxi + 1.0)/(num);
   } else {
      answer = maxi + min(i,m);
   }
   
   cout << setprecision(10) << answer;
   
   
   
   
   
   
   return 0;
}