#include <bits/stdc++.h>

using namespace std;

//fenwick tree
int n,m;

vector<int> bits;

void update(int i, int x){
   //cout << "update " << i << endl;
   for(; i <= n; i += i&-i){
      bits[i]+=x;
   }
}
   
int psum(int i){
   //cout << "psum " << i << endl;
   int cursum = 0;
   for(; i > 0; i -= i&-i){
      cursum += bits[i];
   }
   return cursum;

}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   cin >> n >> m;
   
   bits = vector<int>(n+1,0);
   for(int k = 1; k <= n; k++){
      update(k,1);
   }
   
   vector<int> answer;
   
   int i = 1 + m%n;
   answer.push_back(i);
   update(i,-1);
   int total = n-1;
   for(int k = 0; k < n-1; k++){
      //figure out how much open spaces from i+1 to the end
      int curm = m+1;               //the ith child that gets removed
      int endsum = psum(n) - psum(i);
      
      //binary search parameters (find ith open space)
      int start = 1;
      int l = 1;
      int r = n;
      int ans = -1;
      
      //cout << k << ": " << endsum << " " << curm << endl;
      
      if(endsum >= curm){
         start = i;
         l = i;
      } else {
         curm = (curm-endsum-1)%total+1;
      }
      
      //cout << "curm: " << curm << endl;
      
      
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         int cursum = psum(mid) - psum(start-1);
         //cout << l << " " << mid << " " << r << " " << cursum << endl;
         if(cursum >= curm){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      //cout << "ans: " << ans << endl;
      answer.push_back(ans);
      update(ans,-1);
      i = ans;
      total--;     
   }
   
   for(int i : answer){
      cout << i << " ";
   }
   
   
   
   return 0;
}