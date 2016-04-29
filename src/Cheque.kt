/**

 * Created by Ps-Luan on 29/04/2016.
 */


class Cheque(val valor: Double) {


    private val prefixos = listOf("real" to "reais", "mil" to "mil", "milhão" to "milhões", "bilhão" to "bilhões")

    private fun getUnidades(unidade: Int) = when (unidade) {
        1 -> "um"
        2 -> "dois"
        3 -> "tres"
        4 -> "quatro"
        5 -> "cinco"
        6 -> "seis"
        7 -> "sete"
        8 -> "oito"
        9 -> "nove"
        else -> "zero"
    }

    private fun getDezenasEspeciais(dezena: Int) = when (dezena) {
        11 -> "onze"
        12 -> "doze"
        13 -> "treze"
        14 -> "quatorze"
        15 -> "quinze"
        16 -> "dezesseis"
        17 -> "dezessete"
        18 -> "dezoito"
        19 -> "dezenove"
        else -> ""
    }


    private fun getDezenas(dezena: Int) = when (dezena) {
        in 11..19 -> getDezenasEspeciais(dezena)
        else -> when (dezena / 10) {
            2 -> "vinte"
            3 -> "trinta"
            4 -> "quarenta"
            5 -> "cincuenta"
            6 -> "sessenta"
            7 -> "setenta"
            8 -> "oitenta"
            9 -> "noventa"
            else -> ""
        } + resolverUnidade(dezena % 10, dezena / 10)
    }

    private fun getCentenas(centena: Int) = when (centena) {
        100 -> "cem"
        else -> when (centena / 100) {
            1 -> "cento"
            2 -> "duzentos"
            3 -> "trezentos"
            4 -> "quatrocentos"
            5 -> "quinhentos"
            6 -> "seissentos"
            7 -> "setecentos"
            8 -> "oitocentos"
            9 -> "novecentos"
            else -> ""
        } + resolverDezena(centena % 100, centena / 100)
    }


    private fun resolverUnidade(unidade: Int, dezena: Int): String = if (unidade == 0) {
        ""
    } else {
        if (dezena == 0) {
            ""
        } else {
            " e "
        } + "${getUnidades(unidade)}"
    }

    private fun resolverDezena(dezena: Int, centena: Int) = if (dezena == 0) {
        ""
    } else {
        if (centena == 0) {
            ""
        } else {
            " e "
        } + "${getDezenas(dezena)}"
    }

    private fun resolverCentena(centena: Int) = getCentenas(centena)

    private fun extractAndHumanizeCentavos(): String {
        val centavos = Math.round((valor % 1) * 100.0).toInt()

        return if(centavos == 0){
            ""
        }else{
            "${resolverCentena(centavos)} centavo${if (centavos > 1 ) "s" else ""}";
        }
    }

    private fun extractAndHumanizeReais() : List<String>{

        var reais = valor.toInt()
        val partes = arrayListOf<String>()
        var prefixoIndex = 0

        while(reais > 0){
            val centena = reais % 1000
            reais /= 1000

            if(centena > 0) {

                if(centena == 1 && prefixoIndex == 1){
                    partes.add("mil")
                }else {
                    val prefixo = if (centena == 1) {
                        prefixos[prefixoIndex].first
                    } else {
                        prefixos[prefixoIndex].second
                    }

                    partes.add(0, "${resolverCentena(centena)} ${prefixo}")
                }
            }
            prefixoIndex++

        }

        return partes


    }

    fun humanize(): String {

        var partes = (extractAndHumanizeReais() + extractAndHumanizeCentavos()).filter{ it != "" };

        var resultado = when(partes.size){
            0 -> ""
            1 -> partes[0]
            else -> partes.subList(0, partes.size -1).joinToString(", ") + " e " + partes.last()
        }

        if(!resultado.contains("centavo") && !(resultado.contains("reais") || resultado.contains("real"))){
            resultado += " reais";
        }

        return resultado

    }

}
