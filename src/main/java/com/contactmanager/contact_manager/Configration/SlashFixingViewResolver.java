package com.contactmanager.contact_manager.Configration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;

@Component
public class SlashFixingViewResolver extends ThymeleafViewResolver {

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith("/")) {
            viewName = viewName.substring(1);
        }
        return super.loadView(viewName, locale);
    }
}

