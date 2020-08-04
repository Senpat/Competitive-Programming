import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

	val MAXA = 1000005
	val remaining = BooleanArray(MAXA){true}

	var answer = 0
	for(k in 0 until n){
		if(remaining[array[k]] && !(k < n-1 && array[k+1] == array[k])) answer++
		if(k > 0 && array[k-1] == array[k]) continue
		for(j in array[k] until MAXA step array[k]) remaining[j] = false
	}

	println(answer)

}
