#include <bits/stdc++.h>

using namespace std;



//range update, range max (and min index) query
//to call, v = 0, l = 0, r = size-1
//l,r and ql,qr are inclusive
class Segtree{

   private:
      vector<long long> ops;
      vector<long long> a;
      vector<int> ind;
   
      void propagate(int v, int l, int r){
         if(ops[v] == 0LL) return;
         
         ops[2*v+1] += ops[v];
         a[2*v+1] += ops[v];
         
         ops[2*v+2] += ops[v];
         a[2*v+2] += ops[v];
         
         ops[v] = 0LL;
      }
         
   
   public:
      Segtree(int size){
         ops = vector<long long>(4*size,0LL);
         a = vector<long long>(4*size);
         ind = vector<int>(4*size);
      }
      
      void build(int v, int l, int r, const vector<long long>& array){
         if(l == r){
            a[v] = array[l];
            ind[v] = l;
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            if(a[2*v+1] >= a[2*v+2]) ind[v] = ind[2*v+1];
            else ind[v] = ind[2*v+2];
            
            a[v] = max(a[2*v+1],a[2*v+2]);
         }
      }
      
      void update(int v, int l, int r, int ql, int qr, long long x){
         if(l >= ql && r <= qr){
            ops[v] += x;
            a[v] += x;
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v,l,r);
            
            int mid = (l+r)/2;
            
            update(2*v+1,l,mid,ql,qr,x);
            update(2*v+2,mid+1,r,ql,qr,x);
            
            if(a[2*v+1] >= a[2*v+2]) ind[v] = ind[2*v+1];
            else ind[v] = ind[2*v+2];
            a[v] = max(a[2*v+1],a[2*v+2]);
         }
      
      }
      
      //max
      pair<long long,int> query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return {a[v],ind[v]};
         } else if(r < ql || l > qr){
            return {0LL,-1};
         } else {
            propagate(v,l,r);
            
            int mid = (l+r)/2;
            
            auto pl = query(2*v+1,l,mid,ql,qr);
            auto pr = query(2*v+2,mid+1,r,ql,qr);
            
            if(pl.first <= pr.first) return pl;
            return pr;
            
         }
      }

};




int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      
      vector<long long> array(n);
      for(int k = 0; k < n; k++){
         cin >> array[k];
         array[k] += (long long)(k+1);
      }
      
      Segtree segtree(n);
      segtree.build(0,0,n-1,array);
      
      const long long ninf = -10000000000000000LL;
      vector<long long> answer;
      for(int k = 0; k < n; k++){
         auto p = segtree.query(0,0,n-1,0,n-1);
         
         int i = p.second;
         answer.push_back(p.first);
         
         segtree.update(0,0,n-1,i,i,ninf);
         if(i+1 < n){
            segtree.update(0,0,n-1,i+1,n-1,-1LL);
         }
      }
      
      for(int k = 0; k < n; k++){
         cout << answer[k] << " ";
      }
      cout << endl;
   }
   
   
   return 0;
}