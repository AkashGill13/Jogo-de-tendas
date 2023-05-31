import java.io.File
var stringVazia = ""
val answer = "Data invalida"
val menor = "Menor de idade nao pode jogar"

fun criaMenu(): String {
    return "\nBem vindo ao jogo das tendas\n\n1 - Novo jogo\n0 - Sair\n"
}

fun verificar(): String {  //--vVERIFICAR O INPUT DE UTILIZADOR---
    var opcao: String?
    do {

        opcao = readln()
        if (opcao != "1" && opcao != "0") {
            println("Opcao invalida")
            println(criaMenu())
        }

    } while (opcao != "1" && opcao != "0")
    return opcao
}

fun verificarLinhas(): Int {
    var numeroLinhas: Int?
    do {
        println("Quantas linhas?")
        numeroLinhas = readln().toIntOrNull()
        if (numeroLinhas == null || numeroLinhas <= 0) {
            println("Resposta invalida")
        }


    } while (numeroLinhas == null || numeroLinhas <= 0)
    return numeroLinhas
}

fun verificarColunas(): Int {
    var numeroColunas: Int?
    do {
        println("Quantas colunas?")
        numeroColunas = readln().toIntOrNull()
        if (numeroColunas == null || numeroColunas <= 0) {
            println("Resposta invalida")
        }

    } while (numeroColunas == null || numeroColunas <= 0)
    return numeroColunas
}

fun validaTamanhoMapa(numLinhas: Int, numColunas: Int): Boolean {
    return when {
        numLinhas == 6 && numColunas == 5 -> true
        numLinhas == 6 && numColunas == 6 -> true
        numLinhas == 8 && numColunas == 8 -> true
        numLinhas == 8 && numColunas == 10 -> true
        numLinhas == 10 && numColunas == 8 -> true
        numLinhas == 10 && numColunas == 10 -> true

        else -> false
    }
}

fun eBissexto(ano: Int): Boolean {
    if (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
        return true
    }
    return false;
}

fun eMenorDeIdade(data: String?): String? {


    if (data == null) {
        return answer
    }
    val dia: String = data[0].toString() + data[1].toString()
    val mes: String = data[3].toString() + data[4].toString()
    val ano: String = data[6].toString() + data[7].toString() + data[8].toString() + data[9].toString()
    if (mes.toInt() == 4 || mes.toInt() == 6 || mes.toInt() == 9 || mes.toInt() == 11) {
        if ((dia.toInt() in 1..30) && (mes.toInt() in 11..12) && ano.toInt() >= 2004) {
            return menor
        }
    } else if (mes.toInt() == 1 || mes.toInt() == 3 || mes.toInt() == 5 || mes.toInt() == 7 || mes.toInt() == 8) {
        if (mes.toInt() == 10 || mes.toInt() == 12) {
            if ((dia.toInt() in 1..31) && (mes.toInt() in 11..12) && ano.toInt() >= 2004) {
                return menor
            }
        } else if (mes.toInt() == 2 && eBissexto(ano.toInt())) {

            if (((dia.toInt() in 1..29) && (mes.toInt() in 11..12) && ano.toInt() >= 2004)) {
                return menor
            }
        } else if (mes.toInt() == 2 && !eBissexto(ano.toInt())) {
            if ((dia.toInt() in 1..28) && (mes.toInt() in 11..12) && ano.toInt() >= 2004) {
                return menor
            }
        }

    }

    return null
}

fun vermes31(data: String): Boolean {
    val mes1 = ("${data[3]}${data[4]}").toString().toInt()
    if (mes1 == 1 || mes1 == 3 || mes1 == 5) {
        return true
    }
    return false
}

