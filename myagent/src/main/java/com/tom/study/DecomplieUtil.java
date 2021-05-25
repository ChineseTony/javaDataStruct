package com.tom.study;

import org.benf.cfr.reader.api.CfrDriver;
import org.benf.cfr.reader.api.OutputSinkFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author wangtao
 * @date 2021/5/23
 */
public class DecomplieUtil {

    private DecomplieUtil(){

    }

    public static String decompile(String source, String targetPath) throws IOException {
        Long start = System.currentTimeMillis();
        // source jar
        List<String> files = new ArrayList<>();
        String path =
                Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        String classPath = path+source.replace(".","/")+".class";
        files.add(classPath);

        final StringBuilder result = new StringBuilder(8192);

        OutputSinkFactory mySink = new OutputSinkFactory() {
            @Override
            public List<SinkClass> getSupportedSinks(SinkType sinkType, Collection<SinkClass> collection) {
                return Arrays.asList(SinkClass.STRING, SinkClass.DECOMPILED, SinkClass.DECOMPILED_MULTIVER,
                        SinkClass.EXCEPTION_MESSAGE);
            }

            @Override
            public <T> Sink<T> getSink(final SinkType sinkType, SinkClass sinkClass) {
                return new Sink<T>() {
                    @Override
                    public void write(T sinkable) {
                        if (sinkType == SinkType.PROGRESS) {
                            return;
                        }
                        result.append(sinkable);
                    }
                };
            }
        };

        CfrDriver cfrDriver = new CfrDriver.Builder().withOutputSink(mySink).build();
        cfrDriver.analyse(files);
        Long end = System.currentTimeMillis();
        System.out.println(String.format("decompiler time: %dms", (end - start)));
        return result.toString();
    }
}
