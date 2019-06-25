package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalTime;

public class Post {
    private String mContent;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    private String creation;
    private int id;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm");

    public Post(String content){
        mContent = content;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        this.creation = LocalDateTime.now().format(df);
        instances.add(this);
        this.id = instances.size();
    }

//    Post newPost = new Post("This is a test post for testing");

    //Getters
    public String getContent(){
        return mContent;
    }

    public static ArrayList<Post> getAll() {
        return instances;
    }

    public String getTime() {
        return creation;
    }

    public static void clearAllPosts() {
        instances.clear();
    }

    public boolean getPublished () {
        return published;
    }

    public static Post setUpNewPost(){
        return new Post("day 1");
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public static Post findById(int id) {
        return instances.get(id-1);
    }

    public void update(String content){
        this.mContent = content;
    }

    public static void deletePost(int id){
        instances.remove(id-1);
    }
}
