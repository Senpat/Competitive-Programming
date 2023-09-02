#include <bits/stdc++.h>

using namespace std;


multiset<int> mset1;
multiset<int> mset2;

void balance(){
   while(mset1.size() > mset2.size()){
      int lmax = *mset1.rbegin();
      mset1.erase(mset1.find(lmax));
      mset2.insert(lmax);
   }
   
   while(mset1.size() < mset2.size()-1){
      int rmin = *mset2.begin();
      mset2.erase(mset2.find(rmin));
      mset1.insert(rmin);
   }
}

void addelement(int x){
   int lmax = *mset1.rbegin();
   if(x <= lmax) mset1.insert(x);
   else mset2.insert(x);
   
   balance();
}

void remelement(int x){
   int lmax = *mset1.rbegin();
   if(x <= lmax) mset1.erase(mset1.find(x));
   else mset2.erase(mset2.find(x));
   
   balance();
}

int getmedian(){
   if(mset1.size() >= mset2.size()){
      return *mset1.rbegin();
   } else {
      return *mset2.begin();
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
      if(m == 1){
         cout << array[k] << " ";
      }
   }
   
   if(m == 1){
      return 0;
   }
   
   mset1.insert(min(array[0],array[1]));
   mset2.insert(max(array[0],array[1]));
   
   vector<int> answer;
   
   //initial
   for(int k = 2; k < m; k++){
      addelement(array[k]); 
   }
   
   answer.push_back(getmedian());
   
   for(int k = m; k < n; k++){
      addelement(array[k]);
      remelement(array[k-m]);
      
      answer.push_back(getmedian());
   }
   
   for(int i : answer){
      cout << i << " ";
   }
   
   
   
   
   
   
   
   
   return 0;
}