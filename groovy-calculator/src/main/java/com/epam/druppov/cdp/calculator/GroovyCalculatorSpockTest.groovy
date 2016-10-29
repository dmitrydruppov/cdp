package com.epam.druppov.cdp.calculator

import com.epam.druppov.cdp.calculator.calculator.GroovyCalculator
import spock.lang.Specification

/**
 * Created by Dmytro_Druppov on 10/29/2016.
 */
class GroovyCalculatorSpockTest extends Specification {


    def "1"() {
        setup: ""
            GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
            groovyCalc.calculate("2+2");
        then:
            groovyCalc.lastResult == 4.0;

    }

    def "2"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("2-2");
        then:
        groovyCalc.lastResult == 0.0;

    }

    def "4"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("64/8");
        then:
        groovyCalc.lastResult == 8.0;

    }

    def "5"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("(2-2)*4");
        then:
        groovyCalc.lastResult == 0.0;

    }

    def "3"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("(2-2)*4");
        then:
        groovyCalc.lastResult == 0.0;

    }

    def "7"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("2^3*4");
        then:
        groovyCalc.lastResult == 32.0;

    }

    def "8"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate(null);
        then:
        groovyCalc.lastResult == 0.0;

    }

    def "9"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("2+2*4");
        then:
        groovyCalc.lastResult == 10.0;

    }

    def "10"() {
        setup: ""
        GroovyCalculator groovyCalc = new GroovyCalculator();
        when:
        groovyCalc.calculate("(2-2)*(4-2)");
        then:
        groovyCalc.lastResult == 0.0;

    }

    def "11"() {
        setup: ""
            GroovyCalculator groovyCalc = new GroovyCalculator();
            GroovyCalculator othrerCalc = new GroovyCalculator();
            def sum = 0;
        when:
            groovyCalc.calculate("(2+2)*4");
            othrerCalc.calculate("-1-4");
            sum = groovyCalc + othrerCalc;
        then:
            sum == 11.0;
    }




}
