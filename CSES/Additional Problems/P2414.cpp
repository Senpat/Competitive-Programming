#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   int n2 = n*(n-1)/2;
   
   vector<int> b(n2);
   multiset<int> mset;
   for(int k = 0; k < n2; k++){
      cin >> b[k];
      mset.insert(b[k]);
   }
   
   sort(b.begin(),b.end());
   
   //possible values of the smallest element
   vector<int> possible;
   for(int k = 2; k < n2; k++){
      //see if b[0] + b[1] - 2*x = b[k]
      int px = b[0] + b[1] - b[k];
      if(px >= 1 && px % 2 == 0){
         possible.push_back(px/2);
      }
   }
   
   for(int x : possible){
      //see if x is the first number
      vector<int> a;
      multiset<int> cur(mset);
      //cout << x << endl;
      
      bool fail = false;
      a.push_back(x);
      for(int k = 1; k < n; k++){
         auto beg = cur.begin();
         int curmin = *beg;
         a.push_back(curmin-x);
         //cout << curmin-x << endl;
         cur.erase(beg);
         
         for(int j = 1; j < k; j++){
            auto nextval = cur.find(a[k]+a[j]);
            if(nextval == cur.end()){
               //cout << "fail " << a[k]+a[j] << endl;
               fail = true;
               break;
            }
            //cout << "found " << a[k]+a[j] << endl;
            
            //a.push_back(*nextval);
            cur.erase(nextval);
         }
         
         if(fail) break;
      }
      
      //make sure it's increasing
      if(!fail){
         for(int k = 1; k < n; k++){
            if(a[k] < a[k-1]){
               fail = true;
               break;
            }
         }
      }
      
      if(!fail){
         for(int k = 0; k < n; k++){
            cout << a[k] << " ";
         }
         break;
      }
   }
   
   return 0;
}