fun validaDataNascimento(data: String?): String? {
    val answer = "Data invalida"
    val menor = "Menor de idade nao pode jogar"
    val espaco = null

    if (data == null || data.length != 10) {
        return answer
    }
    val dia: String = ("${data[0]}${data[1]}").toString()
    val mes: String = ("${data[3]}${data[4]}").toString()
    val ano: String = ("${data[6]}${data[7]}${data[8]}${data[9]}").toString()

    if (eMenorDeIdade(data) != null) {
        return menor
    }

    if (ano.toInt() !in 1900..2022) {
        return menor
    }
    if (mes.toInt() == 2 && eBissexto(ano.toInt())) {
        if (dia.toInt() >= 1 && (dia.toInt() <= 29)) {
            return espaco
        } else {
            return answer
        }
    }

    if (mes.toInt() == 4 || mes.toInt() == 6 || mes.toInt() == 9 || mes.toInt() == 11) {
        if ((dia.toInt() in 1..30)) {

            return espaco
        } else {
            return answer
        }
    } else if (vermes31(data) || mes.toInt() == 7 || mes.toInt() == 8 || mes.toInt() == 10 || mes.toInt() == 12) {
        if ((dia.toInt() in 1..31)) {
            if (ano.toInt() == 2004 && (mes.toInt() == 11 || mes.toInt() == 12)) {
                return menor
            }
            if (ano.toInt() > 2004) {
                return menor
            }
            if (ano.toInt() == 2004 && mes.toInt() < 11) {
                return espaco
            }
            return espaco
        } else {
            return answer
        }
    } else if (mes.toInt() == 2 && !eBissexto(ano.toInt())) {
        if (dia.toInt() >= 1 && (dia.toInt() <= 28)) {
            return espaco
        } else {
            return answer
        }
    }
    return espaco
}

fun criaLegendaHorizontal(numColunas: Int): String {
    var charact = 'A'
    var contador = 1
    var legenda: String = ""
    while (contador <= numColunas) {
        if (contador != numColunas) {
            legenda += "$charact | "
        } else {
            legenda += "$charact"
        }
        contador++
        charact++
    }
    return legenda
}

fun criaLegendaContadoresHorizontal(contadoresVerticais: Array<Int?>): String {
    var resposta = ""

    for (i in 0 ..  contadoresVerticais.size-1) {
        if (contadoresVerticais[i] == null) {
            resposta += " "
            resposta += "   "
        } else if (i == contadoresVerticais.size-1) {
            resposta += contadoresVerticais[i]
        } else {
            resposta += contadoresVerticais[i]
            resposta += "   "
        }

    }
    return resposta
}

fun leContadoresDoFicheiro(numLines: Int, numColumns: Int, verticais: Boolean): Array<Int?> {
    val terreno = File("${numLines}x${numColumns}.txt").readLines()
    var result: Array<Int?> = arrayOfNulls(numColumns)

    if (verticais == true) {
        for (i in 0 until  numColumns ) {
            val parte = terreno[0].split(",")
            if (i == numColumns - 1 && parte[i].toInt() == 0) {
                result.size == numColumns - 1
                return result
            } else {
                if (parte[i].toInt() == 0) {
                    result[i] = null
                } else {
                    result[i] = parte[i].toInt()
                }
            }
        }

    } else if (verticais == false) {
        result = arrayOfNulls(numLines)
        for (i in 0 until  numLines) {
            val parte = terreno[1].split(",")
            if (parte[i].toInt() == 0) {
                result[i] = null
            } else {
                result[i] = parte[i].toInt()
            }


        }
    }

    return result
}
fun leTerrenoDoFicheiro(numLines: Int, numColumns: Int): Array<Array<String?>> {
    val terreno: Array<Array<String?>> = Array(numLines) { arrayOfNulls<String?>(numColumns) }

    val ficheiro = File("${numLines}x${numColumns}.txt").readLines()
    for (linha in 0 until numLines) {
        for (coluna in 0 until numColumns) {

            if (ficheiro.contains("$linha,$coluna")) {
                terreno[linha][coluna] = "A"
            }
        }
    }
    return terreno
}

