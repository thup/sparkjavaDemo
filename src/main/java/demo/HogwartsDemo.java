package demo;

/**
 * @Author tlibn
 * @Date 2020/6/10 14:59
 **/

import static spark.Spark.*;

public class HogwartsDemo {

    //霍格沃兹演示demo路由
    public static void router() {


        post("/hgpost", (request, response) -> {
            System.out.println("psot" + request.body() );
            //psot{
            //	"a":"12",
            //	"b":"13",
            //	"c":"123"
            //}

            return request.body();
        });

        //http://localhost:4567/hello/霍格沃兹测试学院
        get("/hello/:name", (request, response) -> {

            String path = request.params(":name");

            return "Hello: " + path;
        });

        // matches "GET /say/hello/to/world"
        // request.splat()[0] is 'hello' and request.splat()[1] 'world'
        get("/say/*/to/*", (request, response) -> {
            return "Number of splat parameters: " + request.splat().length;
        });


        //停止服务
        get("/stop", (request, response) -> {

            stop();
            return "服务已停止";
        });

        get("/bar", (request, response) -> {
            response.redirect("/stop");
            return "重定向";
        });

        notFound("<html><body><h1>Custom 404 handling</h1></body></html>");

        // Using Route
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 404\"}";
        });

        // Using string/html
        internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");

        // Using Route
        internalServerError((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 500 handling\"}";
        });

        get("/throwexception", (request, response) -> {
            throw new YourCustomException();
        });
        get("/hogwartsException", (request, response) -> {
            throw new HogwartsException();
        });


        get("/helloJson", "application/json", (request, response) -> {
            return new MyMessage("Hello World");
        }, new JsonTransformer());

        exception(YourCustomException.class, (exception, request, response) -> {
            System.out.println("YourCustomException");
        });
        exception(HogwartsException.class, (exception, request, response) -> {
            System.out.println("HogwartsException");
        });
        exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
            System.out.println("Exception");
        });
    }
}