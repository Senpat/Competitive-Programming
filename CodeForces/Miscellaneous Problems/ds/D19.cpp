#include <bits/stdc++.h>
//based on D19.java, also tle
using namespace std;

struct Query{
   string s;
   int x;
   int y;
};

class Segtree{

   private:
   vector<set<pair<int,int>>> a;
   const pair<int,int> no = {-1,-1};
   
   public:
   Segtree(int size){
      for(int k = 0; k < 4*size; k++){
         set<pair<int,int>> temp;
         a.push_back(temp);
      }
      
   }
      
   void update(int v, int l, int r, const pair<int,int>& p, bool add){
      if(add){
         a[v].insert(p);
      } 
      else {
         a[v].erase(p);
      }
         
      if(l != r){
         int mid = (l+r)/2;
            
         if(p.second <= mid){
            update(2*v+1,l,mid,p,add);
         } 
         else {
            update(2*v+2,mid+1,r,p,add);
         }
      }
   }
      
      //returns the Point that is leftmost and bottommost after x, and y in the range [ql,qr]
   pair<int,int> query(int v, int l, int r, int ql, int qr, int x){
      if(l >= ql && r <= qr){
         auto ret = a[v].upper_bound(make_pair(x,INT_MAX));
         if(ret == a[v].end()){
            return no;
         } 
         else{
            return *ret;
         }
      }
      else if(r < ql || l > qr){
         return no;
      }
      else {
         int mid = (l+r)/2;
            
         auto pl = query(2*v+1,l,mid,ql,qr,x);
         auto pr = query(2*v+2,mid+1,r,ql,qr,x);
            
         //if one is null, return the other
         if(pl.first == -1) 
            return pr;
         if(pr.first == -1) 
            return pl;
            
         //both are not null, prioritize pl
         if(pl.first <= pr.first) 
            return pl;
         return pr;
      }
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
      
      hset.insert(y);
   
   }
   
   vector<int> alist;
   for(int i : hset){
      alist.push_back(i);
   }
   
   sort(alist.begin(),alist.end());
   
   map<int,int> compress;
   //compress.reserve(1024);
   //compress.max_load_factor(0.25);
   
   for(int k = 0; k < alist.size(); k++){
      compress[alist[k]] = k;
   }
   
   int N = 200005;
   Segtree segtree(N);
   
   for(int t = 0; t < q; t++){
      int x = queries[t].x;
      int y = queries[t].y;
      if(queries[t].s[0] == 'a'){
         segtree.update(0,0,N-1,make_pair(x,compress[y]),true);
      } else if(queries[t].s[0] == 'r'){
         segtree.update(0,0,N-1,make_pair(x,compress[y]),false);
      } else {
         auto p = segtree.query(0,0,N-1,compress[y]+1,N-1,x);
         if(p.first == -1){
            cout << "-1\n";
         } else {
            cout << p.first << " " << alist[p.second] << "\n";
         }
      }
   }
   
   return 0;
}