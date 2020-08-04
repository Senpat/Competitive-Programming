import java.io.*
import java.util.*
import kotlin.math.*

fun main(天神偶么卡儿 : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val xs = HashMap<Int,Int>()
		val ys = HashMap<Int,Int>()

		for(k in 0 until (n*4-1)){
			val (x,y) = f.readLine().split(" ").map{it.toInt()}

			xs[x] = (xs[x] ?: 0) + 1
			ys[y] = (ys[y] ?: 0) + 1
		}

		var x = -1
		var y = -1

		for(xv in xs.keys){
			if(xs[xv]!!%2 == 1){
				x = xv
				break
			}
		}

		for(yv in ys.keys){
			if(ys[yv]!!%2 == 1){
				y = yv
				break
			}
		}

		println("$x $y")
	}
}
