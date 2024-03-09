#include <bits/stdc++.h>

using namespace std;

//NEED TO REVIEW
//https://cp-algorithms.com/algebra/extended-euclid-algorithm.html
//https://cp-algorithms.com/algebra/linear-diophantine-equation.html

int gcd(long long a, long long b, long long& x, long long& y) {
    if (b == 0) {
        x = 1;
        y = 0;
        return a;
    }
    long long x1, y1;
    int d = gcd(b, a % b, x1, y1);
    x = y1;
    y = x1 - y1 * (a / b);
    return d;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   long long m;
   cin >> n >> m;
   
   vector<long long> array(n);
   long long asum = 0LL;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      asum += array[k];
   }
   
   long long want = (m - asum%m)%m;
   
   long long nl = (long long)n;
   long long a = nl;
   long long b = nl*(nl+1)/2LL;
   
   a %= m;
   b %= m;
   
   long long x_,y_;
   long long g = gcd(m,gcd(a,b,x_,y_),x_,y_);
   
   //find the smallest multiple of g >= want
   long long ceil = (want+g-1)/g*g;
   
   long long x,y;
   gcd(a,b,x,y);
   //x and y are the solutions to ax + by = g (note that gcd(x,y) = g)
   
   //make x and y in the right range
   x = ((x%m)+m)%m;
   y = ((y%m)+m)%m;
   
   //multiple x and y by the correct constant
   x = (x * ceil/g + m)%m;
   y = (y * ceil/g + m)%m;
   
   cout << ceil-want << endl;
   cout << x << " " << y << endl;
   
   
   
   
   
   return 0;
}