package demo;

/**
 * @Author tlibn
 * @Date 2020/6/10 14:59
 **/
import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}