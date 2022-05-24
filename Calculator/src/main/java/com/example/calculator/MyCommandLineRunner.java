package com.example.calculator;


import com.example.calculator.common.LoggerProxy;
import com.example.calculator.math.Calculator;
import com.example.calculator.math.CalculatorImpl;
import com.example.calculator.math.CalculatorLogger;
import com.example.calculator.math.CalculatorSecure;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private  Calculator calculator;

    public MyCommandLineRunner() {
        calculator = new CalculatorImpl();
        //calculator = new CalculatorLogger(calculator);
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecure(calculator);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(calculator.add(3,4));
    }
}
