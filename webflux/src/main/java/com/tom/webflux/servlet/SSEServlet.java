package com.tom.webflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * @author WangTao
 */
@WebServlet(urlPatterns = "/sse")
public class SSEServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        //sse
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/event-stream");
        PrintWriter printWriter = resp.getWriter();


        for (int i = 0; i < 5; i++) {
            //指定事件标志
            printWriter.write("evnet:me\n\n");
            //格式 data: 数据 \n\n
            printWriter.write("data:"+i+"\n\n");
            printWriter.flush();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        printWriter.close();

        long endTime = System.currentTimeMillis();
        System.out.println("运行时间"+(endTime - startTime)+"ms");
    }

    private void doSomething(AsyncContext asyncContext){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //异步线程结束
        PrintWriter printWriter = null;
        try {
             printWriter = asyncContext.getResponse().getWriter();
             printWriter.write("hello,I am async servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (printWriter != null){
                printWriter.close();
            }
        }
    }
}
