package com.test.admin.testproj.local;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class ReportTestExecution implements MethodRule {
    @Override
    public Statement apply(final Statement base, FrameworkMethod method, final Object target) {
        final String className = method.getMethod().getDeclaringClass().getSimpleName();
        final String methodName = method.getName();
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println(String.format("Sending to Web service:" +
                        "about to execute %s's method %s", className, methodName));
                base.evaluate();
                System.out.println(String.format("Sending to Web service:" +
                        "done executing %s's method %s", className, methodName));
            }
        };
    }
}
