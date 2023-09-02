#include <bits/stdc++.h>

using namespace std;

struct HASH{
  size_t operator()(const pair<long long, long long>&x)const{
    return hash<long long>()(x.first^(x.second<<32));
  }
};

int P = 2;
long long MOD[2] = {1000000007L,1000000009LL};
long long base[2] = {200003LL,200007LL};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int N = 26;
   vector<vector<long long>> hash(P,vector<long long>(N));
   for(int k = 0; k < P; k++) hash[k][0] = 1LL;
   for(int k = 1; k < N; k++){
      for(int p = 0; p < P; p++){
         hash[p][k] = (hash[p][k-1] * base[p] + MOD[p])%MOD[p];
      }
   }
   
   string s;
   cin >> s;
   int n = s.length();
   
   unordered_set<char> uset;
   for(int k = 0; k < n; k++){
      uset.insert(s[k]);
   }
   
   int c = uset.size();
   
   vector<long long> psum(P,0LL);
   for(int k = 0; k < 26; k++){
      if(uset.find(k+'a') == uset.end()) continue;
      for(int p = 0; p < P; p++){
         if(uset.size() == 1){
            hash[p][k] = MOD[p] - psum[p];
         }
         psum[p] += hash[p][k];
      }
      uset.erase(k+'a');
      
   }
   
   unordered_map<pair<long long, long long>,long, HASH> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   hmap[make_pair(0LL,0LL)] = 1LL;
   
   long long answer = 0LL;
   pair<long long, long long> curhash = make_pair(0LL,0LL);
   for(int k = 0; k < n; k++){
      curhash.first = (curhash.first + hash[0][s[k]-'a'] + MOD[0]) % MOD[0];
      curhash.second = (curhash.second + hash[1][s[k]-'a'] + MOD[1]) % MOD[1];
      answer += hmap[curhash];
      hmap[curhash]++;
      //cout << curhash.first << " " << curhash.second << endl;
   }
   
   cout << answer << endl;
   
   
   return 0;
}