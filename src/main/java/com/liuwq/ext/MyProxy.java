package com.liuwq.ext;

import com.liuwq.service.OrderService;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {
    private static String rt = "\r\t";
    private static String rrt = "\r\r\t";
    private static String rtt = "\r\t\t";
    private static String r = "\r";
    private static String rr = "\r\r";

    public static Object newProxyInstance(JavaClassLoader javaClassLoader,
                                          Class<?> classInfo,
                                          ExtMyInvocationHandler h) {
        //1.拼接代理类的源代码
        try {

            // 1.创建代理类java源码文件,写入到硬盘中..
            Method[] methods = classInfo.getMethods();
            StringBuffer proxyClass = new StringBuffer();
            proxyClass.append("package com.liuwq.ext;").append(rr)
                    .append("import java.lang.reflect.Method;").append(r)
                    .append("import com.liuwq.ext.ExtMyInvocationHandler;").append(rr)
                    .append("public class $Proxy0 implements ").append(classInfo.getName()).append(" {").append(rrt)
                    .append("ExtMyInvocationHandler h;").append(rrt)
                    .append("public $Proxy0(ExtMyInvocationHandler h)").append("{").append(rtt)
                    .append("this.h = h;").append(rt)
                    .append("}").append(rt)
                    .append(getMethodString(methods, classInfo)).append(r)
                    .append("}");

            // 2. 写入到到本地文件中..
            String filename = "d:/code/$Proxy0.java";
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            fw.write(proxyClass);
            fw.flush();
            fw.close();

            // 3. 将源代码编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(filename);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            // 4.使用classLoader 加载到内存中..
            Class<?> $Proxy0 = javaClassLoader.findClass("$Proxy0");

            // 5.指明初始化有参数构造函数
            Constructor<?> constructor = $Proxy0.getConstructor(ExtMyInvocationHandler.class);
            Object o = constructor.newInstance(h);

            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMethodString(Method[] methods, Class intf) {
        StringBuffer proxyMe = new StringBuffer();
        for (Method method : methods) {
            proxyMe.append(rt).append("public String ").append(method.getName()).append("() throws Throwable {").append(rtt)
                   .append("Method md = ").append(intf.getName()).append(".class.getMethod(\"").append(method.getName()).append("\", new Class[]{});").append(rtt)
                   .append("this.h.invoke(this, md, null);").append(rtt)
                    .append("return \"success\";").append(rt)
                    .append("}").append(rt);
        }
        return proxyMe.toString();
    }

}
