package demo;

/**
 * @Author tlibn
 * @Date 2020/6/10 14:59
 **/
import static spark.Spark.*;

//http://localhost:4567/hello
public class HelloWorld {
    public static void main(String[] args) {

        /*int maxThreads = 8;
        threadPool(maxThreads);*/

        port(8080);
        //不用重启服务，就可以添加和删除目录下文件和html页面
        staticFiles.externalLocation("F:\\Googledowndir\\testdir_106_1206_8868\\newman");
        staticFiles.expireTime(600); // ten minutes
        staticFiles.header("Key-1", "Value-1");
        //staticFiles.expireTime(600); // ten minutes
        //您可以使用该staticFiles.location()方法在类路径中为提供静态文件的文件夹分配一个文件夹。请注意，URL中不包含公用目录名称。
        //提供一个文件/public/css/style.css作为http://{host}:{port}/css/style.css
        // root is 'src/main/resources', so put files in 'src/main/resources/public'

        //public目录下html不能打开，其他文件可以下载
        //只有下载了一个文件后，html才能正常显示？？

        ////需要重启服务，就可以添加和删除目录下文件和html页面，而且有未知bug
        //如子目录newman下不能正常下载和展示html
        staticFiles.location("/public"); // Static files


        get("/hello", (req, res) -> "Hello World");

        //IllegalStateException: This must be done before route mapping has begun
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        //staticFiles.location("/public"); // Static files

        //http://localhost:4567/hello/霍格沃兹测试学院
        get("/init", (request, response) -> {
            init();
            return "Hello init()";
        });


        before("/*", (q, a) -> {

            System.out.println("111");
            //可以指定初始化失败时应该发生的情况：默认行为是登录并关闭：
            //initExceptionHandler((e) -> System.out.println("Uh-oh"));
        });

        before((request, response) -> {

            System.out.println("222");
            boolean authenticated = true;
            // ... check if authenticated
            if (!authenticated) {
                halt(401, "You are not welcome here");
            }
        });

        before("/protected/*", (request, response) -> {

            System.out.println("333");
            // ... check if authenticated
            halt(401, "Go Away!");
        });

        redirect.get("/fromPath", "/toPath");

        get("/toPath", (req, res) -> "Hello toPath");

        //IllegalStateException: This must be done before route mapping has begun
        //initExceptionHandler((e) -> System.out.println("Uh-oh"));

        HogwartsDemo.router();
    }
}