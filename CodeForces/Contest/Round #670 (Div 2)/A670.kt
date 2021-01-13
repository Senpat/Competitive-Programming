import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt();
		val array = f.readLine().split(" ").map{it.toInt()}.sorted()

		val a = HashSet<Int>()
		val b = HashSet<Int>()

		for(k in 0 until n){
			if(a.contains(array[k])) b.add(array[k])
			else a.add(array[k])
		}

		var answer = 0
		for(k in 0..100){
			if(!a.contains(k)){
				answer += k
				break
			}
		}

		for(k in 0..100){
			if(!b.contains(k)){
				answer += k
				break
			}
		}

		println(answer)

	}
}
