package br.com.valhala.agenda.config.inicializacao;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import br.com.valhala.agenda.jpa.ContatoRepository;
import br.com.valhala.agenda.service.ContatoService;
import br.com.valhala.agenda.web.controller.ContatoController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { ContatoController.class, ContatoService.class, ContatoRepository.class })
public class ConfiguracaoWeb extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ConfiguracaoWeb() {
        super();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/fontawesome/**").addResourceLocations("/resources/fontawesome/");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringTemplateEngine templateEgine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEgine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[] { "*.html", "*.xhtml" });
        return viewResolver;
    }

    /*
     * @Bean
     * @Scope("prototype")
     * public ThymeleafView mainView() {
     * ThymeleafView view = new ThymeleafView("main"); // templateName = 'main'
     * view.setStaticVariables(
     * Collections.singletonMap("footer", "The ACME Fruit Company"));
     * return view;
     * }
     */

}
