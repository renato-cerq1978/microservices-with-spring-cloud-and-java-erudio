package br.com.erudio.controllers;

import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.math.SimpleMath;
import br.com.erudio.requestconverters.NumberConverter;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    //http:localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")  String numberTwo) throws IllegalArgumentException {

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    //http:localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtrtaction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.subtrtaction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    //http:localhost:8080/math/multiplication/3/5
    @RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    //http:localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        if(NumberConverter.convertToDouble(numberTwo) == 0) throw new UnsuportedMathOperationException("Cannot divide by zero");

        return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/squareRoot/{number}", method=RequestMethod.GET)
    public Double squareRoot(@PathVariable("number") String number) throws Exception {
        if (!NumberConverter.isNumeric(number)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.squareRoot(NumberConverter.convertToDouble(number));
    }
}
