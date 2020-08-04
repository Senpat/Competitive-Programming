import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = IntArray(1) + f.readLine().split(" ").map{it.toInt()}

		val indexof = IntArray(n+1)
		for(k in 1..n) indexof[array[k]] = k

		fun dothing(i : Int) : Pair<Int,Int>{
			var a = -1
			var b = -1

			for(k in i-1 downTo 1){
				if(array[k] < array[i]){
					a = k
					break
				}
			}

			for(k in (i+1)..n){
				if(array[k] < array[i]){
					b = k
					break
				}
			}

			return Pair(a,b)
		}
		var found = false
		for(k in n downTo 1){
			val p = dothing(indexof[k])
			val a = p.first
			val b = p.second
			if(a!=-1 && b != -1){
				found = true
				println("YES")
				println("$a ${indexof[k]} $b")
				break
			}
		}

		if(!found) println("NO")
	}
}
