import java.io.*
import java.util.*
import kotlin.math.*
//does first sample
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		var add = m-(n-1)
		if(add < 0){
			println("Case #${q}: IMPOSSIBLE")
			continue
		}

		val array = IntArray(n){it+1}

		fun rev(ll : Int, rr : Int){
			var l = ll
			var r = rr
			while(l < r){
				var temp = array[l]
				array[l] = array[r]
				array[r] = temp
				l++
				r--
			}
		}

		for(k in n-2 downTo 0){
			if(add == 0){
				continue
			}

			if(add >= n-1-k){
				add -= n-1-k
				rev(k,n-1)
			} else {
				rev(k,k+add)
				add = 0
			}
		}

		if(add == 0){
			println("Case #${q}: ${array.joinToString(" ")}")
		} else {
			println("Case #${q}: IMPOSSIBLE")
		}
	}
}
