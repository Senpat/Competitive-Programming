import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val st = StringTokenizer(f.readLine())
		val a = IntArray(n){st.nextToken().toInt()}
		val b = IntArray(n){st.nextToken().toInt()}
		a.reverse()

		var eat = 0
		for(k in 0 until n){
			if(a[k] == 1) eat++
			else eat--
			if(b[k] == 1) eat++
			else eat--
		}

		val hmap = HashMap<Int,Int>()
		hmap.put(0,0)
		var difb = 0
		for(k in 0 until n){
			if(b[k] == 1) difb++
			else difb--

			if(!hmap.containsKey(difb)){
				hmap.put(difb,k+1)
			}
		}

		var min = 2*n

		//try 0
		if(hmap.containsKey(eat)){
			min = min(min,hmap[eat]!!)
		}
		var difa = 0
		for(k in 0 until n){
			if(a[k] == 1) difa++
			else difa--

			if(hmap.containsKey(eat-difa)){
				min = min(min,k+hmap[eat-difa]!!+1)
			}
		}

		println(min)
	}
}
