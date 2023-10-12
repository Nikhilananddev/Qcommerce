package com.crio.qcommerce.sale.insights;

import java.io.IOException;

import com.crio.qcommerce.contract.exceptions.AnalyticsException;
import com.crio.qcommerce.contract.insights.FactoryOfCompany;
import com.crio.qcommerce.contract.insights.SaleAggregate;
import com.crio.qcommerce.contract.insights.SaleInsights;
import com.crio.qcommerce.contract.resolver.DataProvider;


public class SaleInsightsImpl implements SaleInsights {


	@Override
    public SaleAggregate getSaleInsights(DataProvider dataProvider, int year) throws IOException, AnalyticsException {
       

     SaleInsights saleInsights= FactoryOfCompany.INSTANCE.getCompanyManager(dataProvider, year);

return saleInsights.getSaleInsights(dataProvider, year);
//sale
}
}