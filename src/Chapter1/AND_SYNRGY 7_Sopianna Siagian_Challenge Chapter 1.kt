package Chapter1

fun main() {
    val bentuk = BentukChallenge()
    bentuk.start()
}

class BentukChallenge {
    private val menu = listOf(
        Segitiga(),
        SegitigaTerbalik(),
        Diamond(),
        X(),
        SegitigaBerongga(),

    )

    fun start() {
        var lanjutkan = true

        while (lanjutkan) {
            println("Masukkan jumlah baris yang dibutuhkan (n):")
            val n = readLine()?.toIntOrNull() ?: return

            println("Pilih Bentuk:")
            menu.forEachIndexed { index, shape ->
                println("${index + 1}. ${shape.name}")
            }
            println("6. Exit")

            val choice = readLine()?.toIntOrNull() ?: return

            when (choice) {
                in 1..menu.size -> {
                    menu[choice - 1].draw(n)
                }
                menu.size + 1 -> {
                    println("Terima Kasih")
                    lanjutkan = false
                }
                else -> println("Pilihan tidak valid.")
            }

            if (lanjutkan) {
                println("Apakah Anda ingin memilih menu lagi? (ya/tidak)")
                val input = readLine()
                if (input?.toLowerCase() != "ya") {
                    println("Terima kasih!")
                    lanjutkan = false
                }
            }
        }
    }
}

abstract class Bentuk(val name: String) {
    abstract fun draw(n: Int)
    fun printYellowStar(){
        val yellowColorCode = "\u001B[33m"
        val resetColorCode = "\u001B[0m"
        print(" $yellowColorCode*$resetColorCode ")
    }
}

class Segitiga : Bentuk("Segitiga"){
    override fun draw(n: Int) {
        for (i in 1..n) {
            repeat(n - i) { print(" ") }
            repeat(2 * i - 1) { print("*") }
            println()
        }
    }
}

class SegitigaTerbalik : Bentuk("Segitiga Terbalik"){
    override fun draw(n: Int) {
        for (i in n downTo 1) {
            repeat(n - i) { print(" ") }
            repeat(2 * i - 1) { print("*") }
            println()
        }
    }
}

class Diamond : Bentuk("Diamond") {
    override fun draw(n: Int) {
        for (i in 1..n) {
            repeat(n - i) { print(" ") }
            repeat(2 * i - 1) { print("*") }
            println()
        }
        for (i in n - 1 downTo 1) {
            repeat(n - i) { print(" ") }
            repeat(2 * i - 1) { print("*") }
            println()
        }
    }
}

class X : Bentuk("X") {
    override fun draw(n: Int) {
        for (i in 1..n) {
            for (j in 1..n) {
                print(if (i == j || i + j == n + 1) "*" else " ")
            }
            println()
        }
    }
}

class SegitigaBerongga : Bentuk("Segitiga Berongga") {
    override fun draw(n: Int) {
        for (i in 1..n) {
            for (j in 1..n - i) {
                print("  ")
            }
            for (j in 1..2 * i - 1) {
                if (j == 1 || j == 2 * i - 1) {
                    printYellowStar()
                    if(i == n){
                        repeat(n + 1){printYellowStar()}
                        break
                    }
                } else {
                    print("  ")
                }
            }
            println()
        }
    }
}
