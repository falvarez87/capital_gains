package com.nubank.capitalgains.service;

import com.nubank.capitalgains.domain.Input;
import com.nubank.capitalgains.domain.Output;
import com.nubank.capitalgains.enums.Operation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestCalculateService {
    @InjectMocks
    private CalculateServiceImpl calculateService;

    @Test
    void Case1() {
        //Input:
        //[{"operation":"buy", "unit-cost":10, "quantity": 100},{"operation":"sell", "unit-cost":15, "quantity": 50},{"operation":"sell", "unit-cost":15, "quantity": 50}]
        //Output:
        //[{"tax": 0},{"tax": 0},{"tax": 0}]
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input(Operation.buy.getOperation(),10,100));
        inputList.add(new Input(Operation.sell.getOperation(),15,50));
        inputList.add(new Input(Operation.sell.getOperation(),15,50));
        List<Output> outputListExpected = new ArrayList<>();
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        List<Output> outputList = calculateService.calculateTax(inputList);
        assertArrayEquals(outputList.toArray(), outputListExpected.toArray());
    }

    @Test
    void Case2() {
        //Input:
        //[{"operation":"buy", "unit-cost":10, "quantity": 10000},
        // {"operation":"sell","unit-cost":20, "quantity": 5000},
        // {"operation":"sell", "unit-cost":5, "quantity":5000}]
        //Output:
        //[{"tax": 0},{"tax": 10000},{"tax": 0}]
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input(Operation.buy.getOperation(),10,10000));
        inputList.add(new Input(Operation.sell.getOperation(),20,5000));
        inputList.add(new Input(Operation.sell.getOperation(),5,5000));
        List<Output> outputListExpected = new ArrayList<>();
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(10000.0));
        outputListExpected.add(new Output(0.0));
        List<Output> outputList = calculateService.calculateTax(inputList);
        assertArrayEquals(outputList.toArray(), outputListExpected.toArray());
    }

    @Test
    void Case3() {
        //Input:
        //[{"operation":"buy", "unit-cost":10, "quantity": 10000},
        // {"operation":"sell","unit-cost":5, "quantity": 5000},
        // {"operation":"sell", "unit-cost":20, "quantity":5000}]
        //Output:
        //[{"tax": 0},{"tax": 0},{"tax": 5000}]
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input(Operation.buy.getOperation(),10,10000));
        inputList.add(new Input(Operation.sell.getOperation(),5,5000));
        inputList.add(new Input(Operation.sell.getOperation(),20,5000));
        List<Output> outputListExpected = new ArrayList<>();
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(5000.0));
        List<Output> outputList = calculateService.calculateTax(inputList);
        assertArrayEquals(outputList.toArray(), outputListExpected.toArray());
    }

    @Test
    void Case4() {
        //Input:
        //[{"operation":"buy", "unit-cost":10, "quantity": 10000},
        // {"operation":"buy","unit-cost":25, "quantity": 5000},
        // {"operation":"sell", "unit-cost":15,"quantity": 10000}]
        //Output:
        //[{"tax": 0},{"tax": 0},{"tax": 0}]
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input(Operation.buy.getOperation(),10,10000));
        inputList.add(new Input(Operation.buy.getOperation(),25,5000));
        inputList.add(new Input(Operation.sell.getOperation(),15,10000));
        List<Output> outputListExpected = new ArrayList<>();
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        List<Output> outputList = calculateService.calculateTax(inputList);
        assertArrayEquals(outputList.toArray(), outputListExpected.toArray());
    }

    @Test
    void Case5() {
        //Input:
        //[{"operation":"buy", "unit-cost":10, "quantity": 10000},
        // {"operation":"buy","unit-cost":25, "quantity": 5000},
        // {"operation":"sell", "unit-cost":15,"quantity": 10000},
        // {"operation":"sell", "unit-cost":25, "quantity": 5000}]
        //Output:
        //[{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 10000}]
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input(Operation.buy.getOperation(),10,10000));
        inputList.add(new Input(Operation.buy.getOperation(),25,5000));
        inputList.add(new Input(Operation.sell.getOperation(),15,10000));
        inputList.add(new Input(Operation.sell.getOperation(),25,5000));
        List<Output> outputListExpected = new ArrayList<>();
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(10000.0));
        List<Output> outputList = calculateService.calculateTax(inputList);
        assertArrayEquals(outputList.toArray(), outputListExpected.toArray());
    }

    @Test
    void Case6() {
        //Input:
        //[{"operation":"buy", "unit-cost":10, "quantity": 10000},
        // {"operation":"sell","unit-cost":2, "quantity": 5000},
        // {"operation":"sell", "unit-cost":20, "quantity":2000},
        // {"operation":"sell", "unit-cost":20, "quantity": 2000},
        // {"operation":"sell","unit-cost":25, "quantity": 1000}]
        //Output:
        //[{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 3000}]
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input(Operation.buy.getOperation(),10,10000));
        inputList.add(new Input(Operation.sell.getOperation(),2,5000));
        inputList.add(new Input(Operation.sell.getOperation(),20,2000));
        inputList.add(new Input(Operation.sell.getOperation(),20,2000));
        inputList.add(new Input(Operation.sell.getOperation(),25,1000));
        List<Output> outputListExpected = new ArrayList<>();
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(0.0));
        outputListExpected.add(new Output(3000.0));
        List<Output> outputList = calculateService.calculateTax(inputList);
        assertArrayEquals(outputList.toArray(), outputListExpected.toArray());
    }
}
