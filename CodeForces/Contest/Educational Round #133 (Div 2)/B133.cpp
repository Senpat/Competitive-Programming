#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<vector<int>> matrix(n,vector<int>(n,0));
      
      for(int k = 0; k < n; k++){
         matrix[0][k] = k+1;
      }
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < n; j++){
            matrix[k][j] = matrix[k-1][j];
         }
         
         //swap matrix[k][k] and matrix[k][k-1]
         int temp = matrix[k][k];
         matrix[k][k] = matrix[k][k-1];
         matrix[k][k-1] = temp;
      }
      
      cout << n << "\n";
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            cout << matrix[k][j] << " ";
         }
         cout << "\n";
      }
   }    
   
   
   return 0;
}