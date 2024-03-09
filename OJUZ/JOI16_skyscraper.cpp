#include <bits/stdc++.h>
//connected components dp practice

using namespace std;

const long long MOD = 1000000007LL;

void add(long long& x, long long y){
   x += y;
   if(x >= MOD) x -= MOD;
}

long long mul(long long a, long long b){
   return (a*b + MOD)%MOD;
}

//# of ways for n (mod 2), # of components, L, # of ends used(0, 1, or 2)
long long dp[2][101][1001][3];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n, L;
   cin >> n >> L;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   sort(array.begin(),array.end());
   
   if(n == 1){
      cout << 1 << endl;
      return 0;
   }
   if(n == 2){
      if(array[1] - array[0] <= L){
         cout << 2 << endl;
      } else {
         cout << 0 << endl;
      }
      return 0;
   }
   
   //n >= 3
   //add first element in middle
   dp[0][1][0][0] = 1LL;
   //add on either end
   dp[0][1][0][1] = 2LL;
   
   int newcost;
   for(int k = 0; k < n-1; k++){
      int from = k&1;
      int to = from^1;
      
      int diff = array[k+1] - array[k];
      for(int j = 1; j <= n; j++){
         long long jl = (long long)j;
         for(int h = 0; h <= L; h++){
            //# of ends used
            for(int g = 0; g <= 2; g++){
               newcost = h + diff * (j*2 - g);
               
               if(newcost <= L){
                  //add new component
                  if(j+1 <= n){
                     //add to middle (not an end)
                     add(dp[to][j+1][newcost][g], mul(dp[from][j][h][g],jl+1 - g));
                     
                     //add to end
                     if(g == 0){
                        //2 ends open, add once for each end
                        add(dp[to][j+1][newcost][1], dp[from][j][h][0]);
                        add(dp[to][j+1][newcost][1], dp[from][j][h][0]);
                     } else if(g == 1){
                        //1 end open
                        add(dp[to][j+1][newcost][2], dp[from][j][h][1]);
                     }
                  }
                  
                  //add to endpoint of component
                  
                  //add to middle (not an end)
                  add(dp[to][j][newcost][g], mul(dp[from][j][h][g], jl*2LL - g));
                  
                  //add to end
                  if(g == 0){
                     //2 ends open
                     add(dp[to][j][newcost][1], dp[from][j][h][0]);
                     add(dp[to][j][newcost][1], dp[from][j][h][0]);
                  } else if(g == 1){
                     //1 end open
                     add(dp[to][j][newcost][2], dp[from][j][h][1]);
                  }
                  
                  //join components
                  if(j >= 2){
                     add(dp[to][j-1][newcost][g], mul(dp[from][j][h][g], jl-1));
                  }
                     
               }
               
               //clear
               dp[from][j][h][g] = 0LL;
            }
            
            
         }
      }
   }
   
   long long answer = 0LL;
   for(int l = 0; l <= L; l++){
      add(answer,dp[n&1^1][1][l][2]);
   }
   
   cout << answer << endl;
   
   
   return 0;
}