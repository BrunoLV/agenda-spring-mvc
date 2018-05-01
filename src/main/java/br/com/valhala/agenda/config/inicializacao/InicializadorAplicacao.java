package br.com.valhala.agenda.config.inicializacao;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InicializadorAplicacao extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { ConfiguracaoWeb.class, ConfiguracaoJPA.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
