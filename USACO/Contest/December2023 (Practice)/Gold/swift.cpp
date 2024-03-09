#include <bits/stdc++.h>
#define N 755

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<vector<int>> matrix(n+1,vector<int>(n+1));
   for(int k = 1; k < n; k++){
      string s;
      cin >> s;
      int i = 0;
      for(int j = k+1; j <= n; j++){
         matrix[k][j] = s[i]-'0';
         i++;
      }
   }
   
   vector<bitset<N>> bsets(n+1);
   
   int answer = 0;
   for(int k = n-1; k >= 1; k--){
      for(int j = k+1; j <= n; j++){
         if(bsets[k][j] != matrix[k][j]){
            bsets[k] ^= bsets[j];
            bsets[k].flip(j);
            answer++;
         }
      }
   }
   
   cout << answer << endl;
   
   return 0;
}