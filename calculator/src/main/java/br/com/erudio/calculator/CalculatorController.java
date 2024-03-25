package br.com.erudio.calculator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

@RestController
public class CalculatorController {
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sum(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) {
        exceptionResponse(numberOne, numberTwo);

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) {
        exceptionResponse(numberOne, numberTwo);

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) {
        exceptionResponse(numberOne, numberTwo);

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}")
    public Double division(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) {
        exceptionResponse(numberOne, numberTwo);

        if (convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Não se pode dividir por zero.");
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}")
    public Double average(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) {
        exceptionResponse(numberOne, numberTwo);

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping(value = "/square/{numberOne}")
    public Double square(
        @PathVariable(value = "numberOne") String numberOne
    ) {
        if (!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico.");
        }

        return convertToDouble(numberOne) * convertToDouble(numberOne);
    }

    private void exceptionResponse(String numberOne, String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico.");
        }
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;

        String number = strNumber.replace(",", ".");

        if (isNumeric(number)) return Double.parseDouble(number);

        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;

        String number = strNumber.replace(",", ".");

        return number.matches("[-+]?\\d*\\.?\\d+");
    }
}
