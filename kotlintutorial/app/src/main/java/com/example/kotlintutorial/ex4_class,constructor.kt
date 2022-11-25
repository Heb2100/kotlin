package com.example.marathon

fun main(){
    val Human = human()
//    인스턴스화를 꼭 한담에 써야한다!
    Human.eatingcake()
    println("this human name is ${Human.name}")

    val Human2 = human2("minsu")
    Human2.eatingcake()

    val Human3 = human3("HB", 25)
    Human3.eatingcake()

    val korean = Korean()
    korean.singasong()
}

class human{
    val name = "HB"
    fun eatingcake(){
        println("eating cake is so good~~")
    }
}

class human2(Str : String="anonymous"){
//    생성자만 써있으면 생성자를 쓰는게 강제되지만, 생성자가 설정되어있으면 안써도 기본값으로 사용된다.
//    생성자 설정이 되어있다면 constructor 도 생략해도 무방하다.
//    사실 constructor 자체가 필요없긴 하다... ㅋㅋ

    val name = Str
    fun eatingcake(){
        println("$name love eating cake!")
    }
}

open class human3(val name : String="anonymous"){

    constructor(name : String, age : Int):this(name) {
        println("my name is $name and $age years old")
    }
//   부생성자를 만들때는 주생성자를 꼭 상속받아야 인정받는다.
//   부생성자에만 존재하는 변수는 constructor 지역변수이다.

    init{
        println("New human has been borned!")
    }
    fun eatingcake(){
        println("$name love eating cake!")
    }
    open fun singasong(){
        println("alalla")
    }
}
//  kotlin의 class와 함수는 기본적으로 final 상태. 상속하고싶으면 open 을 해줘야한다.
class Korean : human3(){
    override fun singasong(){
        super.singasong()
        println("hahaha what a lovely life!")
        println("my name is $name")
    }
}
//  상속은 java와 같게 하나밖에 못하고, implement 는 여러개 가능하다.