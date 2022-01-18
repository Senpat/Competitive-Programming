#include <bits/stdc++.h>

using namespace std;

int N = 1000005;
vector<vector<int>> divs(N);

int n;

int gcd(int a, int b){
   if(a > b){
      if(b == 0) 
         return a;
      return gcd(a%b,b);
   } 
   if(b > a){
      if(a == 0) 
         return b;
      return gcd(b%a,a);
   }
   return a;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   for(int k = 1; k < N; k++){
      divs[k].push_back(1);
   }
   
   for(int k = 2; k < N; k++){
      for(int j = k; j < N; j+=k){
         divs[j].push_back(k);
      }
   }
   
   cin >> n;
   
   vector<int> array;
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      array.push_back(i);
   }
   
   vector<int> gcds;
   for(int k = 0; k < N; k++){
      gcds.push_back(-1);
   }
   
   for(int k = 0; k < n; k++){
      for(int div : divs[array[k]]){
         if(gcds[div] == -1) gcds[div] = array[k];
         else gcds[div] = gcd(gcds[div],array[k]);
      }
   }
   
   int answer = 0;
   for(int k = 0; k < N; k++){
      if(gcds[k] == k) answer++;
   }
      
   answer -= n;
   cout << answer << endl;
   
   
   
   
   return 0;
}