#include <bits/stdc++.h>

using namespace std;


int trysum(int tsum, multiset<int> mset){
   //cout << tsum << endl;
   int deleted = INT_MIN;
   while(mset.size() > 0){
      auto b = mset.begin();
      int bi = *b;
      //cout << bi << " " << mset.size() << endl;
      mset.erase(b);
      
      auto i2 = mset.find(tsum - bi);
      if(i2 == mset.end()){
         if(deleted != INT_MIN){
            return -1;
         }
         deleted = tsum-bi;
         continue;
      }
      
      mset.erase(i2);
   }
   
   if(deleted >= 1) return deleted;
   return -1;
}


int solve(int n, const multiset<int>& mset){
   if(n == 1){
      return 1;
   }
   
   auto bigi = mset.rbegin();
   int big = *bigi;
   bigi++;
   int big2 = *bigi;
   
   auto smalli = mset.begin();
   int small = *smalli;
   smalli++;
   int small2 = *smalli;
   
   //add below
   int belowsum = small + big2;
   int below = trysum(belowsum,mset);
   if(below != -1){
      return below;
   }
   
   //add middle
   int middlesum = small + big;
   int middle = trysum(middlesum,mset);
   if(middle != -1){
      return middle;
   }
   
   //add above
   int abovesum = small2 + big;
   int above = trysum(abovesum,mset);
   if(above != -1){
      return above;
   }
   
   return -1;
   
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   ifstream fin ("two_apples_a_day_input.txt");
   ofstream fout ("two_apples_a_day_output.txt");
   
   int t;
   fin >> t;
   
   for(int q = 1; q <= t; q++){
      int n;
      fin >> n;
      
      multiset<int> mset;
      for(int k = 0; k < 2*n-1; k++){
         int i;
         fin >> i;
         mset.insert(i);
      }
      
      int answer = solve(n,mset);
      fout << "Case #" << q << ": " << answer << endl;
      
   }
   
   
   
   
   
   return 0;
}