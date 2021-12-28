import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val done = HashMap<Pair<Int,Int>, Int>()
	var solved = false

	val U = 0
	val L = 1
	val D = 2
	val R = 3

	val strings = arrayOf("up","left","down","right")

	val seen = HashSet<Pair<Int,Int>>()
	//incoming is the direction that was played to get to p
	fun dothing(p : Pair<Int,Int>, incoming : Int){
		//println("${p.first} ${p.second}")
		if(solved) return
		seen.add(p)
		var i : Int = 0
		if(done.containsKey(p)){
		   i = done.get(p)!!
		} else {
		   done.put(p,0)
		}

		//try U
		if((incoming != D) && ((i and (1 shl U)) == 0) && !seen.contains(Pair(p.first,p.second+1))){
		   println("up")
		   System.out.flush()
		   val v = readLine()!!
		   if(v.equals("solved")) {
		       solved = true
		       return
		   }
		   done.put(p,done[p]!! + (1 shl U))
		   if(v.equals("ok")){
		       dothing(Pair(p.first,p.second+1),U)
		   }
		}
		if(solved) return
		//try L
		if((incoming != R) && ((i and (1 shl L)) == 0) && !seen.contains(Pair(p.first-1,p.second))){
		   println("left")
		   System.out.flush()
		   val v = readLine()!!
		   if(v.equals("solved")) {
		       solved = true
		       return
		   }
		   done.put(p,done[p]!! + (1 shl L))
		   if(v.equals("ok")){
		       dothing(Pair(p.first-1,p.second),L)
		   }
		}
		if(solved) return
		//try D
		if((incoming != U) && ((i and (1 shl D)) == 0) && !seen.contains(Pair(p.first,p.second+-1))){
		   println("down")
		   System.out.flush()
		   val v = readLine()!!
		   if(v.equals("solved")) {
		       solved = true
		       return
		   }
		   done.put(p,done[p]!! + (1 shl D))
		   if(v.equals("ok")){
		       dothing(Pair(p.first,p.second-1),D)
		   }
		}
		if(solved) return
		//try R
		if((incoming != L) && ((i and (1 shl R)) == 0) && !seen.contains(Pair(p.first+1,p.second))){
		   println("right")
		   System.out.flush()
		   val v = readLine()!!
		   if(v.equals("solved")) {
		       solved = true
		       return
		   }
		   done.put(p,done[p]!! + (1 shl R))
		   if(v.equals("ok")){
		       dothing(Pair(p.first+1,p.second),R)
		   }
		}
		if(solved) return
		//backtrack
		if(incoming == -1){
		   println("no way out")
		   System.out.flush()
		   val v = readLine()!!
		   return
		}

		println(strings[(incoming+2)%4])
		System.out.flush()
		val ok = readLine()!! //should be "ok"
		//seen.remove(p)

	}

	dothing(Pair(0,0),-1)
}
