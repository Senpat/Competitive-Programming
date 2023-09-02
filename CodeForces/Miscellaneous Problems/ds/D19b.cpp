#include <bits/stdc++.h>

using namespace std;

struct Query{
   string s;
   int x;
   int y;
};

class Segtree{

   private:
   vector<int> a;       //stores max on segment
   
   public:
   Segtree(int size){
      a = vector<int>(4*size);
   }
   
   void assign(int v, int l, int r, int i, int x){
      if(l == r){
         a[v] = x;
      } 
      else {
         int mid = (l+r)/2;
            
         if(i <= mid){
            assign(2*v+1,l,mid,i,x);
         } 
         else {
            assign(2*v+2,mid+1,r,i,x);
         }
            
         a[v] = max(a[2*v+1],a[2*v+2]);
      }
   }
   
   //returns leftmost index > i whose element is > x
   int query(int v, int l, int r, int i, int x){
      if(r <= i || a[v] <= x) 
         return -1;
      else if(l == r){
         if(a[v] > x) 
            return l;
         else 
            return -1;
      } 
      else {
         int mid = (l+r)/2;
            
         int left = query(2*v+1,l,mid,i,x);
         if(left != -1) 
            return left;
            
         return query(2*v+2,mid+1,r,i,x);
      }
   }
   
};

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


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int q;
   cin >> q;
   
   
   unordered_set<int> hset;
   vector<Query> queries(q);
   for(int k = 0; k < q; k++){
      string s;
      int x,y;
      cin >> s >> x >> y;
      queries[k] = {s,x,y};
      
      hset.insert(x);
   
   }
   
   vector<int> alist;
   for(int i : hset){
      alist.push_back(i);
   }
   
   sort(alist.begin(),alist.end());
   
   unordered_map<int,int,custom_hash> compress;
   compress.reserve(1024);
   compress.max_load_factor(0.25);
   
   for(int k = 0; k < alist.size(); k++){
      compress[alist[k]] = k;
   }
   
   int N = 200005;
   vector<set<int>> tsets(N,set<int>());
   
   Segtree segtree(N);
   
   for(int t = 0; t < q; t++){
      int x = compress[queries[t].x];
      int y = queries[t].y;
      
      if(queries[t].s[0] == 'a'){
         tsets[x].insert(y);
         segtree.assign(0,0,N-1,x,*tsets[x].rbegin());
      } else if(queries[t].s[0] == 'r'){
         tsets[x].erase(y);
         int curq = 0;
         if(tsets[x].size() > 0){
            curq = *tsets[x].rbegin();
         }
         
         segtree.assign(0,0,N-1,x,curq);
      } else {
         int i = segtree.query(0,0,N-1,x,y);
         
         if(i == -1){
            cout << "-1\n";
         } else {
            int qy = *tsets[i].upper_bound(y);
            cout << alist[i] << " " << qy << "\n";
         }
      }
   }
   
   
   return 0;
}