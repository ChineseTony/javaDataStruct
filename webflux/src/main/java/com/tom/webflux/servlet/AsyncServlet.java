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


/**
 * @author WangTao
 */
@WebServlet(urlPatterns = "/test",asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        AsyncContext asyncContext = req.startAsync();
        CompletableFuture.runAsync( () ->{
                doSomething(asyncContext);
        });
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
