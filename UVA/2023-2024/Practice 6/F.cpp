#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   vector<int> array(n);
   long long zhao = 0LL;
   int jin = 0;
   int mai = INT_MAX;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      zhao += (long long)array[k];
      jin = max(jin,array[k]);
      mai = min(mai,array[k]);
   }
   
   int a1 = jin*4;
   int a2 = mai*4;
   
   double a4 = (double)zhao / (double)n * 4.0;
   
   //calc a3
   bitset<240005> b1;
   bitset<240005> b2;
   bitset<240005> b3;
   bitset<240005> b4;
   
   for(int k = 0; k < n; k++){
      b1.set(array[k]);
      b2 |= (b1 << array[k]);
      b3 |= (b2 << array[k]);
      b4 |= (b3 << array[k]);
   }
   
   int a3 = b4.count();
   
   cout << a1 << " " << a2 << " " << a3 << " " << a4 << endl;
   
   
   return 0;
}