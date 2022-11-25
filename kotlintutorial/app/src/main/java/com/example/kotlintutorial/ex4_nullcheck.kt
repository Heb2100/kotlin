package com.example.marathon

fun main(){
    nullcheck()
    ignorenull("is this null?")
}

fun nullcheck(){
    val nullname : String? = null
//    단순한 string 데이터타입으로는 null을 담을 수 없다.

    var name : String = "hyunbok"
    var nameinuppercase : String = name.uppercase()
    println(nameinuppercase)

    var nullnameinuppercase = nullname?.uppercase()
    println(nullnameinuppercase)
//  [?]  메소드를 붙이려 하는데 앞에 null이 나올수도 있다면(이 경우에선 nullname) ?를 붙여줘야 한다.

    val lastname = null
    val fullname = name + " " + (lastname?: "No last name")
    println(fullname)
//  [?:]  합치려고 하는데 앞에 null이 나올수도 있다면 대처법을 엘비스 연산자로 제시해줘야 한다.
}

fun ignorenull(str : String?){ // str로 null이 들어올수도 있지롱

    val mnotnull : String = str!!
//    하늘이 두쪽나도 난 null이 아니야!
//    사용을 지양해야함. 여기 null이 들어가면 바로 nullpointerexception발생 다멈춤
    val mnotnull2 : String? = str
//    그래 str 따라서 받는애도 null일수도 있어

    val notnulluppercase = mnotnull.uppercase()
    val notnull2uppercase = mnotnull2?.uppercase()

    val email : String? = "hyunbok2@gist.ac.kr"
    email?.let{
        println("my email is $it")
    }
//    email 이 null이 아니라면 let 이하를 해라 라는 뜻



}

