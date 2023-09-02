#include <bits/stdc++.h>

using namespace std;

//Persistent seg tree

struct Vertex{
   Vertex* left;
   Vertex* right;
   long long sum;
   
   Vertex(long long x) : left(nullptr), right(nullptr), sum(x) {}
   Vertex(Vertex* l, Vertex* r) : left(l), right(r), sum(0LL) {
      if(l) sum += l->sum;
      if(r) sum += r->sum;
   }
};

Vertex* build(int l, int r, const vector<long long>& array){
   if(l == r){
      return new Vertex(array[l]);
   } else {
      int mid = (l+r)/2;
      
      return new Vertex(build(l,mid,array),build(mid+1,r,array));
   }
}

Vertex* assign(Vertex* v, int l, int r, int i, long long x){
   if(l == r){
      return new Vertex(x);
   } else {
      int mid = (l+r)/2;
      
      if(i <= mid){
         return new Vertex(assign(v->left,l,mid,i,x),v->right);
      } else {
         return new Vertex(v->left,assign(v->right,mid+1,r,i,x));
      }
   }
}

//sum
long long query(Vertex* v, int l, int r, int ql, int qr){
   if(l >= ql && r <= qr){
      return v->sum;
   } else if(r < ql || l > qr){
      return 0LL;
   } else {
      int mid = (l+r)/2;
      
      return query(v->left,l,mid,ql,qr) + query(v->right,mid+1,r,ql,qr);
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   int n,q;
   cin >> n >> q;
   
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   vector<Vertex*> roots;
   roots.push_back(build(0,n-1,array));
   
   for(int t = 0; t < q; t++){
      int qt;
      cin >> qt;
      
      if(qt == 1){
         int vi,i;
         long long x;
         cin >> vi >> i >> x;
         vi--;i--;
         
         Vertex* v = assign(roots[vi],0,n-1,i,x);
         roots[vi] = v;
      } else if(qt == 2){
         int vi,l,r;
         cin >> vi >> l >> r;
         vi--;l--;r--;
         
         long long answer = query(roots[vi],0,n-1,l,r);
         cout << answer << endl;
      } else if(qt == 3){
         int vi;
         cin >> vi;
         vi--;
         
         roots.push_back(roots[vi]);
      }
   }
   
   
   return 0;
}