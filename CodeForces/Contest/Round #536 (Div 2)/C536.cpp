#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<long long> array(n);
   
   for(int k = 0; k < n; k++){
      long q;
      cin >> q;
      array[k] = q;
   }
   
   sort(array.begin(),array.end());
   
   long long answer = 0;
   
   for(int k = 0; k < n/2; k++){
      long long i = array[k] + array[n-1-k];
      answer += i*i;
   }
   
   cout << answer;
   
   
   return 0;
}