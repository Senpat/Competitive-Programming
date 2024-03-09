#include <bits/stdc++.h>
//based on P2.java
//also tle (O(n^2 logn))
using namespace std;

const long long MOD = 1000000007LL;

//range update, point query
class Segtree{
   private:
   vector<long long> ops;
   
   public:
   Segtree(int size) : ops(4*size,0LL) {}
   
   void propagate(int v){
      ops[2*v+1] += ops[v];
      if(ops[2*v+1] >= MOD) ops[2*v+1] -= MOD;
      ops[2*v+2] += ops[v];
      if(ops[2*v+2] >= MOD) ops[2*v+2] -= MOD;
      ops[v] = 0LL;
   }
   
   void update(int v, int l, int r, int ql, int qr, long long x){
      //cout << v << " " << l << " " << r << endl;
      if(qr < ql) return;
      if(l >= ql && r <= qr){
         ops[v] += x;
         if(ops[v] >= MOD) ops[v] -= MOD;
      } else if(qr < l || r < ql){
         return;
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         update(2*v+1,l,mid,ql,qr,x);
         update(2*v+2,mid+1,r,ql,qr,x);
      }
   }
   
   long long query(int v, int l, int r, int x){
      if(l == r){
         return ops[v];
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         if(x <= mid) return query(2*v+1,l,mid,x);
         else return query(2*v+2,mid+1,r,x);
      }
   }
};


        //from geeksforgeeks
long long modInverse(long long a, long long m) 
{ 
   long long m0 = m; 
   long long y = 0;
   long long x = 1; 
     
   if (m == 1LL) 
      return 0LL; 
     
   while (a > 1LL) 
   { 
           // q is quotient 
      long long q = a / m; 
      long long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
      m = a % m;
      a = t; 
      t = y; 
     
           // Update y and x 
      y = x - q * y; 
      x = t; 
   } 
     
       // Make x positive 
   if (x < 0LL) 
      x += m0; 
   return x; 
}  

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<long long> array(n+1);
   vector<long long> psum(n+1,0LL);
   for(int k = 1; k <= n; k++){
      cin >> array[k];
      psum[k] = psum[k-1] + array[k];
   }
   
   vector<Segtree> h(n+1,Segtree(n+1));
   vector<Segtree> v(n+1,Segtree(n+1));
   
   //split[l][r] = last index x such that sum of [x,r] is more than half of the sum of [l,r]
   vector<vector<int>> split(n+1,vector<int>(n+1,0));
   for(int r = 2; r <= n; r++){
      long long lsum = array[r];
      long long xsum = 0LL;
      int l = r;
      for(int x = r; x >= 1; x--){
         xsum += array[x];
            
         while(l >= 1 && xsum*2L >= lsum){
            split[l][r] = x;
            l--;
            if(l >= 1) lsum += array[l];
         }
      }
   }
   
   //initial state
   h[1].update(0,0,n,n,n,1LL);
   
   for(int len = n; len >= 2; len--){
      //number of splits is len-1
      long long invsplits = modInverse(len-1,MOD);
         
      for(int l = 1; l+len-1 <= n; l++){
         int r = l+len-1;
            
         long long pin = h[l].query(0,0,n,r) + v[r].query(0,0,n,l);
         if(pin >= MOD) pin -= MOD;
            
         long long pnext = (pin * invsplits + MOD)%MOD;
         
         h[l].update(0,0,n,split[l][r],r-1,pnext);
         v[r].update(0,0,n,l+1,split[l][r],pnext);
      }
   }
      
   for(int k = 1; k <= n; k++){
      long long answer = h[k].query(0,0,n,k) + v[k].query(0,0,n,k);
      if(answer >= MOD) answer -= MOD;
      cout << answer << "\n";
   }
   
   return 0;
}