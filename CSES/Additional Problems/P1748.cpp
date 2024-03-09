#include <bits/stdc++.h>

using namespace std;

int n;

long long MOD = 1000000007LL;

vector<long long> bits;

void update(int i, int x){
   for(; i <= n; i += i&-i){
      //System.out.println(i);
      bits[i] = (bits[i] + x + MOD)%MOD;
   }

}

long long psum(int i){
   long long cursum = 0LL;
   for(; i > 0; i -= i&-i){
      //System.out.println(i);
      cursum = (cursum + bits[i] + MOD)%MOD;
   }
   return cursum;

}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n;
   
   vector<int> raw(n);
   unordered_set<int> uset;
   for(int k = 0; k < n; k++){
      cin >> raw[k];
      uset.insert(raw[k]);
   }
   
   vector<int> order;
   for(int i : uset){
      order.push_back(i);
   }
   sort(order.begin(),order.end());
   
   unordered_map<int,int> compress;
   compress.reserve(1024);
   compress.max_load_factor(0.25);
   
   for(int k = 0; k < order.size(); k++){
      compress[order[k]] = k+1;
   }
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      array[k] = compress[raw[k]];
   }
   
   bits = vector<long long>(n+1,0LL);
   
   long long answer = 0LL;
   for(int k = 0; k < n; k++){
      long long cur = psum(array[k]-1) + 1LL;
      answer = (answer + cur + MOD)%MOD;
      
      update(array[k],cur);
   }
   
   cout << answer << endl;
   
   
   return 0;
}