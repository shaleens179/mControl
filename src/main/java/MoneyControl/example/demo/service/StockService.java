package MoneyControl.example.demo.service;

import MoneyControl.example.demo.entity.Stock;
import MoneyControl.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }


    public Stock updateStock(Long id, Double sellPrice, String sellDate) {
        Optional<Stock> stockOpt = stockRepository.findById(id);
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            stock.setSellPrice(sellPrice);
            stock.setSellDate(java.sql.Date.valueOf(sellDate)); // Convert String to Date

            // Calculate profit or loss
            double profitLoss = (sellPrice - stock.getBuyPrice()) * stock.getQuantity();
            stock.setProfitLoss(profitLoss);

            return stockRepository.save(stock);
        }
        return null;
    }
}
