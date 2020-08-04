import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().map{Character.getNumericValue(it)}

		val a = mutableListOf<Int>()
		val b = mutableListOf<Int>()

		var broken = false
		for(k in 0 until n){
			if(broken){
				a.add(0)
				b.add(array[k])
			} else {
				if(array[k] == 2){
					a.add(1)
					b.add(1)
				} else if(array[k] == 1){
					a.add(1)
					b.add(0)
					broken = true
				} else {
					a.add(0)
					b.add(0)
				}
			}
		}

		println(a.joinToString(""))
		println(b.joinToString(""))
	}
}
