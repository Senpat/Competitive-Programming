#include <bits/stdc++.h>

using namespace std;

int SQRT = 420;
long long MOD = 998244353LL;
long long BASE = 37LL;

long long base[200001];
long long invBase[200001];

long long power(long long x, long long y, long long p){
   long long res = 1L;
   x = x%p;
   while(y > 0){
      if((y&1) == 1) res = (res*x + p)%p;
      y >>= 1;
      x = (x*x+p)%p;
   }
   return res;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   base[0] = 1LL;
   invBase[0] = 1LL;
   
   long long boof = power(BASE, MOD-2, MOD);
   
   for(int i = 1; i <= 200000; i++){
      base[i] = (base[i-1]*BASE + MOD)%MOD;
      invBase[i] = (invBase[i-1]*boof + MOD)%MOD;
   }
   
   string s;
   cin >> s;
   
   int N = s.length();
   vector<char> arr;
   for(int k = 0; k < N; k++){
      arr.push_back(s.at(k));
   }
   arr.push_back('\r');
   N++;
   //cout << N << endl;
   vector<long long> prefix(N);
   prefix[0] = arr[0];
   for(int i = 1; i < N; i++){
      prefix[i] = prefix[i-1] + (arr[i]*base[i] + MOD)%MOD;
      if(prefix[i] >= MOD) prefix[i] -= MOD;
      //cout << prefix[i] << endl;
   }
   
   
   int Q;
   cin >> Q;
   
   vector<string> qstrings;
   vector<int> kth;
   
   for(int q = 0; q < Q; q++){
      string sin;
      int iin;
      cin >> sin >> iin;
      qstrings.push_back(sin);
      kth.push_back(iin);
   }
   
   vector<long long> qhashes;
   unordered_set<long long> smalls;
   for(int qq = 0; qq < Q; qq++){
      string s = qstrings[qq];
      vector<char> temp(s.begin(),s.end());
      int M = temp.size();
      //cout << M << endl;
      long long hash = temp[0];
      
      for(int i = 1; i < M; i++){
         hash += (temp[i]*base[i] + MOD)%MOD;
         if(hash >= MOD) hash-=MOD;
      }
      //cout << hash << endl;
      qhashes.push_back(hash);
      if(M <= SQRT){
         smalls.insert(hash);
         //cout << hash << endl;
      }
   }
   
   unordered_map<long long, vector<int>> map;
   
   for(int length=1; length <= SQRT; length++){
      
      for(int a = 0; a < N-length+1; a++){
         int b = a+length-1;
         long long hash = prefix[b];
         //if(length==1) cout << hash << " " << b << " " << prefix[b] << endl;
         if(a > 0){
            hash -= prefix[a-1];
            if(hash < 0) hash+=MOD;
            hash = (hash*invBase[a] + MOD)%MOD;
         }
         //if(length==1) cout << hash << endl;
         if(smalls.find(hash) != smalls.end()){
            if(map.find(hash) == map.end()){
               vector<int> newvec;
               map[hash] = newvec;
            }
            map[hash].push_back(a+1);
            //cout << a+1 << endl;
         }
      }
   }
   
   for(int qq=0; qq < Q; qq++){
      int M = qstrings[qq].length();
      if(M <= SQRT){
         if(map.find(qhashes[qq]) != map.end()){
            vector<int> ls = map[qhashes[qq]];
            if(kth[qq] <= ls.size()){
               cout << ls[kth[qq]-1] << "\n";
            } else {
               cout << -1 << "\n";
            }
         } else {
            cout << -1 << "\n";
         }
      } else {
         int cnt = 0;
         bool found = false;
         for(int i = 0; i < N-M+1; i++){
            long long hash = prefix[i+M-1];
            if(i > 0){
               hash -= prefix[i-1];
               if(hash < 0) hash+=MOD;
               hash = (hash*invBase[i] + MOD)%MOD;
            }
            if(hash == qhashes[qq]){
               cnt++;
               if(cnt == M){
                  cout << i+1 << "\n";
                  found = true;
                  break;
               }
            }
         }
         if(!found)
            cout << -1 << "\n";
      }
   }
   /*
      for(int k = 0; k < Q; k++){
         cout << qhashes[k] << endl;
      }*/
   return 0;
}