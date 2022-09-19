#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   bool debug = false;
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<vector<int>> matrix(n,vector<int>(2,0));
      
      for(int k = 0; k < 2; k++){
         for(int j = 0; j < n; j++){
            cin >> matrix[j][k]; 
         }
      }
      
      //time to get to that cell while zig zagging
      vector<vector<int>> zigtime(n,vector<int>(2,0));
      int x = 0;
      int y = 0;
      
      zigtime[x][y] = 0;
      int prev = 0;
      y++;
      while(x < n){
         zigtime[x][y] = max(matrix[x][y]+1,prev+1);
         prev = zigtime[x][y];
         if(x % 2 == 0){
            if(y == 1){
               x++;
            } 
            else {
               y++;
            }
         } 
         else {
            if(y == 0){
               x++;
            } 
            else {
               y--;
            }
         }
         
      }
      
      if(debug){
         for(int k = 0; k < 2; k++){
            for(int j = 0; j < n; j++){
               cout << zigtime[j][k] << " ";
            } 
            cout << "\n";
         }
      }
      
      matrix[0][0] = -1;
      vector<vector<int>> endtime(n,vector<int>(2,0));
      
      endtime[n-1][0] = max(matrix[n-1][0],matrix[n-1][1]+1)+1;
      endtime[n-1][1] = max(matrix[n-1][1],matrix[n-1][0]+1)+1;
      
      int prev0 = endtime[n-1][0];
      int prev1 = endtime[n-1][1];
      
      int add = 3;
      for(int k = n-2; k >= 0; k--){
         endtime[k][1] = max(prev1+1,matrix[k][1]+1);
         endtime[k][1] = max(endtime[k][1],matrix[k][0]+1+add);
         
         endtime[k][0] = max(prev0+1,matrix[k][0]+1);
         endtime[k][0] = max(endtime[k][0],matrix[k][1]+1+add);
         
         prev1 = endtime[k][1];
         prev0 = endtime[k][0];
         
         add+=2;
      }
      
      if(debug){
         for(int k = 0; k < 2; k++){
            for(int j = 0; j < n; j++){
               cout << endtime[j][k] << " ";
            } 
            cout << "\n";
         }
      }
      
      //places to end zigzag part
      int answer = INT_MAX;
      for(int k = 0; k < n-1; k++){
         if(k % 2 == 0){
            //do y = 1
            int curanswer = max(zigtime[k][1] + 2*(n-k-1),endtime[k+1][0]);
            answer = min(answer,curanswer);
         }
         if(k % 2 == 1){
            //do y = 0
            int curanswer = max(zigtime[k][0] + 2*(n-k-1),endtime[k+1][1]);
            answer = min(answer,curanswer);
         }
      }
      
      if(n % 2 == 0){
         answer = min(answer,zigtime[n-1][0]);  
      } 
      else {
         answer = min(answer,zigtime[n-1][1]);
      }
      
      answer = min(answer,endtime[0][1]);
      
      cout << answer << "\n";
      
   }
   
   return 0;
}