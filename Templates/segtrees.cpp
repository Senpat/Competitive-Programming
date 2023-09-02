#include <bits/stdc++.h>

using namespace std;

//range assign, range sum query
//to call, v = 0, l = 0, r = size-1
//l,r and ql,qr are inclusive
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


//2D segment tree
//point assign, range sum query
class Segtree2D{

   private:
      vector<vector<int>> a;
      int xn;
      int yn;
      
   public:
      Segtree2D(int n, int m){
         a = vector<vector<int>>(4*n,vector<int>(4*m));
         xn = n;
         yn = m;
      }
      
      void build2(int vx, int lx, int rx, int vy, int ly, int ry, const vector<vector<int>>& board){
         if(ly == ry){
            if(lx == rx){
               a[vx][vy] = board[lx][ly];
            } else {
               a[vx][vy] = a[2*vx+1][vy] + a[2*vx+2][vy];
            }
         } else {
            int mid = (ly+ry)/2;
            
            build2(vx,lx,rx,2*vy+1,ly,mid,board);
            build2(vx,lx,rx,2*vy+2,mid+1,ry,board);
            
            a[vx][vy] = a[vx][2*vy+1] + a[vx][2*vy+2];
         }
      }
      
      void build(int vx, int lx, int rx, const vector<vector<int>>& board){
         if(lx != rx){
            int mid = (lx+rx)/2;
            
            build(vx*2+1,lx,mid,board);
            build(vx*2+2,mid+1,rx,board);
         }
         build2(vx,lx,rx,0,0,yn-1,board);
      }
      
      void assign2(int vx, int lx, int rx, int vy, int ly ,int ry, int qy, int x){
         if(ly == ry){
            if(lx == rx){
               a[vx][vy] = x;
            } else {
               a[vx][vy] = a[vx*2+1][vy] + a[vx*2+2][vy];
            }
         } else {
            int mid = (ly+ry)/2;
            
            if(qy <= mid){
               assign2(vx,lx,rx,2*vy+1,ly,mid,qy,x);
            } else {
               assign2(vx,lx,rx,2*vy+2,mid+1,ry,qy,x);
            }
            
            a[vx][vy] = a[vx][2*vy+1] + a[vx][2*vy+2];
         }
      }
      
      void assign(int vx, int lx, int rx, int qx, int qy, int x){
         if(lx != rx){
            int mid = (lx+rx)/2;
            
            if(qx <= mid){
               assign(vx*2+1,lx,mid,qx,qy,x);
            } else {
               assign(vx*2+2,mid+1,rx,qx,qy,x);
            }
         }
         assign2(vx,lx,rx,0,0,yn-1,qy,x);
      }
      
      
      int query2(int vx, int vy, int ly, int ry, int qy1, int qy2){
         if(ly >= qy1 && ry <= qy2){
            return a[vx][vy];
         } else if(ry < qy1 || ly > qy2){
            return 0;
         } else {
            int mid = (ly+ry)/2;
            
            return query2(vx,vy*2+1,ly,mid,qy1,qy2) + 
                   query2(vx,vy*2+2,mid+1,ry,qy1,qy2);
         }
      }
      
      //sum on submatrix
      int query(int vx, int lx, int rx, int qx1, int qy1, int qx2, int qy2){
         if(lx >= qx1 && rx <= qx2){
            return query2(vx,0,0,yn-1,qy1,qy2);
         } else if(rx < qx1 || lx > qx2){
            return 0;
         } else {
            int mid = (lx+rx)/2;
            
            return query(vx*2+1,lx,mid,qx1,qy1,qx2,qy2) + 
                   query(vx*2+2,mid+1,rx,qx1,qy1,qx2,qy2);
         }
      }
      
      


};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   
   
   return 0;
}