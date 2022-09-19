#include <bits/stdc++.h>
 
using namespace std;
 
#define MAXN 200005
#define K 20
 
int lg[MAXN+1]; 
int st[MAXN][K + 1];
 
int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<int> array(m);
   for(int k = 0; k < m; k++){
      cin >> array[k];
   }
   
   //int lg[MAXN+1];
   lg[1] = 0;
   for (int i = 2; i <= MAXN; i++)
      lg[i] = lg[i/2] + 1;
 
   //int st[MAXN][K + 1];
 
   for (int i = 0; i < m; i++)
      st[i][0] = array[i];
 
   for (int j = 1; j <= K; j++)
      for (int i = 0; i + (1 << j) <= m; i++)
         st[i][j] = max(st[i][j-1], st[i + (1 << (j - 1))][j - 1]);
 
   int q;
   cin >> q;
   
   for(int k = 0; k < q; k++){
      int xs,ys,xt,yt,i;
      cin >> xs >> ys >> xt >> yt >> i;
      ys--;yt--;
      if(yt < ys) swap(yt,ys);
      
      if(abs(yt-ys) % i != 0){
         cout << "NO\n";;
         continue;
      }
      if(abs(xt-xs) % i != 0){
         cout << "NO\n";
         continue;
      }
      
      int high = xs + (n-xs)/i*i;
      
      int j = lg[yt - ys + 1];
      int getmax = max(st[ys][j], st[yt - (1 << j) + 1][j]);
 
      
      if(getmax < high){
         cout << "YES\n";
      } 
      else {
         cout << "NO\n";
      }
   
   }
   
   
   return 0;
}