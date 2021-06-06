import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stocks.Portfolio;
import stocks.Stock;
import stocks.StockService;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PortfolioTest {
    Portfolio portfolio;
    StockService stockService;

    /*public static void main(String[] as) {
        PortfolioTest tester = new PortfolioTest();
        tester.setUp();
        System.out.println(tester.testMarketValue()?"pass":"fail");
    }*/

    @Before
    public void setUp() {
        portfolio = new Portfolio();
        stockService = mock(StockService.class);
        portfolio.setStockService(stockService);
    }

    @Test
    public void testMarketValue() {
        List<Stock> stocks = new ArrayList<>();
        Stock googleStock = new Stock("google", "Google", 10);
        Stock microsoftStock = new Stock("microsoft", "Microsoft", 20);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        portfolio.setStocks(stocks);

        when(stockService.getPrice(googleStock)).thenReturn(50.0);
        when(stockService.getPrice(microsoftStock)).thenReturn(100.0);

        double marketValue = portfolio.getMarketValue();

        Assert.assertTrue(marketValue == 2500);
//        return marketValue == 2500;
    }
}
