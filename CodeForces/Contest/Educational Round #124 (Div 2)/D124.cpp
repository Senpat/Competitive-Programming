#include <bits/stdc++.h>

using namespace std;

int mul2 = 18;

struct custom_hash {
    static uint64_t splitmix64(uint64_t x) {
        // http://xorshift.di.unimi.it/splitmix64.c
        x += 0x9e3779b97f4a7c15;
        x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9;
        x = (x ^ (x >> 27)) * 0x94d049bb133111eb;
        return x ^ (x >> 31);
    }

    size_t operator()(uint64_t x) const {
        static const uint64_t FIXED_RANDOM = chrono::steady_clock::now().time_since_epoch().count();
        return splitmix64(x + FIXED_RANDOM);
    }
};

long long gethash(long long x, long long y){
   return (x << mul2) + y;
}
   
long long getx(long long x){
   return x >> mul2;
}
   
long long gety(long long x){
   return x & ((1 << mul2) -1);
}

unordered_set<long long> hset;

int dx[] = {-1,1,1,-1};
int dy[] = {-1,-1,1,1};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<long long> array;
   
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      
      array.push_back(gethash(a,b));
      hset.insert(gethash(a,b));
   }
   
   for(int q = 0; q < n; q++){
         
      long long curx = getx(array[q]);
      long long cury = gety(array[q]);
         
         
      long long dist = 1;
      long long answerx = -1;
      long long answery = -1;
      while(answerx == -1){
            //check all points on that distance
            
         long long checkx = curx;
         long long checky = cury+dist;
            
         for(int side = 0; side < 4; side++){
            for(int k = 0; k < dist; k++){
               if(hset.find(gethash(checkx,checky)) == hset.end()){
                  answerx = checkx;
                  answery = checky;
                  break;
               }
                  
               checkx += dx[side];
               checky += dy[side];
            }
            if(answerx != -1) break;
         }
            
         dist++;
      }
         
      cout << answerx << " " << answery << endl;
   }

   
   
   
   return 0;
}