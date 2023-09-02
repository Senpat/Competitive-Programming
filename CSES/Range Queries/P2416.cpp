#include <bits/stdc++.h>

using namespace std;

struct Node{
   long long answer;
   vector<long long> pmax;
   vector<long long> pmaxsum;
   Node() {
      answer = 0LL;
      pmax = vector<long long>();
      pmaxsum = vector<long long>();
   }
};

class Segtree{

   private:
   vector<Node> a;
   
   public:
   Segtree(int size){
      a = vector<Node>(4*size);
   }
   
   void build(int v, int l, int r, const vector<long long>& array){
      if(l != r){
         int mid = (l+r)/2;
         
         build(2*v+1,l,mid,array);
         build(2*v+2,mid+1,r,array);
      }
      
      vector<long long> sub;
      for(int k = l; k <= r; k++){
         sub.push_back(array[k]);
      }
      int n = sub.size();
      
      a[v].answer = 0LL;
      
      a[v].pmax = vector<long long>(n);
      a[v].pmaxsum = vector<long long>(n);
      
      a[v].pmax[0] = sub[0];
      a[v].pmaxsum[0] = sub[0];
      for(int k = 1; k < n; k++){
         a[v].pmax[k] = max(a[v].pmax[k-1],sub[k]);
         a[v].pmaxsum[k] = a[v].pmaxsum[k-1]+a[v].pmax[k];
         
         a[v].answer += a[v].pmax[k]-sub[k];
      }
      
      
   }
   
   //returns pair with sum and last value (prev max)
   pair<long long, long long> query(int v, int l, int r, int ql, int qr, long long last_val){
      if(l >= ql && r <= qr){
         //see how much to increase answer by
         
         //binary search to get last pmax that is < last_val
         int bl = 0;
         int br = a[v].pmax.size()-1;
         int ans = -1;
         while(bl <= br){
            int mid = bl + (br-bl)/2;
              
            if(a[v].pmax[mid] < last_val){
               ans = mid;
               bl = mid+1;
            } else {
               br = mid-1;
            }
         }
         
         if(ans == -1){
            //all values are >= last_val
            return make_pair(a[v].answer,a[v].pmax.back());
         }
         
         long long curanswer = a[v].answer - a[v].pmaxsum[ans] + last_val * (ans+1);
         long long curlastval = max(a[v].pmax.back(),last_val);
         
         return make_pair(curanswer,curlastval);
      } else if(r < ql || l > qr){
         return make_pair(0LL,0LL);
      } else {
         int mid = (l+r)/2;
         
         //left
         auto left = query(2*v+1,l,mid,ql,qr,last_val);
         auto right = query(2*v+2,mid+1,r,ql,qr,left.second);
         
         return make_pair(left.first+right.first,right.second);
      }
   }

};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   Segtree segtree(n);
   segtree.build(0,0,n-1,array);
   
   for(int t = 0; t < q; t++){
      int l,r;
      cin >> l >> r;
      l--;r--;
      
      auto ans = segtree.query(0,0,n-1,l,r,0LL);
      cout << ans.first << "\n";
   }
   
   
   return 0;
}