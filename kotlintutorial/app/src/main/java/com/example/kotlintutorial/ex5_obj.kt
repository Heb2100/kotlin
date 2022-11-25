package com.example.marathon

// object

object CarFactory{

    val cars = mutableListOf<Car>()

    fun makeCar(horsepower: Int) : Car {
        val car = Car(horsepower)
        cars.add(car)
        return car
    }
}
//  object 는 앱이 시작될때 단 한번만 실행된다.

data class Car (val horsepower : Int)

fun main(){
    val car = CarFactory.makeCar(horsepower = 10)
    val car1 = CarFactory.makeCar(horsepower = 200)

    println(car)
    println(CarFactory)
    println(CarFactory.cars.size.toString())
//    object만 반환시키면 해쉬주소를 준다.
//    반면 dataclass instance 를 반환시키면 친절히 준다.
}