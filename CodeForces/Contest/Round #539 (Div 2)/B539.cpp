#include <bits/stdc++.h>

using namespace std;

vector<int> fac;
int size = 0;
// from geeks for geeks
void primeFactors(int n) 
{ 
    // Print the number of 2s that divide n 
    while (n%2 == 0) 
    { 
        fac.push_back(2); 
        size++;
        n = n/2; 
        
    } 
  
    // n must be odd at this point.  So we can skip  
    // one element (Note i = i +2) 
    for (int i = 3; i <= sqrt(n); i = i+2) 
    { 
        // While i divides n, print i and divide n 
        while (n%i == 0) 
        { 
            fac.push_back(i); 
            cout << i << endl;
            size++;
            n = n/i; 
        } 
    } 
  
    // This condition is to handle the case when n  
    // is a prime number greater than 2 
    if (n > 2) {
        fac.push_back(n);
        cout << n << endl;
        size++;
     }
  }

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   int a[n];
   
   for(int k = 0; k < n; k++){
      cin >> a[k];
      primeFactors(a[k]);
   }
   
   sort(fac.begin(),fac.begin() + size);
   
   priority_queue<int,vector<int>,greater<int>> pq;
   
   int index = size-1;
   for(int k = 0; k < n; k++){
      pq.push(fac[index]);
      
      if(index < 0){
         pq.push(1);
         cout << 111 << endl;
      }
      index--;
      cout << index << endl;
   }
   
   for(int k = 0; k < size; k++){
      cout << fac[k] << endl ;
   }
   
   while(index >= 0){
      int cur = pq.top();
      pq.pop();
      cout << "hi" << endl;
      pq.push(cur*fac[index]);
      index--;
   }
   
   long long sum = 0;
   while(!pq.empty()){
      sum += pq.top();
      cout << pq.top() << endl;
      pq.pop();
   }
   
   cout << sum;
   
   
   
   
   
   return 0;
}