fun criaTerreno(terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>?, contadoresHorizontais: Array<Int?>?,
                mostraLegendaHorizontal: Boolean = true, mostraLegendaVertical: Boolean = true): String {

    val arrayDeTerreno: Array<String> = Array(terreno.size,{""})
    var terrenoCompleto = ""

    if (contadoresVerticais != null) {
        arrayDeTerreno[0] += "       ${criaLegendaContadoresHorizontal(contadoresVerticais)}\n"
    }

    if (mostraLegendaHorizontal == true) {
        arrayDeTerreno[0] += "     | ${criaLegendaHorizontal(terreno[0].size)}\n"
    }

    for (linhas in 0 until terreno.size) {
        if (contadoresHorizontais != null) {
            if (contadoresHorizontais[linhas] == null) {
                arrayDeTerreno[linhas] += "  "
            } else if (contadoresHorizontais[linhas] != null) {
                arrayDeTerreno[linhas] += "${contadoresHorizontais[linhas]} "
            } else {
                arrayDeTerreno[linhas] += "  "
            }
        } else {
            arrayDeTerreno[linhas] += "  "
        }

        if (mostraLegendaVertical == true) {
            if (linhas < 9) {
                arrayDeTerreno[linhas] += " ${linhas +1} "
            } else {
                arrayDeTerreno[linhas] += "${linhas +1} "
            }
        } else {
            arrayDeTerreno[linhas] += "   "
        }

        for (coluna in 0 .. terreno[0].size-1) {

            if (terreno[linhas][coluna] == null){
                arrayDeTerreno[linhas] += "|  "
            }
            if (terreno[linhas][coluna] == "A"){
                arrayDeTerreno[linhas] += "| \u25B3"
            }
            if (terreno[linhas][coluna] == "T"){
                arrayDeTerreno[linhas] += "| T"
            }
            if (coluna < terreno[linhas].size - 1) {
                arrayDeTerreno[linhas] += " "
            }

            when (coluna == terreno[linhas].size - 1) {
                true -> {
                    if (linhas < terreno.size - 1){
                        arrayDeTerreno[linhas] += "\n"
                    }
                }
                false -> {
                }
            }
        }
    }
    for (i in 0 .. terreno.size-1) {
        terrenoCompleto += arrayDeTerreno[i]
    }

    return terrenoCompleto
}

fun verificarCoordenadasValidas(coordenadasStr: String?, numLines: Int, numColumns: Int): Boolean {
    when {
        (coordenadasStr != null && coordenadasStr[0] > '0') && (coordenadasStr.length in 3..4) &&
                coordenadasStr[0].isDigit() &&
                coordenadasStr[1] == ',' && (coordenadasStr[2].isLetter() || coordenadasStr[3].isLetter())
                && coordenadasStr[0] <= (numLines.toChar() + 48)
                && coordenadasStr[2] >= 'A' &&
                coordenadasStr[2] <= (numColumns.toChar() + 64) -> return true
        else -> return false
    }
}

fun processaCoordenadas(coordenadasStr: String?, numLines: Int, numColumns: Int): Pair<Int, Int>? {
    if (verificarCoordenadasValidas(coordenadasStr, numLines, numColumns) == true && coordenadasStr != null) {
        val coordx = coordenadasStr[0].code - 49
        val coordy = coordenadasStr[2].code - 65
        val cordPair: Pair<Int, Int> = Pair(coordx, coordy)
        return cordPair

    } else {
        return null
    }
}

fun temArvoreAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {
    val linha = coords.first
    val coluna = coords.second


    if (linha > 0 && terreno[linha - 1][coluna] == "A" ||
        linha < terreno.size - 1 && terreno[linha + 1][coluna] == "A"
    ) {
        return true
    }


    // verifica em lado esquerdo e direito

    if (coluna > 0 && terreno[linha][coluna - 1] == "A" ||
        coluna < terreno[linha].size - 1 && terreno[linha][coluna + 1] == "A"
    ) {
        return true
    }
    return false
}

