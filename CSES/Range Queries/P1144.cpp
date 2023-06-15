#include <bits/stdc++.h>

using namespace std;

struct Query{
   char qt;
   int i1;
   int i2;
};

//fenwick tree
int N = 1000000;
int bits[1000001];
void update(int i, int x){
   for(; i <= N; i += i&-i){
      bits[i] += x;
   }
}

int psum(int i){
   int sum = 0;
   for(; i > 0; i -= i&-i){
      sum += bits[i];
   }
   return sum;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   vector<int> array(n);
   vector<int> nums;
   unordered_set<int> hset;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      
      if(hset.find(array[k]) == hset.end()){
         nums.push_back(array[k]);
         hset.insert(array[k]);
      }
   }
   
   vector<Query> queries(q);
   for(int k = 0; k < q; k++){
      char qt;
      int i1,i2;
      cin >> qt >> i1 >> i2;
      queries[k] = {qt,i1,i2};
      
      
      if(qt == '?' && hset.find(i1) == hset.end()){
         nums.push_back(i1);
         hset.insert(i1);
      }
      
      if(hset.find(i2) == hset.end()){
         nums.push_back(i2);
         hset.insert(i2);
      }
   }
   
   sort(nums.begin(),nums.end());
   
   unordered_map<int,int> compress;
   compress.reserve(1024);
   compress.max_load_factor(0.25);
   
   for(int k = 0; k < nums.size(); k++){
      compress[nums[k]] = k+1;   
   }
   
   for(int k = 0; k < n; k++){
      array[k] = compress[array[k]];
      update(array[k],1);
   }
   
   for(Query curq : queries){
      if(curq.qt == '!'){
         int index = curq.i1-1;
         int x = compress[curq.i2];
         update(array[index],-1);
         update(x,1);
         array[index] = x;
      } else {
         int l = compress[curq.i1];
         int r = compress[curq.i2];
         
         int answer = psum(r) - psum(l-1);
         cout << answer << endl;
         
      }
   }
   
   return 0;
}