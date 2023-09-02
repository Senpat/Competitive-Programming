#include <bits/stdc++.h>
//in contest attempt, tle
using namespace std;

/*
struct Node{
   priority_queue<int> out_arrows;
   int arrows_within;
};
*/

class Segtree{
   private:
   vector<int> array;
   
   //vector<int> arrows;
   
   //vector<multiset<int>> msets;
   
   public:
   Segtree(const vector<int>& nums) : array(nums){
      //a = vector<Node>(4*nums.size());
      
      //init arrows
      //arrows = vector<int>(4*nums.size());
      
      //msets = vector<multiset<int>>(4*nums.size());
   }
   
   /*
   void build(int v, int l, int r, const vector<int>& nums){
      if(l != r){
         int mid = (l+r)/2;
         
         build(2*v+1,l,mid,nums);
         build(2*v+2,mid+1,r,nums);
      }
      msets[v] = multiset<int>(nums.begin()+l,nums.begin()+r+1);
   }*/
   
   //updates the in_arrows priorityqueue (to arrows going out of the segment)
   //and returns the # of arrows within the segment
   int query(int v, int l, int r, int ql, int qr, priority_queue<int>& in_arrows){
      if(l == r){
         int within = 0;
         if(array[l] >= ql && array[l] <= qr){
            while(!in_arrows.empty() && in_arrows.top() >= array[l]){
               if(in_arrows.top() != array[l]){
                  within++;
               }
               in_arrows.pop();
            }
            in_arrows.push(array[l]);
         }
         return within;
      } else {
         int mid = (l+r)/2;
         
         int right = query(2*v+2,mid+1,r,ql,qr,in_arrows);
         int left = query(2*v+1,l,mid,ql,qr,in_arrows);
         
         return right+left;
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
   
   Segtree segtree(array);
   
   for(int t = 0; t < q; t++){
      int l,r;
      cin >> l >> r;
      
      priority_queue<int> pq;
      int within = segtree.query(0,0,n-1,l,r,pq);
      
      int answer = within + pq.size();
      cout << answer << endl;
   }   
      
   
   return 0;
}