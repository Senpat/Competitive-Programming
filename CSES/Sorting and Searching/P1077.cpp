#include <bits/stdc++.h>

using namespace std;

multiset<long long> mset1;
long long sum1 = 0LL;
multiset<long long> mset2;
long long sum2 = 0LL;

void balance(){
   
   while(mset1.size() > mset2.size()+1){
      long long lmax = *mset1.rbegin();
      mset1.erase(mset1.find(lmax));
      sum1 -= lmax;
      
      mset2.insert(lmax);
      sum2 += lmax;
   }
   
   while(mset1.size() < mset2.size()){
      long long rmin = *mset2.begin();
      mset2.erase(mset2.find(rmin));
      sum2 -= rmin;
      
      mset1.insert(rmin);
      sum1 += rmin;
   }
   
}

void addelement(long long x){
   long long lmax = *mset1.rbegin();
   if(x <= lmax){
      mset1.insert(x);
      sum1 += x;
   } else {
      mset2.insert(x);
      sum2 += x;
   }
}

void remelement(long long x){
   long long lmax = *mset1.rbegin();
   if(x <= lmax){
      mset1.erase(mset1.find(x));
      sum1 -= x;
   } else {
      mset2.erase(mset2.find(x));
      sum2 -= x;
   }
}

long long getanswer(){
   long long lmax = *mset1.rbegin();
   long long answer = sum2 - lmax * mset2.size() + lmax * mset1.size() - sum1;
   return answer;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   vector<long long> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   vector<long long> answer;
   
   //first answer
   for(int k = 0; k < m; k++){
      mset1.insert(array[k]);
      sum1 += array[k];
   }
   
   balance();
   
   //(sum2 - lmax) + (lmax - sum1) = sum2 - sum1
   answer.push_back(getanswer());
   
   for(int k = m; k < n; k++){
      addelement(array[k]);
      remelement(array[k-m]);
      balance();
      
      answer.push_back(getanswer());
   }
   
   for(long long i : answer){
      cout << i << " ";
   }
   
   
   
   
   return 0;
}