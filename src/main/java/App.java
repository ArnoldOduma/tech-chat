import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main (String[] args) {
        staticFileLocation("/public");
        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model,"home.hbs");
        },new HandlebarsTemplateEngine());

        post("/welcome",(request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String inputedUsername = request.queryParams("username");
            request.session().attribute("username", inputedUsername);
            model.put("username",inputedUsername);
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
