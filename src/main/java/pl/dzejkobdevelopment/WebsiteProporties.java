package pl.dzejkobdevelopment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

/**
 * Created by Jakub Michałowski on 22.10.2017.
 * All rights reserved.
 */
public class WebsiteProporties {
    @Getter @Setter String title;

    @Getter @Setter String bannerTitle;

    @Getter @Setter String bannerSubtitle;

    public WebsiteProporties(){
        bannerTitle = "Baner";
        bannerSubtitle = "Tekst pod banerem";
    }
    public void initModel(Model model){
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        WebsiteProporties websiteProporties = (WebsiteProporties)context.getBean("websiteProporties");

        model.addAttribute("bannerTitle", getBannerTitle());
        model.addAttribute("bannerSubtitle", getBannerSubtitle());
    }
}
