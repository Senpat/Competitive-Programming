#include <bits/stdc++.h>

using namespace std;

class Segtree{

   private:
      const long long NOOP = LLONG_MAX; 
      vector<long long> ops;
      vector<long long> a;
   
      void propagate(int v, int l, int r){
         if(ops[v] == NOOP) return;
         
         int mid = (l+r)/2;
         
         ops[2*v+1] = ops[v];
         long long lenl = (long long)(mid-l+1);
         a[2*v+1] = lenl * ops[v];
         
         ops[2*v+2] = ops[v];
         long long lenr = (long long)(r-(mid+1)+1);
         a[2*v+2] = lenr * ops[v];
         
         ops[v] = NOOP;
      }
         
   
   public:
      Segtree(int size){
         ops = vector<long long>(4*size,NOOP);
         a = vector<long long>(4*size);
      }
      
      
      void assign(int v, int l, int r, int ql, int qr, long long x){
         if(l >= ql && r <= qr){
            ops[v] = x;
            a[v] = x * (long long)(r-l+1);
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v,l,r);
            
            int mid = (l+r)/2;
            
            assign(2*v+1,l,mid,ql,qr,x);
            assign(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      
      }
      
      //sum
      long long query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0LL;
         } else {
            propagate(v,l,r);
            
            int mid = (l+r)/2;
            
            return query(2*v+1,l,mid,ql,qr) + query(2*v+2,mid+1,r,ql,qr);
         }
      }

};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   Segtree segtree(n);
   
   for(int t = 0; t < q; t++){
      int qt,l,r;
      cin >> qt >> l >> r;
      if(qt == 1){
         long long x;
         cin >> x;
         segtree.assign(0,0,n-1,l,r-1,x);
      } else {
         long long answer = segtree.query(0,0,n-1,l,r-1);
         cout << answer << endl;
      }
   }
   
   
   return 0;
}