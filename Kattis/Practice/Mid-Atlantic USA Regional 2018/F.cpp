#include <bits/stdc++.h>

using namespace std;

const int MAXN = 24;
const int MAXM = 144;

int arr[MAXN];
long long pow6[MAXN+1];
long long probs[MAXN][MAXM+1];

unordered_set<int> poss[MAXN+1];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   int sum = 0;
   for(int k = 0; k < n; k++){
      cin >> arr[k];
      sum += arr[k];
   }
   
   if(sum == 0){
      cout << "0" << endl;
      return 0;
   }
   
   
   
   for(int k = 0; k < 23; k++){
      for(int j = 0; j < 145; j++){
         probs[0][k] = 0LL;
      }
   }
   
   for(int k = 1; k <= 6; k++){
      probs[0][k] = 1LL;
   }
   
   for(int k = 0; k < 23; k++){
      for(int j = 0; j < 145; j++){
         if(probs[k][j] == 0) 
            continue;
         for(int h = j+1; h <= j+6; h++){
            probs[k+1][h] += probs[k][j];
         }
      }
   }
   
   pow6[0] = 1LL;
   for(int k = 1; k <= MAXN; k++){
      pow6[k] = pow6[k-1]*6LL;
   }
   
   for(int k = 0; k < 23; k++){
      for(int j = 0; j < 145; j++){
         probs[k][j] *= pow6[23-k];
      }
   }
   
   int top = 1<<24;
   for(int k = 0; k < top; k++){
         
      int cursum = 0;
      int i = k;
      int num1 = 0;              //number of 1s so far
      int numt = 0;              //total number of digits
      while(i > 0 && numt < n){
         if(i%2 == 1){
            cursum += arr[numt];
            num1++;
         }
         numt++;
         i >>= 1;
      }
         
      poss[num1].insert(cursum);
   }
   
   long long maxprob = -1;
   int answer = -1;
      
   for(int k = 1; k <= 24; k++){
      for(int sump : poss[k]){
         int i = m-(sum-sump);
         if(i >= 0 && i <= 144){
            if(probs[k-1][i] > maxprob){
               maxprob = probs[k-1][i];
               answer = k;
            }
         }
      }
   }
   
   cout << answer << endl;
   
   
   
   
   
   return 0;
}