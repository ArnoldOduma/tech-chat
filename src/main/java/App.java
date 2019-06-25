import static spark.Spark.*;

import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
//import java.util.ne

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main (String[] args) {
        staticFileLocation("/public");

        Post.setUpNewPost();
        Post.setUpNewPost();

        get("/",(req,res) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Post> posts = Post.getAll();
//            Post newPost = new Post("This is a test ");
            model.put("posts",posts);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/new/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPost = Integer.parseInt(request.params(":id"));
            Post found = Post.findById(idOfPost);
            model.put("post", found);
            return new ModelAndView(model,"more-info.hbs");
        },new HandlebarsTemplateEngine());

        get("/home",(req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("username", req.session().attribute("username"));
            return new ModelAndView(model,"home.hbs");
        }, new HandlebarsTemplateEngine());

        post("/welcome",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String inputedUsername = request.queryParams("username");
            request.session().attribute("username", inputedUsername);
            model.put("username",inputedUsername);
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/welcome",(req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("username", req.session().attribute("username"));
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String content = req.queryParams("content1");
            Post newPost = new Post(content);
            model.put("username", req.session().attribute("username"));
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        get("/new",(req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Post> posts = Post.getAll();
//            Post newPost = new Post("1");
//            String time = newPost.getTime();
            model.put("username", req.session().attribute("username"));
            model.put("posts", posts);
//            model.put("time", time);
            return new ModelAndView( model, "post.hbs" );
        }, new HandlebarsTemplateEngine());

        get("/posts/:id/update", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            model.put("editPost",editPost);
            return new ModelAndView(model,"newpost-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/posts/:id/update", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(req.queryParams("content1"));
            model.put("editPost",editPost);
            return new ModelAndView(model,"newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/post/delete/:id", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfPostToDelete = Integer.parseInt(req.params(":id"));
            System.out.println("------------------------------\n" +
                    "This is the debugger"+idOfPostToDelete);
            Post.deletePost(idOfPostToDelete);
            model.put("editPost",idOfPostToDelete);
            return new ModelAndView(model,"post.hbs");
        }, new HandlebarsTemplateEngine());
        get("/posts/delete", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            Post.clearAllPosts();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
