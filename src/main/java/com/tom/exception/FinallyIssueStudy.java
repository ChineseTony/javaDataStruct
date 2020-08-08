package com.tom.exception;

/**
 * @author WangTao
 */
public class FinallyIssueStudy {



    public static void main(String[] args)throws Exception {
        wrongUse();
    }

    public static void wrongUse() throws Exception{
        TestResource testResource = new TestResource();
        try{
            testResource.read();
        }finally {
            testResource.close();
        }
    }

    public static void rightUse() throws Exception{
        try(TestResource resource = new TestResource()){
            resource.read();
        }
    }
}
