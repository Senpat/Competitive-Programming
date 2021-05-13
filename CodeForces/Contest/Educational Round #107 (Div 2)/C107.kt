import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,q) = f.readLine().split(" ").map{it.toInt()}

	val array = f.readLine().split(" ").map{it.toInt()}
	val qs = f.readLine().split(" ").map{it.toInt()}

	val colors = IntArray(51){-1}							//stores index of all colors

	for(k in 0 until n){
		if(colors[array[k]] == -1) colors[array[k]] = k+1
	}

	val answer = IntArray(q){0}
	for(k in 0 until q){
		answer[k] = colors[qs[k]]
		for(j in 1..50){
			if(colors[j] < colors[qs[k]]) colors[j]++
		}
		colors[qs[k]] = 1
	}

	println(answer.joinToString(" "))

}
