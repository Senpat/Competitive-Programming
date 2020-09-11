import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val MOD : Long = 1000000007L

	val fac = LongArray(1000005){0L}
	val pow2 = LongArray(1000005){0L}
	fac[0] = 1L
	pow2[0] = 1L

	for(k in 1 until 1000005){
		fac[k] = (k.toLong()*fac[k-1] + MOD)%MOD
		pow2[k] = (2L*pow2[k-1] + MOD)%MOD
	}

	val answer = ((fac[n]-pow2[n-1] + MOD)%MOD+MOD)%MOD
	println(answer)
}
