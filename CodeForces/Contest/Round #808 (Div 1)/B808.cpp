#include <bits/stdc++.h>

using namespace std;

#define N 500005

int freq[N];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   
   for(int q = 0; q < t; q++){
      
      int n;
      cin >> n;
      
      int freqmax = 0;
      vector<int> array(n);
      for(int k = 0; k < n; k++){
         int x;
         cin >> x;
         array.push_back(x);
         freq[x]++;
         freqmax = max(freqmax,x);
      }
      
      vector<pair<int,int>> p1;
      vector<pair<int,int>> p2;
      
      for(int k = 0; k <= freqmax){
         if(freq[k] == 0) continue;
         p1.push_back(make_pair(k,freq[k]));
      }
      
      int answer = 0;
      
      for(int rep = 0; rep < n-1; rep++){
         
         int last = -1;
         for(int k = 0; k <= amax; k++){
            if(freqa[k] == 0) continue;
            if(last == -1){
               freqb[0] += freqa[k]-1;
               last = k;
               
            } else {
               freqb[k-last]++;
               freqb[0] += max(0,freqa[k]-1);
               
               bmax = max(bmax,k-last);
               
               last = k;
               
            }
         
         
         }
         for(int k = 0; k <= bmax; k++){
            freqa[k] = freqb[k];
            freqb[k] = 0;
         }
         for(int k = bmax+1; k <= amax; k++){
            freqa[k] = 0;
         }
         
         amax = bmax;
         bmax = 0;
         
         if(rep == n-2){
            answer = amax;
         }
      }
      
      cout << answer << endl;
   
   
   }
   
   
   
   
   return 0;
}