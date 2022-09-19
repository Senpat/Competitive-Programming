#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<long long> array(m,0LL);
      for(int k = 0; k < m; k++){
         cin >> array[k];
      }
      
      sort(array.begin(),array.end());
      
      priority_queue<long long> pq;
      
      for(int k = 1; k < m; k++){
         long long diff = array[k]-array[k-1]-1;
         if(diff > 0LL) pq.push(diff);
      }
      
      long long end = n-array[m-1];
      long long start = array[0]-1;
      if(end + start > 0) pq.push(end + start);
      
      long long answer = 0LL;
      long long sub = 0LL;
      while(!pq.empty()){
         long long i = pq.top();
         pq.pop();
         
         if(i > sub+1LL){
            answer += i-(sub+1LL);
         } else if(i > sub){
            answer ++;
         }
         
         
         sub += 4LL;
      }
      
      cout << n-answer << "\n";
   }
   
   
   
   
   return 0;
}