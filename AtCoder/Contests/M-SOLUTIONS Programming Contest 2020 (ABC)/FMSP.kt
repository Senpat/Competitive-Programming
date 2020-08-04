import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	//sort left to right, bot to top
	val n = f.readLine().toInt()
	val points = Array<Point>(n){
		val (a,b,c) = f.readLine().split(" ")
		Point(a.toInt(),b.toInt(),c.single())
	}.sortedWith(compareBy({it.x},{it.y}))

	val MAX = 4000000
	var answer = MAX

	val cols = HashMap<Int,MutableList<Point>>()
	val rows = HashMap<Int,MutableList<Point>>()

	//diagonal determined by y-x
	val ulminus = HashMap<Int,MutableList<Point>>()
	val rdminus = HashMap<Int,MutableList<Point>>()

	//diagonal determined by x+y
	val ruplus = HashMap<Int,MutableList<Point>>()
	val dlplus = HashMap<Int,MutableList<Point>>()

	for(k in 0 until n){
		val p = points[k]
		if(p.d == 'U'){
			if(cols.containsKey(p.x)){
				cols[p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				cols.put(p.x,pq)
			}

			if(ulminus.containsKey(p.y-p.x)){
				ulminus[p.y-p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				ulminus.put(p.y-p.x,pq)
			}

			if(ruplus.containsKey(p.y+p.x)){
				ruplus[p.y+p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				ruplus.put(p.y+p.x,pq)
			}
		} else if(p.d == 'D'){
			if(cols.containsKey(p.x)){
				cols[p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				cols.put(p.x,pq)
			}

			if(rdminus.containsKey(p.y-p.x)){
				rdminus[p.y-p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				rdminus.put(p.y-p.x,pq)
			}

			if(dlplus.containsKey(p.y+p.x)){
				dlplus[p.y+p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				dlplus.put(p.y+p.x,pq)
			}
		} else if(p.d == 'L'){
			if(rows.containsKey(p.y)){
				rows[p.y]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				rows.put(p.y,pq)
			}

			if(ulminus.containsKey(p.y-p.x)){
				ulminus[p.y-p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				ulminus.put(p.y-p.x,pq)
			}

			if(dlplus.containsKey(p.y+p.x)){
				dlplus[p.y+p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				dlplus.put(p.y+p.x,pq)
			}
		} else if(p.d == 'R'){
			if(rows.containsKey(p.y)){
				rows[p.y]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				rows.put(p.y,pq)
			}

			if(rdminus.containsKey(p.y-p.x)){
				rdminus[p.y-p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				rdminus.put(p.y-p.x,pq)
			}

			if(ruplus.containsKey(p.y+p.x)){
				ruplus[p.y+p.x]!!.add(p)
			} else {
				val pq = mutableListOf<Point>()
				pq.add(p)
				ruplus.put(p.y+p.x,pq)
			}
		}
	}

	fun dist(p1 : Point, p2 : Point) : Int{
		return max(abs(p1.x-p2.x),abs(p1.y-p2.y))
	}

	fun calc(li : MutableList<Point>,first : Char, second : Char) : Int{

		var seenprev = false
		var prev = Point(0,0,second)

		var ret = 3*MAX

		for(p in li){
			if(p.d == first){
				seenprev = true
				prev = p
			} else if(p.d == second){
				if(seenprev){
					ret = min(ret,dist(p,prev))
				}
			}

		}

		return ret
	}

	//cols
	for(i in cols.keys){
		answer = min(answer,calc(cols[i]!!,'U','D')*5)
	}

	//rows
	for(i in rows.keys){
		answer = min(answer,calc(rows[i]!!,'R','L')*5)
	}

	//ulminus
	for(i in ulminus.keys){
		answer = min(answer,calc(ulminus[i]!!,'U','L')*10)
	}

	//drminus
	for(i in rdminus.keys){
		answer = min(answer,calc(rdminus[i]!!,'R','D')*10)
	}

	//ruplus
	for(i in ruplus.keys){
		answer = min(answer,calc(ruplus[i]!!,'R','U')*10)
	}

	//dlplus
	for(i in dlplus.keys){
		answer = min(answer,calc(dlplus[i]!!,'D','L')*10)
	}

	if(answer == MAX){
		println("SAFE")
	} else {
		println(answer)
	}

}
data class Point(val x : Int, val y : Int, val d : Char)
