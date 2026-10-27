package com.example.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    @GetMapping("/calculate")
    public Map<String, Object> calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operator") String operator) {
        
        Map<String, Object> response = new HashMap<>();

        try {
            double result = 0;
            switch (operator.toLowerCase()) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 == 0) {
                        response.put("error", "Cannot divide by zero.");
                        return response;
                    }
                    result = num1 / num2;
                    break;
                default:
                    response.put("error", "Invalid operator.");
                    return response;
            }

            if (result == (long) result) {
                response.put("result", (long) result);
            } else {
                response.put("result", result);
            }

        } catch (Exception e) {
            response.put("error", "Calculation processing error.");
        }

        return response;
    }
}
