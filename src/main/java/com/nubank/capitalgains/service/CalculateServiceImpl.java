package com.nubank.capitalgains.service;

import com.nubank.capitalgains.domain.Input;
import com.nubank.capitalgains.domain.Output;
import com.nubank.capitalgains.enums.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CalculateServiceImpl implements ICalculateService {
    @Override
    public List<Output> calculateTax(List<Input> inputList) {
        List<Output> outputList = new ArrayList<>();
        Double loss = 0.0;
        Integer average = inputList.stream().filter(i -> i.getOperation().equals(Operation.buy.getOperation())).mapToInt(i -> i.getUnitCost() * i.getQuantity()).sum() /
                inputList.stream().filter(i -> i.getOperation().equals(Operation.buy.getOperation())).mapToInt(i -> i.getQuantity()).sum();
        for (Input input : inputList
        ) {
            //Buying stocks do not pay taxes
            if (input.getOperation().equals(Operation.buy.getOperation())) {
                outputList.add(new Output(0.0));
                continue;
            }
            Integer totalAmount = input.getQuantity() * input.getUnitCost();
            if (input.getUnitCost() < average && loss == 0)
                loss = Double.valueOf(input.getQuantity() * average) - Double.valueOf(input.getQuantity() * input.getUnitCost());
            //Total amount less than R$ 20000
            if (totalAmount <= 20000) {
                outputList.add(new Output(0.0));
                continue;
            }
            //Losses
            if (input.getUnitCost() <= average) {
                outputList.add(new Output(0.0));
                continue;
            }
            //Profit
            double profit = (input.getUnitCost() - average) * input.getQuantity();
            if (loss == 0) {
                outputList.add(new Output(profit * 0.2));
                continue;
            }
            if (loss >= profit) {
                loss = loss - profit;
                outputList.add(new Output(0.0));
                continue;
            }
            outputList.add(new Output((profit - loss) * 0.2));
            loss = 0.0;
        }
        return outputList;
    }
}
