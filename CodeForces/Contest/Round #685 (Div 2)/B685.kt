import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

		for(t in 0 until m){
			var (l,r) = f.readLine().split(" ").map{it.toInt()}
			l--
			r--

			var yes = false
			for(k in 0 until l){
				if(array[k] == array[l]){
					yes = true
					break
				}
			}

			for(k in r+1 until n){
				if(array[k] == array[r]){
					yes = true
					break
				}
			}

			if(yes){
				println("YES")
			} else {
				println("NO")
			}
		}
	}
}
