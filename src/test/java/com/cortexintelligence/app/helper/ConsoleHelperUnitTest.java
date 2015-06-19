package com.cortexintelligence.app.helper;

import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import com.cortexintelligence.app.exceptions.TipoInvalidoException;
import com.cortexintelligence.app.helper.console.ConsoleHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by lennonjesus on 18/06/15.
 */
@RunWith(JUnit4.class)
public class ConsoleHelperUnitTest {

    private ConsoleHelper console = new ConsoleHelper();

    @Test(expected = ParametrosInsuficientesException.class)
    public void deveCriticarAusenciaDeParametros() {
        console.init();

    }

    @Test(expected = ParametrosInsuficientesException.class)
    public void deveCriticarFonercimentoDeApenasUmParametro() {
        console.init("XXXX");
    }

    @Test(expected = TipoInvalidoException.class)
    public void deveCriticarAusenciaDeTipoValido() {
        console.init("INVALIDO", "BLAH!");
    }

}
