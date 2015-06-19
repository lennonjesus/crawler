package com.cortexintelligence.app.helper.console;

import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import com.cortexintelligence.app.exceptions.TipoInvalidoException;
import com.cortexintelligence.app.crawler.TipoCrawler;
import org.apache.commons.lang.ArrayUtils;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class ConsoleHelper {

    public void init(String ... args) {

        if (args == null || args.length < 2) {
            throw new ParametrosInsuficientesException();
        }

        TipoCrawler tipoCrawler = TipoCrawler.from(args[0]);

        if (tipoCrawler == null) {
            throw new TipoInvalidoException();
        }

        ArrayUtils.removeElement(args, args[0]);

        tipoCrawler.execute(args);

    }

}
