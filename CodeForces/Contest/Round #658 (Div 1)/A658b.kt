import java.io.*
import java.util.*
import kotlin.math.*
//works on A2
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val a = f.readLine().map{Character.getNumericValue(it)}.toIntArray()
		val b = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

		val answer = mutableListOf<Int>()
		//turn into all ones

		var cur = a[0]

		for(k in 1 until n){
			if(a[k] != cur){
				cur = 1-cur
				answer.add(k-1)
			}
		}

		if(cur == 0){
			//flip whole thing
			answer.add(n-1)
		}

		cur = 1

		for(k in n-1 downTo 0){
			if(b[k] != cur){
				cur = 1-cur
				answer.add(k)
			}
		}

		for(k in 0 until answer.size){
			answer[k]++
		}
		println("${answer.size} " + answer.joinToString(" "))
	}
}
