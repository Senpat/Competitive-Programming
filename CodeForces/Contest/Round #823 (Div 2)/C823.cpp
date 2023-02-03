#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      string s;
      cin >> s;
      int n = s.length();
      vector<int> array(n);
      for(int k = 0; k < n; k++){
         array[k] = s[k]-'0';
      }
      
      vector<int> add1(10,0);
      
      stack<int> start;
      
      for(int k = 0; k < n; k++){
         while(!start.empty() && start.top() > array[k]){
            add1[min(start.top()+1,9)]++;
            start.pop();
         }
         
         start.push(array[k]);
      }
      
      vector<int> add0(10,0);
      while(!start.empty()){
         add1[start.top()]++;
         start.pop();
      }
      
      vector<int> answer;
      for(int k = 0; k <= 9; k++){
         for(int j = 0; j < add1[k]; j++){
            answer.push_back(k);
         }
      }
      
      for(int k = 0; k < answer.size(); k++){
         cout << answer[k];
      }
      cout << "\n";
   }
   
   
   return 0;
}