#include <bits/stdc++.h>

using namespace std;

//range += update, range min query, range max query
class Segtree{
   private:
   vector<int> op;
   vector<int> amin;
   vector<int> amax;
   
   public:
   Segtree(int sz){
      op = vector<int>(4*sz);
      amin = vector<int>(4*sz);
      amax = vector<int>(4*sz);
   }
   
   //no need for build
   
   void propagate(int v){
      op[2*v+1] += op[v];
      op[2*v+2] += op[v];
      amin[2*v+1] += op[v];
      amin[2*v+2] += op[v];
      amax[2*v+1] += op[v];
      amax[2*v+2] += op[v];
      
      op[v] = 0;
   }
   
   void update(int v, int l, int r, int ql, int qr, int x){
      if(l >= ql && r <= qr){
         op[v] += x;
         amin[v] += x;
         amax[v] += x;
      } else if(l > qr || r < ql){
         return;
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         update(2*v+1,l,mid,ql,qr,x);
         update(2*v+2,mid+1,r,ql,qr,x);
         
         amin[v] = min(amin[2*v+1],amin[2*v+2]);
         amax[v] = max(amax[2*v+1],amax[2*v+2]);
      }
      
   }
   
   //returns a pair containing the min and max
   pair<int,int> query(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         return make_pair(amin[v],amax[v]);
      } else if(l > qr || r < ql){
         return make_pair(INT_MAX,INT_MIN);
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         auto pl = query(2*v+1,l,mid,ql,qr);
         auto pr = query(2*v+2,mid+1,r,ql,qr);
         
         return make_pair(min(pl.first,pr.first),max(pl.second,pr.second));
      }
   }
   
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   Segtree segtree(n);
   
   for(int k = 0; k < n; k++){
      int i,x;
      cin >> i >> x, i--;
      
      if(x == 1){
         segtree.update(0,0,n-1,0,i,1);
      } else {
         segtree.update(0,0,n-1,0,i,-1);
      }
      
      auto p = segtree.query(0,0,n-1,0,n-1);
      //cout << p.first << " " << p.second << endl;
      if(p.first >= 0){
         cout << ">\n";
      } else if(p.second <= 0){
         cout << "<\n";
      } else {
         cout << "?\n";
      }
      
   }
   
   return 0;
}