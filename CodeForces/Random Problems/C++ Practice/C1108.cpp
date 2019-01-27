#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   string s;
   cin >> s;
   
   string start[6] = {"RGB","RBG","GBR","GRB","BRG","BGR"};
   
   int min = INT_MAX;
   string minperm = start[0];
   
   for(int k = 0; k < 6; k++){
      int count = 0;
      for(int j = 0; j < n; j++){
         if(s[j] != start[k][j%3]){
            count++;
         }
      }
      
      if(min > count){
         min = count;
         minperm = start[k];
      }
   }
   
   cout << min << endl;
   for(int k = 0; k < n; k++){
      cout << minperm[k%3];
   }
   
   
   
   
   return 0;
}