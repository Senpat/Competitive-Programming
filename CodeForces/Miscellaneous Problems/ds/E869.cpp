#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;

class BIT{
   
   private:
   vector<long long> bits;
   int n;
   
   public:
   BIT(int size){
      n = size;
      bits = vector<long long>(n+1);
   }
   
   void update(int i, long long x){
      //cout << "starting update " << i << " " << n << endl;
      for(; i <= n; i += i&-i){
         //cout << "update " << i << " " << x << endl;
         bits[i] = ((bits[i]+x)%MOD + MOD)%MOD;
      }
   }
   
   long long psum(int i){
      long long cursum = 0LL;
      for(; i > 0; i -= i&-i){
         //cout << "add " << bits[i] << endl;
         cursum = ((cursum+bits[i])%MOD + MOD)%MOD;
      }
      return cursum;
   
   }
   
   
};

class Segtree2D{

   private:
   vector<BIT> a;
   int n;
   
   public:
   Segtree2D(int size){
      a = vector<BIT>(4*size, BIT(size));
   }
   
   
   void add(int vx, int lx, int rx, int qx, int qy, long long d){
      
      if(lx != rx){
         int mid = (lx+rx)/2;
         
         if(qx <= mid){
            add(2*vx+1,lx,mid,qx,qy,d);
         } else {
            add(2*vx+2,mid+1,rx,qx,qy,d);  
         }
      }
      //cout << "add " << lx << " " << rx << " " << qx << " " << qy << " " << d << endl;
      a[vx].update(qy,d);
   }
   
   //sum from (1,1) to (qx,qy)
   long long psum(int vx, int lx, int rx, int qx, int qy){
      if(rx <= qx){
         //cout << lx << " " << rx << " " << qx << " " << qy << " " << a[vx].psum(qy) << endl;
         return a[vx].psum(qy);
      } else if(lx > qx){
         return 0LL;
      } else {
         int mid = (lx+rx)/2;
         
         long long left = psum(2*vx+1,lx,mid,qx,qy);
         long long right = psum(2*vx+2,mid+1,rx,qx,qy);
         
         return ((left+right)%MOD+MOD)%MOD;
      }
   }
   
   
   

};

struct Rect{
   int x1;
   int y1;
   int x2;
   int y2;
   

};
bool operator==(const Rect& l, const Rect& r) {
   return l.x1 == r.x1 && l.y1 == r.y1 && l.x2 == r.x2 && l.y2 == r.y2;
}

struct HASH{
  size_t operator()(const Rect& r)const{
    auto h1 = hash<long long>()(((long long)r.x1)^(((long long)r.y1)<<32));
    auto h2 = hash<long long>()(((long long)r.x2)^(((long long)r.y2)<<32));
    return hash<long long>()((long long)h1 & (long long)h2);
  }
};




int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   //mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
   mt19937 rng(0);
   
   int n,m,q;
   cin >> n >> m >> q;
   
   int N = 2505;   
   Segtree2D segtree(N);
   
   unordered_map<Rect,long long, HASH> hmap;
   
   for(int t = 0; t < q; t++){
      int qt,x1,y1,x2,y2;
      cin >> qt >> x1 >> y1 >> x2 >> y2;
      
      
      if(qt == 1){
         long long r = ((long long)rng() + MOD)%MOD;
         //cout << r << " " << MOD-r << endl;
         Rect rect = {x1,y1,x2,y2};
         
         hmap[rect] = r;
         
         segtree.add(0,0,N-1,x1,y1,r);
         segtree.add(0,0,N-1,x2+1,y1,MOD-r);
         segtree.add(0,0,N-1,x1,y2+1,MOD-r);
         segtree.add(0,0,N-1,x2+1,y2+1,r);
      } else if(qt == 2){
         Rect rect = {x1,y1,x2,y2};
         long long r = hmap[rect];
         
         segtree.add(0,0,N-1,x1,y1,MOD-r);
         segtree.add(0,0,N-1,x2+1,y1,r);
         segtree.add(0,0,N-1,x1,y2+1,r);
         segtree.add(0,0,N-1,x2+1,y2+1,MOD-r);
      } else {
         long long a = segtree.psum(0,0,N-1,x1,y1);
         long long b = segtree.psum(0,0,N-1,x2,y2);
         //cout << a << " " << b << endl;
         if(a == b){
            cout << "Yes\n";
         } else {
            cout << "No\n";
         }
      }
      
   }
   
   
   return 0;
}