fun temTendaAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {
    val linha = coords.first
    val coluna = coords.second

    // verifica emcima
    if (linha > 0 && terreno[linha - 1][coluna] == "T") {
        return true
    }

    // verifica embaixo
    if (linha < terreno.size - 1 && terreno[linha + 1][coluna] == "T") {
        return true
    }

    // verifica ao lado esquerdo e direito
    if (coluna > 0 && terreno[linha][coluna - 1] == "T") {
        return true
    }
    if (coluna < terreno[linha].size - 1 && terreno[linha][coluna + 1] == "T") {
        return true
    }

    // verifica diagonal
    if (linha > 0 && coluna > 0 && terreno[linha - 1][coluna - 1] == "T") {
        return true
    }
    if (linha > 0 && coluna < terreno[linha].size - 1 && terreno[linha - 1][coluna + 1] == "T") {
        return true
    }
    if (linha < terreno.size - 1 && coluna > 0 && terreno[linha + 1][coluna - 1] == "T") {
        return true
    }
    if (linha < terreno.size - 1 && coluna < terreno[linha].size - 1 && terreno[linha + 1][coluna + 1] == "T") {
        return true
    }

    return false
}

fun contaTendasColuna(terreno: Array<Array<String?>>, coluna: Int): Int {
    var totalTendasColunas = 0
    for (linha in terreno.indices) {
        if (terreno[linha][coluna] == "T") {
            totalTendasColunas++
        }
    }
    return totalTendasColunas
}

fun contaTendasLinha(terreno: Array<Array<String?>>, linha: Int): Int {
    var totalTendasLinhas = 0

    for (coluna in terreno[linha].indices) {
        if (terreno[linha][coluna] == "T") {
            totalTendasLinhas++
        }
    }
    return totalTendasLinhas
}

