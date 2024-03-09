#include <bits/stdc++.h>

using namespace std;

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
const int RANDOM = chrono::high_resolution_clock::now().time_since_epoch().count();
struct chash {
    int operator()(int x) const { return x ^ RANDOM; }
};


int comb3(int a, int b, int c){
   return (a << 2) + (b << 1) + c;
}

//max 8^8 ~ 10^7
int gethash(const vector<int>& array){
   int hash = 0;
   for(int k = 0; k < array.size(); k++){
      hash <<= 3;
      hash += array[k];
   }
   //cout << hash << endl;
   return hash;
}

int hashmapping(const vector<int>& array){
   int hash = 0;
   for(int k = 0; k < 8; k++){
      hash *= 9;
      if(array[k] == -1) hash += 8;
      else hash += array[k];
   }
   return hash;
}

int adj[8][4];

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   //precompute graph
   //bits of i is m,x,y
   
   for(int k = 0; k < 8; k++){
      int m = ((k >> 2)&1);
      int x = ((k >> 1)&1);
      int y = ((k >> 0)&1);
      //x = x&y
      adj[k][0] = comb3(m,x&y,y);
      //x = x | y
      adj[k][1] = comb3(m,x|y,y);
      //y = x ^ y
      adj[k][2] = comb3(m,x,x^y);
      //y = y ^ m
      adj[k][3] = comb3(m,x,y^m);
   }
   
   gp_hash_table<int,int,chash> qcache;
   
   int t;
   cin >> t;
   for(int tt = 0; tt < t; tt++){
      int a,b,c,d,m;
      cin >> a >> b >> c >> d >> m;
      
      vector<int> mapping(8,-1);
      
      bool fail = false;
      for(int k = 0; k < 30; k++){
         int ai = ((a >> k)&1);
         int bi = ((b >> k)&1);
         int ci = ((c >> k)&1);
         int di = ((d >> k)&1);
         int mi = ((m >> k)&1);
         
         int from = comb3(mi,ai,bi);
         int to = comb3(mi,ci,di);
         
         if(mapping[from] == -1) mapping[from] = to;
         else if(mapping[from] != to){
            fail = true;
            break;
         }
         
         if(mapping[from] == 1 && from != 1) fail = true;
      }
      
      /*
      for(int k = 0; k < 8; k++){
         cout << mapping[k] << " ";
      }
      cout << endl;
      */
      
      if(mapping[0] != 0 && mapping[0] != -1) fail = true;
      
      
      if(fail){
         cout << "-1\n";
         continue;
      }
      
      int testhash = hashmapping(mapping);
      if(qcache.find(testhash) != qcache.end()){
         cout << qcache[testhash] << endl;
         continue;
      }
      
      gp_hash_table<int,int,chash> seen;
            
      vector<int> start;
      vector<int> finish;
      for(int k = 0; k < 8; k++){
         if(mapping[k] != -1){
            start.push_back(k);
            finish.push_back(mapping[k]);
         }
      }
      int sz = start.size();
      
      int endhash = gethash(finish);
      
      if(gethash(start) == endhash){
         cout << "0\n";
         continue;
      }
      
      queue<pair<int,vector<int>>> q;
      seen[gethash(start)] = -1;
      int answer = -1;
      q.push(make_pair(0,start));
      
      while(!q.empty()){
         auto [d,vec] = q.front();
         q.pop();
         
         bool found = false;
         for(int k = 0; k < sz; k++){
            if(vec[k] == 0 && finish[k] != 0) found = true;
            if(finish[k] == 1 && vec[k] != 1) found = true;
            for(int j = k+1; j < sz; j++){
               if(vec[k] == vec[j] && finish[k] != finish[j]){
                  found = true;
                  break;
               }
            }
            if(found) break;
         }
         
         if(found) continue;
         
         for(int e = 0; e < 4; e++){
            vector<int> nei(sz);
            for(int k = 0; k < sz; k++){
               nei[k] = adj[vec[k]][e];
            }
            
            int hash = gethash(nei);
            if(hash == endhash){
               answer = d+1;
               break;
            }
            
            if(seen.find(hash) != seen.end()) continue;
            
            seen[hash] = -1;
            q.push(make_pair(d+1,nei));
         }
         
         if(answer != -1) break;
      }
      
      qcache[testhash] = answer;
      cout << answer << endl;
      
      
      
   }
   
   
   return 0;
}