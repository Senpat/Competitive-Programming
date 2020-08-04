import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		var ai = 0
		var bi = n-1
		var turn = 0

		var prev = 0
		var a = 0
		var b = 0

		while(ai <= bi){

			if(turn%2 == 0){
				var cur = 0
				while(cur <= prev && ai <= bi){
					cur += array[ai]
					a += array[ai]
					ai++
				}

				prev = cur
			} else {
				var cur = 0
				while(cur <= prev && ai <= bi){
					cur += array[bi]
					b += array[bi]
					bi--
				}

				prev = cur
			}

			turn++
		}

		println("$turn $a $b")
	}
}
