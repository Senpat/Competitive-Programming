import java.io.*
import java.util.*
import kotlin.math.*
//in contest attempt, probably not right
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MAX = 1000000
	val MIN = 0

	var (n,m) = f.readLine().split(" ").map{it.toInt()}

	val hori = Array<Hori>(n){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
		Hori(a,b,c)
	}
	val vert = Array<Vert>(m){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
		Vert(a,b,c)
	}

	hori.sortBy(it.x1)
	vert.sortBy(it.x)

	val start = IntArray(n){0}
	//when to add each Hori

	val vertup = mutableListOf<Int>()
	val vertdown = mutableListOf<Int>()

	val verti = m-1
	val horii = n-1
	for(k in MAX downTo MIN){
		//add verts
		if(vert[verti].x == k){
			//add
			if(vert[verti].y1 == 0){
				while(vertdown.size() > 0 && vertdown[vertdown.size()-1].y2 <= vert[verti].y2){

				}
			}
		}
	}









	val horil = mutableListOf<Hori>()
	val horir = mutableListOf<Hori>()
	val vertl = mutableListOf<Vert>()
	val vertr = mutableListOf<vert>()

	for(k in 0 until n){
		if(hori[k].x1 == 0){
			horil.add(hori[k])
		} else {
			horir.add()
		}
	}

}

data class Hori(val y : Int, val x1 : Int, val x2 : Int)
data class Vert(val x : Int, val y1 : Int, val y2 : Int)
