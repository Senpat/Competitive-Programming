#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;
const long long base = 29LL;
const long long ibase = 758620695LL;

//point assign and range sum query
class Segtree{
   
   private:
      vector<long long> a;
   
   public:
      Segtree(int size){
         a = vector<long long>(4*size);
      }
      
      void build(int v, int l, int r, const vector<long long>& array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = (a[2*v+1] + a[2*v+2] + MOD)%MOD;
         }
      }
      
      void assign(int v, int l, int r, int i, long long x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               assign(2*v+1,l,mid,i,x);
            } else {
               assign(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = (a[2*v+1] + a[2*v+2] + MOD)%MOD;
         }
      }
      
      //sum
      long long query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0LL;
         } else {
            int mid = (l+r)/2;
            
            long long left = query(2*v+1,l,mid,ql,qr);
            long long right = query(2*v+2,mid+1,r,ql,qr);
            
            return (left + right + MOD)%MOD;
         }
      }

};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 200005;
   vector<long long> pow(N);
   vector<long long> ipow(N);
   pow[0] = 1LL;
   ipow[0] = 1LL;
   for(int k = 1; k < N; k++){
      pow[k] = (pow[k-1] * base + MOD)%MOD;
      ipow[k] = (ipow[k-1] * ibase + MOD)%MOD;
   }
   
   
   
   int n,q;
   
   cin >> n >> q;
   
   string s;
   cin >> s;
   
   vector<long long> hash1(n);
   vector<long long> hash2(n);
   for(int k = 0; k < n; k++){
      hash1[k] = ((long long)(s[k]-'a') * pow[k] + MOD)%MOD;
      hash2[k] = ((long long)(s[k]-'a') * pow[n-k-1] + MOD)%MOD;
   }
   
   Segtree segl(n);
   Segtree segr(n);
   
   segl.build(0,0,n-1,hash1);
   segr.build(0,0,n-1,hash2);
   
   
   
   for(int t = 0; t < q; t++){
      int qt;
      cin >> qt;
      
      if(qt == 1){
         int i;
         char c;
         cin >> i >> c;
         i--;
         
         long long lhash = ((long long)(c-'a') * pow[i] + MOD)%MOD;
         segl.assign(0,0,n-1,i,lhash);
         long long rhash = ((long long)(c-'a') * pow[n-i-1] + MOD)%MOD;
         segr.assign(0,0,n-1,i,rhash);
         
      } else {
         int l,r;
         cin >> l >> r;
         l--;
         r--;
         
         int mid = (l+r)/2;
         
         long long lhash = (segl.query(0,0,n-1,l,mid) * ipow[l] + MOD)%MOD;
         int rl = mid;
         if((r-l+1)%2 == 0) rl++;
         long long rhash = (segr.query(0,0,n-1,rl,r) * ipow[n-r-1] + MOD)%MOD;
         
         if(lhash == rhash){
            cout << "YES\n";
         } else {
            cout << "NO\n";
         }
      }
   }
   
   
   
   
   
   return 0;
}