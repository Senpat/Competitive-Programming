#include <bits/stdc++.h>

using namespace std;

void printintervals(const set<pair<int,int>>& intervals){
   for(auto [l,r] : intervals){
      cout << l << " " << r << endl;
   }
}

void printsizes(const multiset<int>& sizes){
   for(int i : sizes){
      cout << i << " ";
   }
   cout << endl;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      array[k] = s[k]-'0';
   }
   
   multiset<int> sizes;
   set<pair<int,int>> intervals;
   
   int curstart = 0;
   for(int k = 1; k < n; k++){
      if(array[k] != array[k-1]){
         intervals.insert(make_pair(curstart,k-1));
         sizes.insert(k-1 - curstart + 1);
         curstart = k;
      }
   }
   
   intervals.insert(make_pair(curstart,n-1));
   sizes.insert(n-1 - curstart + 1);
   
   //printintervals(intervals);
   //printsizes(sizes);
   int q;
   cin >> q;
   
   for(int t = 0; t < q; t++){
      int x;
      cin >> x, x--;
      
      auto lb = make_pair(x,INT_MAX);
      auto curi = intervals.lower_bound(lb);
      curi--;
      auto curinterval = *curi;
      
      bool mergeleft = (x > 0 && x == curinterval.first);
      bool mergeright = (x < n-1 && x == curinterval.second);
      
      intervals.erase(curinterval);
      sizes.erase(sizes.find(curinterval.second-curinterval.first+1));
      
      //cout << x << endl;
      //cout << "curinterval: " << curinterval.first << " " << curinterval.second << endl;
      
      auto newinterval = make_pair(x,x);
      if(mergeleft){
         auto lefti = intervals.lower_bound(lb);
         lefti--;
         auto leftinterval = *lefti;
         intervals.erase(leftinterval);
         sizes.erase(sizes.find(leftinterval.second-leftinterval.first+1));
         
         newinterval = make_pair(leftinterval.first,newinterval.second);
      }
      if(mergeright){
         auto temp = intervals.lower_bound(lb);
         //temp++;
         auto rightinterval = *temp;
         
         intervals.erase(temp);
         sizes.erase(sizes.find(rightinterval.second-rightinterval.first+1));
         
         newinterval = make_pair(newinterval.first,rightinterval.second);
      }
      
      intervals.insert(newinterval);
      sizes.insert(newinterval.second-newinterval.first+1);
      
      if(newinterval.first > curinterval.first){
         intervals.insert(make_pair(curinterval.first,newinterval.first-1));
         sizes.insert(newinterval.first-1 - curinterval.first + 1);
      }
      
      if(newinterval.second < curinterval.second){
         intervals.insert(make_pair(newinterval.second+1,curinterval.second));
         sizes.insert(curinterval.second - (newinterval.second+1) + 1);
      }
      
      //printintervals(intervals);
      //printsizes(sizes);
      cout << *sizes.rbegin() << " ";
      
   }
   
   return 0;
}