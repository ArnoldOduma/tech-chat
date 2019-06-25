package models;

import org.junit.After;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class PostTest {
    @Test
    public void newPostObject_correctlyInstantiates_true(){
        Post post = new Post("Day 1: Intro");
        assertTrue(post instanceof Post);
    }

    @Test
    public void newPost_instantiatesWithContent_String() {
        Post post = new Post("Day 1: intro");
        assertEquals("Day 1: intro", post.getContent());
    }

    @After
    public void tearDown() {
        Post.clearAllPosts();
    }


    @Test
    public void allPostsCorrectlyReturn_true() {
        Post post = new Post("Day 1: intro");
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2,post.getAll().size());
    }
    @Test
    public void allPostsContainAllPosts_true() {
        Post post = new Post("Day 1: intro");
        Post otherPost = new Post("How to pair successfully");
        assertTrue(Post.getAll().contains(post));
        assertTrue(Post.getAll().contains(otherPost));
    }

    @Test
    public void newPublish__isFalse_false() {
        Post myPost = new Post("Day 1 intro");
        assertEquals(false, myPost.getPublished());
    }

    @Test
    public void getDate_instantiateWithCurrentTime_today() throws Exception{
        setUpNewpost();
        assertEquals(LocalDateTime.now().getDayOfMonth(), setUpNewpost().getCreatedAt().getDayOfMonth());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm");
        assertEquals( LocalDateTime.now().getDayOfWeek(), setUpNewpost().getCreatedAt().getDayOfWeek() );
    }

    public Post setUpNewpost() {
        return new Post("Day 1 intro");
    }

    @Test
    public void getId_postInstantiatedWithId_2() throws  Exception{
        Post.clearAllPosts();
        Post newPost = new Post("123bgnh");
        Post newPosts = new Post("123bgnh");
        assertEquals(2,newPosts.getId());
    }

    @Test
    public void returnCorrectPost_post() throws Exception{
        Post post = setUpNewpost();
        assertEquals(1, Post.findById(post.getId()).getId());
    }

    @Test
    public void returnCorrectPostWhenManyPostExist_post() throws Exception{
        Post post = setUpNewpost();
        Post otherpost = new Post("How to pair successfully");
        Post otherpostYet = new Post("How to pair successfully");
        assertEquals(2, Post.findById(otherpost.getId()).getId());
    }

    @Test
    public void update_changePost() throws Exception {
        Post post = setUpNewpost();
        String formerCont = post.getContent();
        LocalDateTime formerDate = post.getCreatedAt();
        int formerId = post.getId();
        post.update("Android: Day 40");
        assertEquals(formerId, post.getId());
        assertEquals(formerDate, post.getCreatedAt());
        assertNotEquals(formerCont, post.getContent());
    }

    @Test
    public void deleteAPost_deletePost() throws Exception{
        Post post = Post.setUpNewPost();
        Post otherPost = new Post("You will be deleted soon");
        post.deletePost(otherPost.getId()-1);

        assertEquals(1,Post.getAll().size());
    }

    @Test
    public void deleteAllPost_deleteAllPosts() throws Exception{
        Post post = Post.setUpNewPost();
        Post otherPost = new Post("You will be deleted soon");
        Post.clearAllPosts();
        assertEquals(0,Post.getAll().size());
    }
}