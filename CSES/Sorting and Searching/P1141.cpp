#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   //ifstream fin("P1141test.txt");
   //ofstream fout("P1141out.txt");
   
   int n;
   cin >> n;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   unordered_map<int,int> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   
   int answer = 0;
   int l = 0;
   for(int k = 0; k < n; k++){
      //fout << k << " " << array[k] << ":" << endl;
      if(hmap.find(array[k]) != hmap.end()){
         //fout << "found " << array[k] << " at " << hmap[array[k]] << endl;
         //fout << "l is " << l << endl; 
         int lto = hmap[array[k]]+1;
         for(int j = l; j <= hmap[array[k]]; j++){
            hmap.erase(array[j]);
         }
         l = lto;
         //fout << "l set to " << l << endl;
      }
      
      hmap[array[k]] = k;
      answer = max(answer,(int)hmap.size());
      //fout << k << " " << array[k] << " " << (int)hmap.size() << endl;
   }
   
   cout << answer << endl;
   
   return 0;
}