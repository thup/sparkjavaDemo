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

        //停止服务
        get("/stop", (request, response) -> {

            stop();

            return "服务已停止";
        });

    }
}