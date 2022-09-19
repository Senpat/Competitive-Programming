#include <bits/stdc++.h>

using namespace std;

long long BASE = 31LL;
long long MOD = 1000000007LL;
long long BASEI = 129032259LL;

long long DPMOD = 1000000007LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 505;
   vector<long long> pow(N,0LL);
   pow[0] = 1LL;
   for(int k = 1; k < N; k++){
      pow[k] = (pow[k-1] * BASE + MOD)%MOD;
   }
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      string a,b;
      cin >> a >> b;
      
      int an = a.length();
      int bn = b.length();
      
      if(an < bn){
         cout << "0 1\n";
         continue;
      }
      
      //get hash of b
      long long bhash = 0LL;
      for(int k = 0; k < bn; k++){
         long long prod = ((long long)(b[k]-'a'+1)*pow[k] + MOD)%MOD;
         bhash = (bhash + prod + MOD)%MOD;
      }
      
      vector<pair<int,int>> intervals;
      //get hashes of a
      long long ahash = 0LL;
      for(int k = 0; k < bn; k++){
         long long prod = ((long long)(a[k]-'a'+1)*pow[k] + MOD)%MOD;
         ahash = (ahash + prod + MOD)%MOD;
      }
      
      if(ahash == bhash){
         intervals.push_back(make_pair(0,bn-1));
      }
      
      for(int k = bn; k < an; k++){
         //subtract a[k-bn]
         long long sub = (long long)(a[k-bn]-'a'+1);
         ahash = (ahash - sub + MOD)%MOD;
         //divide by base
         ahash = (ahash * BASEI + MOD)%MOD;
         //add
         long long add = ((long long)(a[k]-'a'+1)*pow[bn-1] + MOD)%MOD;
         ahash = (ahash + add + MOD)%MOD;
         
         if(ahash == bhash){
            intervals.push_back(make_pair(k-bn+1,k));
            //cout << "adding: " << k-bn+1 << " " << k << endl;
         }
      }
      
      if(intervals.size() == 0){
         cout << "0 1\n";
         continue;
      }
      
      //[dp[k][j][h] -> first k intervals, j is the last element of the earliest interval that is not covered, h is # of intervals used
      vector<vector<vector<long long>>> dp(intervals.size(),vector<vector<long long>>(an+1,vector<long long>(intervals.size()+1,0LL)));
      
      //use first interval
      dp[0][an][1] = 1LL;
      //don't use first interval
      dp[0][intervals[0].second][0] = 1LL;
      
      for(int k = 0; k < intervals.size()-1; k++){
         for(int j = 0; j <= an; j++){
            for(int h = 0; h < intervals.size(); h++){
               if(dp[k][j][h] == 0LL) continue;
               if(intervals[k+1].first > j) continue;
               
               //use interval
               dp[k+1][an][h+1] = (dp[k+1][an][h+1] + dp[k][j][h] + DPMOD)%DPMOD;
               //don't use interval
               if(j == an){
                  dp[k+1][intervals[k+1].second][h] = (dp[k+1][intervals[k+1].second][h] + dp[k][j][h] + DPMOD)%DPMOD;
               } else {
                  dp[k+1][j][h] = (dp[k+1][j][h] + dp[k][j][h] + DPMOD)%DPMOD;
               }
            }
         }
      }
      
      int a1 = 0;
      long long a2 = 0;
      
      for(int k = 0; k <= intervals.size(); k++){
         if(dp[intervals.size()-1][an][k] != 0LL){
            a1 = k;
            a2 = dp[intervals.size()-1][an][k];
            break;
         }
      }
      
      cout << a1 << " " << a2 << "\n";
   }
   
   
   return 0;
}