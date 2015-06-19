package com.cortexintelligence.app.helper.console;

import com.cortexintelligence.app.crawler.TipoCrawler;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import com.cortexintelligence.app.exceptions.TipoInvalidoException;
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

        tipoCrawler.execute((String[]) ArrayUtils.remove(args, 0));

    }

}
