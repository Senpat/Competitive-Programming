#include <bits/stdc++.h>

using namespace std;

struct range{
   int l;
   int r;
   int i;
};

//fenwick tree
int fn;

vector<int> bits;

void update(int i, int x){
   for(; i <= fn; i += i&-i){
      bits[i]+=x;
   }
}
   
int psum(int i){
   int cursum = 0;
   for(; i > 0; i -= i&-i){
      cursum += bits[i];
   }
   return cursum;

}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<pair<int,int>> array(n);
   unordered_set<int> uset;
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      array[k] = make_pair(a,b);
      
      uset.insert(a);
      uset.insert(b);
   }
   
   vector<int> vec;
   for(int i : uset){
      vec.push_back(i);
   }
   sort(vec.begin(),vec.end());
   
   unordered_map<int,int> compress;
   compress.reserve(1024);
   compress.max_load_factor(0.25);
   
   for(int k = 0; k < vec.size(); k++){
      compress[vec[k]] = k+1;
   }
   
   fn = compress.size();
   bits = vector<int>(fn+1,0);
   
   vector<vector<range>> start(fn+1,vector<range>());
   vector<vector<range>> endr(fn+1,vector<range>());
   
   for(int k = 0; k < n; k++){
      int l = compress[array[k].first];
      int r = compress[array[k].second];
      range cur = {l,r,k};
      start[l].push_back(cur);
      endr[r].push_back(cur);
   }
   
   vector<int> answer1(n);
   for(int k = 1; k <= fn; k++){
      for(range r : endr[k]){
         update(r.l,1);
      }
      
      for(range r : endr[k]){
         if(psum(r.r) > psum(r.l-1)+1){
            answer1[r.i] = 1;
         }
      }
   }
   
   for(int k = 0; k < n; k++){
      cout << answer1[k] << " ";
   }
   cout << endl;
   
   fill(bits.begin(),bits.end(),0);
   vector<int> answer2(n);
   for(int k = 1; k <= fn; k++){
      for(range r : start[k]){
         update(r.l,1);
      }
      
      for(range r : endr[k]){
         if(psum(r.l) > 1){
            answer2[r.i] = 1;
         }
      }
      
      for(range r : endr[k]){
         update(r.l,-1);
      }
   }
   
   for(int k = 0; k < n; k++){
      cout << answer2[k] << " ";
   }
   cout << endl;
   
   
   return 0;
}