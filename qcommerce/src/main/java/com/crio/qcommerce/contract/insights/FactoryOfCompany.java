package com.crio.qcommerce.contract.insights;


import com.crio.qcommerce.contract.resolver.DataProvider;

public class FactoryOfCompany {

  public final static FactoryOfCompany INSTANCE = new FactoryOfCompany();

  public SaleInsights getCompanyManager(DataProvider dataProvider, int year) {

    if (dataProvider.getProvider().equalsIgnoreCase("amazon")) {
      return new AmazonSale();
    }

    else if (dataProvider.getProvider().equalsIgnoreCase("ebay"))

    {
      return new EbaySale();
    } else {
      return new FlipkartSale();
    }
  }
}