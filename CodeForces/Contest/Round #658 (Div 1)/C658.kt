import java.io.*
import java.util.*
import kotlin.math.*
//contest bs, not at all done
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,x,y) = f.readLine().split(" ").map{it.toInt()}

		val array = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray()

		val hs = (1..(n+1)).toHashSet()
		var notused = -1

		for(k in 1..n){
			if(hs.contains(array[k])) hs.remove(k)
		}

		for(i in hs){
			notused = i
			break
		}

		val answer = IntArray(n+1){0}


	}
}
