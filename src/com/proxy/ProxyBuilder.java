package com.proxy;

import com.mathworks.jmi.Matlab;

import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;


public class ProxyBuilder {

    public final class Handler implements InvocationHandler{

        // these are the methods declared by the interface
        public final Set<String> interfaceMethods = new HashSet<>();

        // map method names to Matlab commands
        public final HashMap<String,String> invocationMap = new HashMap<>();

        public Handler(Class classObj){

            // generate list of methods implemented by this class
            Arrays.stream(classObj.getDeclaredMethods())
                    .forEach(m -> this.interfaceMethods.add(m.getName()));

            // comfort signal
            this.interfaceMethods.stream()
                    .forEach(m -> System.out.println("added interface method: " + m));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // blab about what function was invoked
            //System.out.println("method invocation: "+method.toGenericString());

            // figure out number of args
            int numArgs = (args == null)? 0:args.length;

            // print out the args
            //for (int i = 0; i < numArgs; i++){
            //    System.out.println("arg"+i+": "+args[i]);
            //}

            // if this is a non interface method then invoke and return
            if (!this.interfaceMethods.contains(method.getName())){ return method.invoke(this,args); }

            // if there is no callback then we're done
            if (!this.invocationMap.containsKey(method.getName())){
                System.out.println("callback map does not contain key for "+method.getName());
                return null;
            }

            // retreieve the function to call in Matlab
            String func = this.invocationMap.get(method.getName());

            // if the func is anonymous function then need to mod args
            if (func.startsWith("@")){

                System.out.println("processing anonymous function: "+func);

                // allocate new args with 1 extra argment
                Object[] nargs = (numArgs > 0) ? (new Object[numArgs + 1]) : null;

                if (numArgs > 0 ) {

                    // set first arg to be anonymous function string
                    nargs[0] = func;

                    // copy the original args back in
                    System.arraycopy(args, 0, nargs, 1, args.length);

                    // forward these new args to jeval.m function
                    //     function out = jeval( fstr,varargin )
                    //        out = feval(str2func(fstr),varargin{:});
                    //     end

                    // print out the final version of the args
                    //for (int i = 0; i < nargs.length; i++) {
                    //    System.out.println("narg" + i + ": " + nargs[i]);
                    //}
                }

                Matlab.whenMatlabIdle(() -> {
                    try {
                        Matlab.mtFevalConsoleOutput("jeval",nargs,0);
                    }catch (Exception e){e.printStackTrace();}
                });

            } else {

                Matlab.whenMatlabIdle(() -> {
                    try {
                        Matlab.mtFevalConsoleOutput(func, args, 0);
                    } catch (Exception e) { e.printStackTrace(); }
                });
            }

            // we're done
            return null;
        }
    }

    public Object buildProxy(Class classObj){
        // make sure it is an interface
        if (!classObj.isInterface()){
            System.out.println(classObj.toGenericString()+" is not an interface");
            return null;
        }
        return Proxy.newProxyInstance( classObj.getClassLoader(), new Class[]{classObj}, new Handler(classObj));
    }
}
