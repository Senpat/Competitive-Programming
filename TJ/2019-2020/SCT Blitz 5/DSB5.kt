import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,MOD) = f.readLine().split(" ").map{it.toLong()}

	val fibo = Array(2){x -> LongArray(2){y -> max(x,y).toLong()}}

	fun matmul(a : Array<LongArray>, b : Array<LongArray>) : Array<LongArray>{
		val ret = Array(2){LongArray(2){0L}}

		for(k in 0 until 2){
			for(j in 0 until 2){
				for(h in 0 until 2){
					ret[k][j] = (ret[k][j] + a[h][k]*b[j][h] + MOD)%MOD
				}
			}
		}
		return ret
	}

	fun exp(base : Array<LongArray>, power : Long) : Array<LongArray>{
		if(power == 0L) return Array(2){LongArray(2){1L}}
		if(power == 1L) return base
		var ans = exp(base,power/2)
		ans = matmul(ans,ans)
		if(power%2 == 1L) ans = matmul(ans,base)
		return ans
	}

	var answer = exp(fibo,n)

	println(answer[1][1])

}
