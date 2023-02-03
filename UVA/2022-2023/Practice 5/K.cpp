#include <bits/stdc++.h>

using namespace std;

int N = 100;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   vector<int> afreq(N+1,0);
   vector<int> bfreq(N+1,0);
   
   int n;
   cin >> n;
   
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      afreq[a]++;
      bfreq[b]++;
      
      auto acur = afreq;
      auto bcur = bfreq;
      
      int answer = 0;
      
      int ai = 1;
      int bi = N;
      
      int added = 0;
      
      while(added <= k){
         if(acur[ai] == 0) ai++;
         else if(bcur[bi] == 0) bi--;
         else{
            answer = max(answer,ai+bi);
            int minfreq = min(acur[ai],bcur[bi]);
            added += minfreq;
            acur[ai] -= minfreq;
            bcur[bi] -= minfreq;
         }
         
      }
      
      cout << answer << "\n";
   }
   
   
   return 0;
}