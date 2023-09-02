#include <bits/stdc++.h>

using namespace std;

//point assign, range min query
class Segtree{
 
   private:
      vector<int> a;
   
   public:
      Segtree(int size){
         a = vector<int>(4*size, INT_MAX);
      }
      
      void build(int v, int l, int r, const vector<int>& array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = min(a[2*v+1],a[2*v+2]);
         }
      }
      
      void assign(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               assign(2*v+1,l,mid,i,x);
            } else {
               assign(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = min(a[2*v+1],a[2*v+2]);
         }
      
      }
      
      //min
      long long query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return INT_MAX;
         } else {
            int mid = (l+r)/2;
            
            return min(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
         }
      }
 
};


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   Segtree segtreeinc(n);
   Segtree segtreedec(n);
   vector<int> arrayinc(n);
   vector<int> arraydec(n);
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      arrayinc[k] = i+k;
      arraydec[k] = i+n-k-1;
   }
   
   segtreeinc.build(0,0,n-1,arrayinc);
   segtreedec.build(0,0,n-1,arraydec);
   
   for(int t = 0; t < q; t++){
      int qt,i;
      cin >> qt >> i;
      i--;
      if(qt == 1){
         int x;
         cin >> x;
         
         segtreeinc.assign(0,0,n-1,i,x+i);
         segtreedec.assign(0,0,n-1,i,x+n-i-1);
      } else {
         int answer = min(segtreeinc.query(0,0,n-1,i,n-1) - i,segtreedec.query(0,0,n-1,0,i) - (n-i-1));
         cout << answer << endl;
      }
   }
   
   
   
   
   return 0;
}