fun colocaTenda(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {

    val coordenadaX = coords.first
    val coordenadaY = coords.second
    var resposta = ""


    // Cverificar se existe T na posição
    if (terreno[coordenadaX][coordenadaY] == "T") {
        // Remover tenda
        terreno[coordenadaX][coordenadaY] = null
        return true
    }

    // verificar se está livre a posição
    if (terreno[coordenadaX][coordenadaY] != null) {
        resposta += " "
        return false
    }

    // verifica se existe arvore adjacente na posição
    if (!temArvoreAdjacente(terreno, coords)) {
        return false
    }

    // verificar se existe tenda adjacente
    if (temTendaAdjacente(terreno, coords)) {

        return false
    }

    // Por a tenda
    terreno[coordenadaX][coordenadaY] = "T"

    return true
}


fun terminouJogo(
    terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>,
    contadoresHorizontais: Array<Int?>
): Boolean {

    var tendas = 0
    var numArvores = 0

    for (i in terreno.indices) {
        for (j in terreno[0].indices) {
            if (terreno[i][j] == "A") {
                numArvores++
            } else if (terreno[i][j] == "T") {
                tendas++
            }
        }
    }

    if (tendas == numArvores) {
        for (linha in 0 until terreno.size - 1) {
            if (contaTendasLinha(terreno, linha) != contadoresHorizontais[linha] &&
                !(contaTendasLinha(terreno, linha) == 0 && contadoresHorizontais[linha] == null)
            ) {
                return false
            }

        }
        for (coluna in 0 until terreno[0].size - 1) {
            if (contaTendasColuna(terreno, coluna) != contadoresVerticais[coluna] &&
                !(contaTendasColuna(terreno, coluna) == 0 && contadoresVerticais[coluna] == null)
            ) {
                return false
            }

        }
        return true

    }
    return false




}
fun jogoRepetir(){
    println(criaMenu())
    val verificar = verificar()
    if (verificar == "1") {

        var colunas: Int
        var linhas: Int
        var data: String

        do {
            linhas = verificarLinhas()
            colunas = verificarColunas()
            if (validaTamanhoMapa(linhas, colunas) == false) {
                println("terreno Invalido")
                println(criaMenu())
                verificar()
            }
        } while (validaTamanhoMapa(linhas, colunas) == false)


        if (linhas == 10 && colunas == 10) {
            do {
                println("Qual a sua data de nascimento? (dd-mm-yyyy)")
                data = readLine()!!.toString()
                if (validaDataNascimento(data) == answer) {
                    println(answer)
                }
                if (validaDataNascimento(data) == menor) {
                    println(menor)
                }
            } while (validaDataNascimento(data) == answer ||
                validaDataNascimento(data) == menor
            )

        }
        val leterrno = leTerrenoDoFicheiro(linhas, colunas)
        val contadoresVerticais = leContadoresDoFicheiro(linhas, colunas, true)
        val contadoresHorizontais = leContadoresDoFicheiro(linhas, colunas, false)
        val mapa = criaTerreno(leterrno, contadoresVerticais, contadoresHorizontais)
        println()
        println(mapa)
        do {
            println("Coordenadas da tenda? (ex: 1,B)")
            val read = readln()
            val cordPair = processaCoordenadas(read,linhas,colunas)
            if (read=="sair"){
                return
            }
            if (cordPair!=null){
                if (colocaTenda(leterrno,cordPair)==true){
                    leterrno[cordPair.first][cordPair.second] = "T"
                    println()
                    println(criaTerreno(leterrno,contadoresVerticais,contadoresHorizontais))

                }else{
                    println("Tenda nao pode ser colocada nestas coordenadas")
                }
            }
        }while (terminouJogo(leterrno,contadoresVerticais,contadoresHorizontais)!=true)
        println("Parabens! Terminou o jogo!")

    }
}

fun main() {

    println(criaMenu())
    val verificar = verificar()
    if (verificar == "1") {

        var colunas: Int
        var linhas: Int
        var data: String

        do {
            linhas = verificarLinhas()
            colunas = verificarColunas()
            if (validaTamanhoMapa(linhas, colunas) == false) {
                println("terreno Invalido")
                println(criaMenu())
                verificar()
            }
        } while (validaTamanhoMapa(linhas, colunas) == false)


        if (linhas == 10 && colunas == 10) {
            do {
                println("Qual a sua data de nascimento? (dd-mm-yyyy)")
                data = readLine()!!.toString()
                if (validaDataNascimento(data) == answer) {
                    println(answer)
                }
                if (validaDataNascimento(data) == menor) {
                    println(menor)
                }
            } while (validaDataNascimento(data) == answer ||
                validaDataNascimento(data) == menor
            )

        }
        val leterrno = leTerrenoDoFicheiro(linhas, colunas)
        val contadoresVerticais = leContadoresDoFicheiro(linhas, colunas, true)
        val contadoresHorizontais = leContadoresDoFicheiro(linhas, colunas, false)
        val mapa = criaTerreno(leterrno, contadoresVerticais, contadoresHorizontais)
        println()
        println(mapa)
        do {
            println("Coordenadas da tenda? (ex: 1,B)")
            val read = readln()
            val cordPair = processaCoordenadas(read, linhas, colunas)
            if (read == "sair") {
                return
            }
            if (cordPair != null) {
                if (colocaTenda(leterrno, cordPair) == true) {
                    leterrno[cordPair.first][cordPair.second] = "T"
                    println()
                    println(criaTerreno(leterrno, contadoresVerticais, contadoresHorizontais))

                } else {
                    println("Tenda nao pode ser colocada nestas coordenadas")
                }
            }
        } while (terminouJogo(leterrno, contadoresVerticais, contadoresHorizontais) != true)
        println("Parabens! Terminou o jogo!")
        jogoRepetir()

    }
}