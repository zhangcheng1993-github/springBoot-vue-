package com.base.springboot.common.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

@Aspect
@Configuration
public class TransactionAdviceConfig {

    //声明切面
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.base.springboot.service.*.impl.*.*(..))";


    //事务管理器
    @Autowired
    @Qualifier("transactionManager")
    private PlatformTransactionManager transactionManager;

    //声明通知
    @Bean(name = "txInterceptor")
    public TransactionInterceptor txInterceptor(){

        Properties attributes = new Properties();
        attributes.setProperty("insert*",   "PROPAGATION_REQUIRED");
        attributes.setProperty("add*",      "PROPAGATION_REQUIRED");
        attributes.setProperty("update*",   "PROPAGATION_REQUIRED");
        attributes.setProperty("delete*",   "PROPAGATION_REQUIRED");
        attributes.setProperty("deploy*",   "PROPAGATION_REQUIRED");
        attributes.setProperty("select*",   "PROPAGATION_REQUIRED,readOnly");
        attributes.setProperty("get*",  	"PROPAGATION_REQUIRED,readOnly");
        attributes.setProperty("query*",    "PROPAGATION_REQUIRED,readOnly");
        return new TransactionInterceptor(transactionManager, attributes);
    }

    @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(@Qualifier("txInterceptor") TransactionInterceptor txInterceptor){
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(txInterceptor);
        pointcutAdvisor.setExpression(AOP_POINTCUT_EXPRESSION);
        return pointcutAdvisor;
    }
}
