package com.nubank.capitalgains.service;

import com.nubank.capitalgains.domain.Input;
import com.nubank.capitalgains.domain.Output;

import java.util.List;

public interface ICalculateService {

    List<Output> calculateTax(List<Input> inputList);
}
