#include <bits/stdc++.h>

using namespace std;

struct Query{
   public:
   int qt;
   int i;
   int c;
   long long v;
   
   vector<int> colorset;
   
   Query(int qt_, int i_, int c_) : qt(qt_), i(i_), c(c_){}    //query 1
   Query(int qt_, int i_, long long v_) : qt(qt_), i(i_), v(v_){}    //query 2
   Query(int qt_, int i_, vector<int> colorset_) : qt(qt_), i(i_), colorset(colorset_){}        //query 3
};

template<class T>
class BIT{
   public:
   int n;
   vector<T> bits;
   map<int,int> compress;
   BIT(int n_) : n(n_){
      bits = vector<T>(n_+1);
      compress = map<int,int>();
   }
   
   void update2(int i, T x){
      //i guaranteed to exist in compress
      update(compress[i],x);
   }
   void update(int i, T x){
      for(; i <= n; i += i&-i){
         bits[i] += x;
      }
   }
   
   T psum2(int i){
      //get floor of i
      auto it = compress.upper_bound(i);
      if(it == compress.begin()) return 0;
      it--;
      return psum(it->second);
   }
   
   T psum(int i){
      T ret = 0;
      for(; i > 0; i -= i&-i){
         ret += bits[i];
      }
      return ret;
   }
   
};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int qq = 0; qq < t; qq++){
      int n,q;
      cin >> n >> q;
      
      //for every color stores the indeces where it appears in the queries
      vector<unordered_set<int>> usets(n+1,unordered_set<int>());
      
      vector<int> c(n+1);
      for(int k = 1; k <= n; k++){
         cin >> c[k];
         usets[c[k]].insert(k);
      }
      vector<long long> v(n+1);
      BIT<long long> bit(n);
      for(int k = 1; k <= n; k++){
         cin >> v[k];
         bit.update(k,v[k]);
      }
      
      vector<Query> queries(q,Query(-1,-1,-1));
      for(int qi = 0; qi < q; qi++){
         int qt;
         cin >> qt;
         if(qt == 1){
            int i,ci;
            cin >> i >> ci;
            queries[qi] = Query(qt,i,ci);
            usets[ci].insert(i);
         } else if(qt == 2){
            int i;
            long long vi;
            cin >> i >> vi;
            queries[qi] = Query(qt,i,vi);
         } else if(qt == 3){
            int i, qn;
            cin >> i >> qn;
            vector<int> colorset(qn);
            for(int k = 0; k < qn; k++){
               cin >> colorset[k];
            }
            queries[qi] = Query(qt,i,move(colorset));
         }
      }
      
      //cout << "read in queries" << endl;
      
      vector<BIT<int>> bits;
      bits.push_back(BIT<int>(1));         //1-indexed
      for(int k = 1; k <= n; k++){
         BIT<int> cur(usets[k].size());
         vector<int> pos;
         for(int i : usets[k]){
            pos.push_back(i);
         }
         sort(pos.begin(),pos.end());
         for(int k = 0; k < pos.size(); k++){
            cur.compress[pos[k]] = k+1;
         }
         
         bits.push_back(cur);
      }
      
      //cout << "fill usets" << endl;
      
      //initial values of the bits
      for(int k = 1; k <= n; k++){
         bits[c[k]].update2(k,1);
      }
      
      //cout << "fill initial bits" << endl;
      
      for(int qi = 0; qi < q; qi++){
         Query qu = queries[qi];
         if(qu.qt == 1){
            bits[c[qu.i]].update2(qu.i,-1);
            bits[qu.c].update2(qu.i,1);
            c[qu.i] = qu.c;
         } else if(qu.qt == 2){
            bit.update(qu.i,qu.v - v[qu.i]);
            v[qu.i] = qu.v;
         } else if(qu.qt == 3){
            int cn = qu.colorset.size();
            int l,r;
            //binary search right border
            l = qu.i;
            r = n;
            int rb = qu.i;
            
            while(l <= r){
               int mid = l + (r-l)/2;
               
               int tot = 0;
               for(int k = 0; k < cn; k++){
                  tot += bits[qu.colorset[k]].psum2(mid) - bits[qu.colorset[k]].psum2(qu.i-1);
               }
               
               if(tot == mid-qu.i+1){
                  l = mid+1;
                  rb = mid;
               } else {
                  r = mid-1;
               }
            }
            
            //binary search left border
            l = 1;
            r = qu.i;
            int lb = qu.i;
            while(l <= r){
               int mid = l + (r-l)/2;
               
               int tot = 0;
               for(int k = 0; k < cn; k++){
                  tot += bits[qu.colorset[k]].psum2(qu.i) - bits[qu.colorset[k]].psum2(mid-1);
               }
               
               if(tot == qu.i-mid+1){
                  r = mid-1;
                  lb = mid;
               } else {
                  l = mid+1;
               }
            }
            
            long long answer = bit.psum(rb) - bit.psum(lb-1);
            cout << answer << endl;
            
         }
      }
      
   }
   
   
   
   return 0;
}