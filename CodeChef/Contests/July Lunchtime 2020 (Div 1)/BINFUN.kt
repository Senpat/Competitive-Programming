import java.io.*
import java.util.*
import kotlin.math.*
//works on first tc
fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

		val lens = IntArray(n)
		for(k in 0 until n){
			var i = array[k]
			while(i > 0){
				lens[k]++
				i = i shr 1
			}
		}

		var answer = 0L

		for(k in 0 until n){
			for(j in k+1 until n){
				if(k == j) continue
				val xy = (array[k] shl lens[j]) + array[j]
				val yx = (array[j] shl lens[k]) + array[k]

				answer = max(answer,abs(xy-yx))
			}
		}

		println(answer)
	}
}
