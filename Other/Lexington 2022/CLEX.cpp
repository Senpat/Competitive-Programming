#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   int n;
   long long m;
   cin >> n >> m;
   
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   if(n == 1){
      cout << array[0] << endl;
      return 0;
   }
   
   sort(array.begin(),array.end());
   
   long long sum = 0LL;
   for(int k = 0; k < n/2; k++){
      sum += array[k];
   }
   
   long long move = min(m/2,sum);
   
   int i = n/2;
   long answer = array[i];
   long long moved = 0LL;
   while(i < n-1 && moved <= move){
      long long curmove = (array[i+1]-array[i])*(long long)(i-n/2+1);
      
      if(curmove + moved > move){
         break;
      } 
      else {
         answer = array[i+1];
         moved += curmove;
         i++;
      }
   } 
   
   if(moved < move){
      //move as much as you can
      long long d = (long long)(i-n/2+1);
      answer += (move-moved)/d;
   } 
   
   cout << answer << endl;
   
   return 0;
}