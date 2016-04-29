import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Ps-Luan on 29/04/2016.
 */

class ChequeTest {

    @Test
    fun umReal() {
        val umReal = Cheque(1.0)
        assertEquals("um real", umReal.humanize())
    }

    @Test
    fun doisReais() {
        val doisReais = Cheque(2.0)
        assertEquals("dois reais", doisReais.humanize())
    }

    @Test
    fun cincoCentavos() {
        val cincoCentavos = Cheque(0.05)
        assertEquals("cinco centavos", cincoCentavos.humanize())
    }

    @Test
    fun centoVinteDoisReais() {
        val centoVinteDoisReais = Cheque(122.22)
        assertEquals("cento e vinte e dois reais e vinte e dois centavos",
                centoVinteDoisReais.humanize())
    }

    @Test
    fun umMilhaoTrintaMileCincoCentavos(){
        val umMilhaoTrintaMileCincoCentavos = Cheque(1030000.05)
        assertEquals("um milhão, trinta mil e cinco centavos",
                umMilhaoTrintaMileCincoCentavos.humanize())
    }

    @Test
    fun centoQuinzeReais() {
        val centoQuinzeReais = Cheque(115.75)
        assertEquals("cento e quinze reais e setenta e cinco centavos", centoQuinzeReais.humanize())
    }

    @Test
    fun centoUmReais() {
        val centoUmReais = Cheque(101.0)
        assertEquals("cento e um reais", centoUmReais.humanize())
    }

    @Test
    fun milReais() {
        val milReais = Cheque(1000.0)
        assertEquals("mil reais", milReais.humanize())
    }
}

