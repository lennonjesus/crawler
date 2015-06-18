package com.cortexintelligence.app.helper;

import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import com.cortexintelligence.app.exceptions.TipoInvalidoException;
import org.apache.commons.lang.ArrayUtils;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class ConsoleHelper {

    private Tipo tipo;

    public void init(String ... args) {

        if (args == null || args.length < 2) {
            throw new ParametrosInsuficientesException();
        }

        tipo = Tipo.from(args[0]);

        if (tipo == null) {
            throw new TipoInvalidoException();
        }

        ArrayUtils.removeElement(args, args[0]);

        tipo.execute(args);

    }

    public static void println(String str) {
        System.out.println(str);
    }
}
