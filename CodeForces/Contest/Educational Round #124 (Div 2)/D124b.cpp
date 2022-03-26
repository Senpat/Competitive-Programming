#include <bits/stdc++.h>

using namespace std;

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

vector<set<int>> hsets;

int dx[] = {-1,1,1,-1};
int dy[] = {-1,-1,1,1};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   for(int k = 0; k < 200005; k++){
      set<int> curset;
      hsets.push_back(curset);
   }
   
   int n;
   cin >> n;
   
   vector<int> arrayx;
   vector<int> arrayy;
   
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      
      arrayx.push_back(a);
      arrayy.push_back(b);
      
      hsets[a].insert(b);
   }
   
   for(int q = 0; q < n; q++){
      
      int curx = arrayx[q];
      int cury = arrayy[q];
      
      int dist = 1;
      int answerx = -1;
      int answery = -1;
      while(answerx == -1){
            //check all points on that distance
            
         int checkx = curx;
         int checky = cury+dist;
            
         for(int side = 0; side < 4; side++){
            for(int k = 0; k < dist; k++){
               if(hsets[checkx].find(checky) == hsets[checkx].end()){
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