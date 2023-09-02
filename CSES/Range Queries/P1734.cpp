#include <bits/stdc++.h>

using namespace std;

class Segtree{
   
   private:
   vector<vector<int>> a;
   
   public:
   Segtree(int size){
      a = vector<vector<int>>(4*size,vector<int>());
   }
   
   void build(int v, int l, int r, const vector<int>& array){
      if(l == r){
         a[v].push_back(array[l]);
      } else {
         int mid = (l+r)/2;
         
         build(2*v+1,l,mid,array);
         build(2*v+2,mid+1,r,array);
         
         int ln = a[2*v+1].size();
         int rn = a[2*v+2].size();
         
         int li = 0;
         int ri = 0;
         while(li < ln && ri < rn){
            if(a[2*v+1][li] < a[2*v+2][ri]){
               a[v].push_back(a[2*v+1][li]);
               li++;
            } else {
               a[v].push_back(a[2*v+2][ri]);
               ri++;
            }
         }
         
         while(li < ln){
            a[v].push_back(a[2*v+1][li]);
            li++;
         }
         while(ri < rn){
            a[v].push_back(a[2*v+2][ri]);
            ri++;
         }
      }
   }
   
   int query(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         //see how many values in a[v] are > qr
         
         int bl = 0;
         int br = a[v].size();
         int ans = -1;
         while(bl <= br){
            int mid = bl + (br-bl)/2;
            
            if(a[v][mid] > qr){
               ans = mid;
               br = mid-1;
            } else {
               bl = mid+1;
            }
         }
         if(ans == -1) return 0;
         return a[v].size()-1 - ans+1;
         
      } else if(r < ql || l > qr){
         return 0;
      } else {
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
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   unordered_map<int,int> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   vector<int> next(n,INT_MAX);
   for(int k = n-1; k >= 0; k--){
      if(hmap.find(array[k]) != hmap.end()){
         next[k] = hmap[array[k]];
      }
      hmap[array[k]] = k;
   }
   
   Segtree segtree(n);
   segtree.build(0,0,n-1,next);
   
   for(int t = 0; t < q; t++){
      int l,r;
      cin >> l >> r;
      l--;r--;
      
      cout << segtree.query(0,0,n-1,l,r) << "\n";
   }
   
   return 0;
}