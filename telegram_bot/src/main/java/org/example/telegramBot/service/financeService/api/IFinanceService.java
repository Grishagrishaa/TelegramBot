package org.example.telegramBot.service.financeService.api;

import org.example.telegramBot.service.impl.financeService.dto.CurrencyMyfinDto;
import org.example.telegramBot.service.impl.financeService.dto.CurrencyMyfinNbrbDto;
import org.example.telegramBot.service.impl.financeService.pagination.Page;
import org.example.telegramBot.service.impl.financeService.pagination.PageRequest;

public interface IFinanceService {

    /**
     * This method provide bot with currencies, which is able to use
     * in currency conversion. Currencies, you can choose to convert in "Converter".
     * @return Convert Currency array
     */
    String[] getConvertCurrencies();

    /**
     * This method provide bot with currencies, which is able to use
     * in best rates viewing. Currencies, you can choose to view in "Best Rates".
     * @return Rates Currency array
     */
    String[] getRatesCurrencies();

    /**
     * This method provide bot with cities, which is able to use in best rates viewing.
     * City, you can choose in settings to view "Best Rates" in particular region.
     * @return Cities array
     */
    String[] getCities();

    /**
     * This method provide bot with best rates on particular in particular region
     * @param city city of rates
     * @param currency
     * @param pageable page size, page number
     * @return page of rates
     */
    Page<CurrencyMyfinDto> getBestRates(String city, String currency, PageRequest pageable);

    /**
     * This method provide bot with best rates from National Bank, in case user didn't choose region in settings
     * @param pageable page size, page number
     * @return page of rates
     */
    Page<CurrencyMyfinNbrbDto> getBestNbrbRates(PageRequest pageable);

    /**
     * This method provide bot with conversion ratio from National Bank
     * @param original Original currency
     * @param target Target currency
     * @return Conversion ratio
     */
    Double getRate(String original, String target);
}
