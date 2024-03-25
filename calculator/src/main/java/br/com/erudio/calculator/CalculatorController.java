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
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor, use um valor numérico.");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
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
