#include <bits/stdc++.h>

using namespace std;

int query(int a, int b){
   cout << "? " << a << " " << b << endl;
   int ret;
   cin >> ret;
   return ret;
}

//get result of 4 team single elimination in 2 queries
int get4(int a, int b, int c, int d){
   int first = query(a,c);
   if(first == 0){
      //a == c == 0
      int second = query(b,d);
      if(second == 1) return b;
      return d;
   } else if(first == 1){
      //a > c
      int second = query(a,d);
      if(second == 1) return a;
      return d;
   } else {
      //c > a
      int second = query(c,b);
      if(second == 1) return c;
      return b;
   }
   //second shouldn't equal 0
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n2;
      cin >> n2;
      
      int n = (1 << n2);
      
      vector<int> array(n,0);
      for(int k = 0; k < n; k++){
         array[k] = k+1;
      }
      
      //find the winner of every 4 game bracket in 2 queries
      while(array.size() >= 4){
         vector<int> next;
         
         for(int k = 0; k < array.size(); k += 4){
            next.push_back(get4(array[k],array[k+1],array[k+2],array[k+3]));
         }
         
         array.swap(next);
         next = vector<int>();
      }
      
      int answer = array[0];
      if(array.size() == 2){
         int response = query(array[0],array[1]);
         if(response == 1) answer = array[0];
         else answer = array[1];
         //response shouldn't equal 0;
      }
      
      cout << "! " << answer << endl;
   }
   
   
   return 0;
}