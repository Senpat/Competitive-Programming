#include <bits/stdc++.h>

using namespace std;

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
   
   int n,q;
   cin >> n >> q;
   
   vector<vector<int>> board(n,vector<int>(n));
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < n; j++){
         if(s[j] == '*'){
            board[k][j] = 1;
         }
      }
   }
   
   Segtree2D segtree(n,n);
   segtree.build(0,0,n-1,board);
   
   for(int t = 0; t < q; t++){
      int qt,x1,y1;
      cin >> qt >> x1 >> y1;
      
      x1--;
      y1--;
      if(qt == 1){
         board[x1][y1] = 1-board[x1][y1];
         segtree.assign(0,0,n-1,x1,y1,board[x1][y1]);
      } else {
         int x2,y2;
         cin >> x2 >> y2;
         x2--;
         y2--;
         
         cout << segtree.query(0,0,n-1,x1,y1,x2,y2) << endl;
      }
   }
   
   
   
   return 0;
}