#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<int> array;
   for(int k = 0; k < n; k++){
      int i;
      cin >> i;
      array.push_back(i);
   }
   
   map<vector<pair<int,int>>,long long> freq;
   
   long long answer = 0ll;
   
   for(int k = 0; k < n; k++){
      vector<pair<int,int>> facs;
      vector<pair<int,int>> oppo;
         
      int x = array[k];
      for(int i = 2; i*i <=  x; i++){
         if(x%i == 0){
            int num = 0;
            while(x%i == 0){
               num++;
               x/=i;
            }
            if(num%m!=0){
               facs.push_back(make_pair(i,num%m));
               oppo.push_back(make_pair(i,m-num%m));
            }
         }
      }
         
      if(x!=1){
         facs.push_back(make_pair(x,1));
         oppo.push_back(make_pair(x,m-1));
      }
         
      if(freq.find(oppo)!=freq.end()){
         answer += freq[oppo];
      }
         
      freq[facs]++;
         /*
         if(map.find(facs) != map.end()){
            map[facsmap.get(facs)+1);
         } else {
            map.put(facs,1);
         }*/
         
         
   }
   
   cout << answer << endl;

   
   
   
   
   return 0;
}