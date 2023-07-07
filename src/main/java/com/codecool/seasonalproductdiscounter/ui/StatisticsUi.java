package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;

import java.util.Map;

public class StatisticsUi {
    private final ProductStatistics productStatistics;

    public StatisticsUi(ProductStatistics productStatistics) {
        this.productStatistics = productStatistics;
    }

    public void run() {
        for (Map.Entry<Color, Double> entry : productStatistics.getAveragePricesByColor().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

