//FROM: https://www.geeksforgeeks.org/queries-number-distinct-elements-subarray/?ref=rp
// C++ code to find number of distinct numbers 
// in a subarray 
#include<bits/stdc++.h> 
using namespace std; 
  
const int MAX = 500005; 
  
// structure to store queries 
struct Query 
{ 
   int l, r, idx; 
}; 
  
// cmp function to sort queries according to r 
bool cmp(Query x, Query y) 
{ 
   return x.r < y.r; 
} 
  
// updating the bit array 
void update(int idx, int val, int bit[], int n) 
{ 
   for (; idx <= n; idx += idx&-idx) 
      bit[idx] += val; 
} 
  
// querying the bit array 
int query(int idx, int bit[], int n) 
{ 
   int sum = 0; 
   for (; idx>0; idx-=idx&-idx) 
      sum += bit[idx]; 
   return sum; 
} 
   
// driver code 

int main() 
{ 
   ios::sync_with_stdio(false);
   cin.tie(0);
   //cout << "hi" << endl;
   int n;
   int q;
   cin >> n >> q;
    
   int arr[MAX];
   for(int k = 0; k < n; k++){
      cin >> arr[k];
   }
   Query queries[MAX];
   for(int k = 0; k < q; k++){
      int l;
      int r;
      cin >> l >> r;
      queries[k].l=l;
      queries[k].r=r;
      queries[k].idx=k;
   }
    
        // initialising bit array 
   int bit[n+1]; 
   memset(bit, 0, sizeof(bit)); 
  
    // holds the rightmost index of any number 
    // as numbers of a[i] are less than or equal to 10^6 
   int last_visit[MAX]; 
   memset(last_visit, -1, sizeof(last_visit)); 
  
    // answer for each query 
   int ans[q]; 
   int query_counter = 0; 
   for (int i=0; i<n; i++) 
   { 
      cout << i;
        // If last visit is not -1 update -1 at the 
        // idx equal to last_visit[arr[i]] 
      if (last_visit[arr[i]] !=-1) 
         update (last_visit[arr[i]] + 1, -1, bit, n); 
   
        // Setting last_visit[arr[i]] as i and updating 
        // the bit array accordingly 
      last_visit[arr[i]] = i; 
      update(i + 1, 1, bit, n); 
   
        // If i is equal to r of any query  store answer 
        // for that query in ans[] 
      while (query_counter < q && queries[query_counter].r == i) 
      { 
         ans[queries[query_counter].idx] = 
                     query(queries[query_counter].r + 1, bit, n)- 
                     query(queries[query_counter].l, bit, n); 
         query_counter++; 
      } 
   } 
  
    // print answer for each query 
   for (int i=0; i<q; i++) 
      cout << ans[i] << endl; 
    
    
   return 0; 
}