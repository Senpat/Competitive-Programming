import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 1000000007L
	val pow2 = LongArray(400005){0L}
	pow2[0] = 1L
	for(k in 1 until 400005){
		pow2[k] = (pow2[k-1]*2L+MOD)%MOD
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val a = f.readLine().split(" ").map{it.toInt()}
		val b = f.readLine().split(" ").map{it.toInt()}

		val bindexof = IntArray(n+1){0}
		for(k in 0 until n){
			bindexof[b[k]] = k
		}

		var comps = 0
		val seen = HashSet<Int>()
		for(k in 0 until n){
			if(seen.contains(k)) continue
			comps++

			var i = k
			while(!seen.contains(i)){
				seen.add(i)
				i = bindexof[a[i]]
			}

		}

		println(pow2[comps])
	}
}
