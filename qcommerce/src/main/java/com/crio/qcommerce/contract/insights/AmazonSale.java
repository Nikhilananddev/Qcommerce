package com.crio.qcommerce.contract.insights;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.crio.qcommerce.contract.exceptions.AnalyticsException;
import com.crio.qcommerce.contract.resolver.DataProvider;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class AmazonSale implements SaleInsights {
    @Override
    public SaleAggregate getSaleInsights(DataProvider dataProvider, int year) throws IOException, AnalyticsException {
        Reader fileReader;
        SaleAggregate saleAggregate = new SaleAggregate();
        File CsvFile = dataProvider.resolveFile();
        InputStream inputStream = new FileInputStream(CsvFile);
        fileReader = new InputStreamReader(inputStream, "UTF-8");
        CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        List<SaleAggregateByMonth> monthsSales = new ArrayList<>();

        HashMap<Integer, Double> map = new HashMap<>();

        try {

            String[] nextRecord;
            int month = 0;
            int checkyear = 0;
            Double janMonth = 0.0;
            Double febMonth = 0.0;
            Double marchMonth = 0.0;
            Double AprilMonths = 0.0;
            Double mayMOnth = 0.0;
            Double JuneMonth = 0.0;
            Double July = 0.0;
            Double sep = 0.0;
            Double Agust = 0.0;
            Double oct = 0.0;
            Double nov = 0.0;
            Double dec = 0.0;

            while ((nextRecord = reader.readNext()) != null) 
            {
                String retrievedate = nextRecord[4];
                String status = nextRecord[3];
                LocalDate tod = LocalDate.parse(retrievedate);

                month = tod.getMonthValue();
                checkyear = tod.getYear();

                if (month == 1 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);
                    janMonth = janMonth + sale;

                    map.put(1, janMonth);
                }

                else if (month == 2 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    febMonth = febMonth + sale;

                    map.put(2, febMonth);

                }

                else if (month == 3 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    marchMonth = marchMonth + sale;

                    map.put(3, marchMonth);
                }

                else if (month == 4 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    AprilMonths = AprilMonths + sale;

                    map.put(4, AprilMonths);
                } else if (month == 5 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    mayMOnth = mayMOnth + sale;

                    map.put(5, mayMOnth);
                } else if (month == 6 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    JuneMonth = JuneMonth + sale;

                    map.put(6, JuneMonth);
                } else if (month == 7 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    July = July + sale;

                    map.put(7, July);

                } else if (month == 8 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    Agust = Agust + sale;

                    map.put(8, Agust);
                } else if (month == 9 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    sep = sep + sale;

                    map.put(9, sep);
                }

                else if (month == 10 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    oct = oct + sale;

                    map.put(10, oct);
                } else if (month == 11 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);

                    nov = nov + sale;

                    map.put(11, nov);
                } else if (month == 12 && checkyear == year && status.equalsIgnoreCase("shipped"))

                {

                    double sale = Double.parseDouble(nextRecord[5]);
                    dec = dec + sale;
                    map.put(12, dec);
                }

            }

        } catch (NumberFormatException e)
         {
            throw new AnalyticsException("not valid" + e.getCause());
         }

        for (HashMap.Entry<Integer, Double> e : map.entrySet()) {
            SaleAggregateByMonth saleAggregateByMonth = new SaleAggregateByMonth(e.getKey(), e.getValue());

            monthsSales.add(saleAggregateByMonth);

        }

        Double totalamount = 0.0;
        for (SaleAggregateByMonth saleAggregateByMonth : monthsSales) {
            totalamount = totalamount + saleAggregateByMonth.getSales();
        }
        saleAggregate.setAggregateByMonths(monthsSales);
        saleAggregate.setTotalSales(totalamount);

        return saleAggregate;

    }

}
