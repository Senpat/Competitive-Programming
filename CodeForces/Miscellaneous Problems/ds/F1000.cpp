#include <bits/stdc++.h>

using namespace std;


struct Val{
   int l;
   int r;
   int val;
};

struct Node{
   vector<Val> nums;
   //stores index of the max right value
   vector<int> pmax;
};

class Segtree{
   
   private:
   vector<Node> a;
   
   public:
   Segtree(int size){
      a = vector<Node>(4*size);
   }
   
   void build(int v, int l, int r, const vector<int>& prev, const vector<int>& next, const vector<int>& array){
      if(l == r){
         a[v].nums.push_back({prev[l],next[l],array[l]});
         a[v].pmax.push_back(0);
      } else {
         int mid = (l+r)/2;
         
         build(2*v+1,l,mid,prev,next,array);
         build(2*v+2,mid+1,r,prev,next,array);
         
         //merge, sort by l
         int li = 0;
         int ri = 0;
         int lsize = a[2*v+1].nums.size();
         int rsize = a[2*v+2].nums.size();
         while(li < lsize || ri < rsize){
            if(li >= lsize || (ri < rsize && a[2*v+1].nums[li].l > a[2*v+2].nums[ri].l)){
               a[v].nums.push_back(a[2*v+2].nums[ri]);
               ri++;
            } else {
               a[v].nums.push_back(a[2*v+1].nums[li]);
               li++;
            }
         }
         
         int n = a[v].nums.size();
         a[v].pmax = vector<int>(n);
         a[v].pmax[0] = 0;
         for(int k = 1; k < n; k++){
            if(a[v].nums[k].r > a[v].nums[a[v].pmax[k-1]].r){
               a[v].pmax[k] = k;
            } else {
               a[v].pmax[k] = a[v].pmax[k-1];
            }
         }
         
      }
   }
   
   int query(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         int n = a[v].nums.size();
         
         //binary search for biggest index with left < ql
         int lbs = 0;
         int rbs = n-1;
         int ans = -1;
         
         while(lbs <= rbs){
            int mid = lbs + (rbs-lbs)/2;
            
            if(a[v].nums[mid].l < ql){
               ans = mid;
               lbs = mid+1;
            } else {
               rbs = mid-1;
            }
         }
         
         if(ans == -1) return 0;
         if(a[v].nums[a[v].pmax[ans]].r <= qr) return 0;
         return a[v].nums[a[v].pmax[ans]].val;
         
         
      } else if(r < ql || l > qr){
         return 0;
      } else {
         int mid = (l+r)/2;
         
         int left = query(2*v+1,l,mid,ql,qr);
         if(left != 0) return left;
         return query(2*v+2,mid+1,r,ql,qr);
      }
   
   
   
   }
   
};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   vector<int> prev(n,-1);
   vector<int> next(n,INT_MAX);
   
   int N = 500005;
   vector<int> last(N,-1);
   
   for(int k = 0; k < n; k++){
      if(last[array[k]] != -1){
         prev[k] = last[array[k]];
         next[last[array[k]]] = k;
      }
      
      last[array[k]] = k;
   }
   
   Segtree segtree(n);
   segtree.build(0,0,n-1,prev,next,array);
   
   
   
   int q;
   cin >> q;
   
   for(int t = 0; t < q; t++){
      int l,r;
      cin >> l >> r;
      l--;
      r--;
      
      int answer = segtree.query(0,0,n-1,l,r);
      cout << answer << "\n";
   }
      
   
   
   return 0;
}