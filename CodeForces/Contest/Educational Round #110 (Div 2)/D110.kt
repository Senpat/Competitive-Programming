import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val N = 1 shl n

	val array = CharArray(1) + f.readLine().toCharArray().reversed()

	val counts = IntArray(N){0}

	for(k in N-1 downTo 1){
		if(k*2 > N-1){
			if(array[k] == '?') counts[k] = 2
			else counts[k] = 1
		} else {
			if(array[k] == '?') counts[k] = counts[k*2] + counts[k*2+1]
			else if(array[k] == '0') counts[k] = counts[k*2+1]
			else if(array[k] == '1') counts[k] = counts[k*2]

			//shouldn't have an else
		}
	}

	val q = f.readLine().toInt()

	for(k in 0 until q){
		val line = f.readLine().split(" ")
		var i = N-line[0].toInt()
		val ch = line[1].single()

		array[i] = ch

		while(i >= 1){
			if(i*2 > N-1){
				if(array[i] == '?') counts[i] = 2
				else counts[i] = 1
			} else {
				if(array[i] == '?') counts[i] = counts[i*2] + counts[i*2+1]
				else if(array[i] == '0') counts[i] = counts[i*2+1]
				else if(array[i] == '1') counts[i] = counts[i*2]

				//shouldn't have an else
			}
			i = i shr 1
		}

		println(counts[1])
	}
}
