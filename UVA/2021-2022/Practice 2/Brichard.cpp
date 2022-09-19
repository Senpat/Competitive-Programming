// Author: Richw818
// Created: 03.19.2022 21:29:06

#include <bits/stdc++.h>
using namespace std;
const int64_t MOD = 1e9 + 7;
struct matrix{
    int dim = 2;
    //int64_t MOD = 1e9 + 7;
    int64_t a[2][2];
    matrix(){
        for(int i = 0; i < dim; ++i){
            for(int j = 0; j < dim; ++j)
                a[i][j] = 0;
        }
    }
    matrix(int _dim){
        dim = _dim;
        for(int i = 0; i < dim; ++i){
            for(int j = 0; j < dim; ++j)
                a[i][j] = 0;
        }
    }
    matrix operator*(matrix other){
        assert(dim == other.dim);
        matrix res = matrix(dim);
        for(int k = 0; k < dim; ++k){
            for(int i = 0; i < dim; ++i){
                for(int j = 0; j < dim; ++j){
                    res.a[i][j] += a[i][k] * other.a[k][j];
                    res.a[i][j] %= MOD;
                }
            }
        }
        return res;
    }
};
int64_t modpow(int64_t n, int64_t p){
    int64_t res = 1;
    while(p){
        if(p & 1)
            res = (res * n) % MOD;
        n = (n * n) % MOD;
        p >>= 1;
    }
    return res;
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int64_t a, b, n; cin >> a >> b >> n;
    matrix f;
    int64_t c = (a * a - b * b) % MOD;
    c = (c + MOD) % MOD;
    c *= modpow((a * a + b * b)%MOD, MOD - 2);
    c %= MOD;
    assert(c >= 0);
    int64_t s = (2ll * a * b) % MOD;
    s *= modpow((a * a + b * b)%MOD, MOD - 2);
    s %= MOD;
    assert(s >= 0);
    f.a[0][0] = c;
    f.a[0][1] = s;
    f.a[1][0] = (-s + MOD) % MOD;
    f.a[1][1] = c;
    
    ++n;
    matrix res;
    res.a[0][0] = 1;
    res.a[0][1] = 0;
    res.a[1][0] = 0;
    res.a[1][1] = 1;
    while(n){
        if(n & 1)
            res = f * res;
        n >>= 1;
        f = f * f;
    }
    
    cout << res.a[0][0] << " " << res.a[0][1] << endl;
    cout << res.a[1][0] << " " << res.a[1][1] << endl;
    
    int64_t ans = -res.a[0][0];
    ans = (ans + MOD) % MOD;
    assert(ans >= 0);
    cout << ans;
    return 0;
}