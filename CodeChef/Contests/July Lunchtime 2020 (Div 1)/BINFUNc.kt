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

		var maxindex = 0
		var minindex = 0

		for(k in 1 until n){
			if(array[k] > array[maxindex]) maxindex = k
			if(array[k] < array[minindex]) minindex = k
		}

		val xy = (array[maxindex] shl lens[minindex]) + array[minindex]
		val yx = (array[minindex] shl lens[maxindex]) + array[maxindex]

		val answer = abs(xy-yx)

		println(answer)
	}
}
