
package com.crio.qcommerce.contract.insights;

import java.io.IOException;

import com.crio.qcommerce.contract.exceptions.AnalyticsException;
import com.crio.qcommerce.contract.resolver.DataProvider;

public interface SaleInsights {

  SaleAggregate getSaleInsights(DataProvider dataProvider, int year)
      throws IOException, AnalyticsException;

}

