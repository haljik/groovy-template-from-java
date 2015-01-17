package example;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haljik on 15/01/17.
 */
public class Application {

    public static void main(String ...args) {
        SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
        templateEngine.setVerbose(true);

        Map<String,User> binding = new HashMap<>();
        binding.put("user", new User());
        try {
            Template template = templateEngine.createTemplate(
                    "こんにちは ${user.name}!\n" +
                    "お気に入りの一覧\n" +
                    "<% user.favorites.each{ out <<  '* '+ it  + '\\n'}%>"
            );

            System.out.println(template.make(binding));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class User {
        String name = "haljik";

        List<String> favorites = Arrays.asList(new String[]{"java","groovy","scala", "kotlin"});

        public String getName() {
            return name;
        }

        public List<String> getFavorites() {
            return favorites;
        }
    }
}
