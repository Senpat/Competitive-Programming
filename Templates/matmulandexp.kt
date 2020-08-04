import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun matmul(a : Array<LongArray>, b : Array<LongArray>) : Array<LongArray>{
		val ret = Array(n){LongArray(n){0L}}

		for(k in 0 until n){
			for(j in 0 until n){
				for(h in 0 until n){
					ret[k][j] = (ret[k][j] + a[h][k]*b[j][h] + MOD)%MOD
				}
			}
		}
		return ret
	}

	fun exp(base : Array<LongArray>, power : Int) : Array<LongArray>{
		if(power == 0) return Array(n){LongArray(n){1}}
		if(power == 1) return base
		var ans = exp(base,power/2)
		ans = matmul(ans,ans)
		if(power%2 == 1) ans = matmul(ans,base)
		return ans
	}
}
