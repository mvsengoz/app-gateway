package com.gateway.app.scheduled;

import com.gateway.app.dto.HoroscopeDailyDto;
import com.gateway.app.dto.HoroscopeMonthlyDto;
import com.gateway.app.dto.HoroscopeWeeklyDto;
import com.gateway.app.provider.IProviderClient;
import com.gateway.app.provider.horoscopeai.Client;
import com.gateway.app.services.HoroscopeDailyService;
import com.gateway.app.services.HoroscopeMonthlyService;
import com.gateway.app.services.HoroscopeWeeklyService;
import com.gateway.app.services.SignService;
import com.gateway.app.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentService {

    private static final Logger logger = LoggerFactory.getLogger(ContentService.class);

    /*
    Cron expression is represented by six fields:
    second, minute, hour, day of month, month, day(s) of week
    * means match any
    * /X means "every X"
    ? ("no specific value")
    */

    private final ApplicationContext applicationContext;
    private final HoroscopeDailyService horoscopeDailyService;
    private final HoroscopeWeeklyService horoscopeWeeklyService;
    private final HoroscopeMonthlyService horoscopeMonthlyService;
    private final SignService signService;

    public ContentService(HoroscopeDailyService horoscopeDailyService,
                          HoroscopeWeeklyService horoscopeWeeklyService,
                          HoroscopeMonthlyService horoscopeMonthlyService,
                          SignService signService,
                          ApplicationContext applicationContext) {
        this.horoscopeDailyService = horoscopeDailyService;
        this.horoscopeWeeklyService = horoscopeWeeklyService;
        this.horoscopeMonthlyService = horoscopeMonthlyService;
        this.signService = signService;
        this.applicationContext = applicationContext;

    }

    @Scheduled(cron = "* */1000 * * * *")
    public void acquireDailyContent() {

        var listOfClients = applicationContext.getBeanNamesForType(IProviderClient.class);

        var signs = signService.findAllSigns();

        var period = TimeUtil.getDailyStartEndDate(LocalDateTime.now());

        List<HoroscopeDailyDto> latestHoroscopes = horoscopeDailyService.findLatestHoroscopeForAllSigns();

        Observable.from(listOfClients).subscribeOn(Schedulers.io()).subscribe(clientName -> {

                    IProviderClient client = (IProviderClient) applicationContext.getBean(clientName);
                    if (client.isActive()) {
                        Observable.from(signs).subscribe(k -> {
                            try {

                                var oHoroscope = latestHoroscopes.stream().filter(l -> l.getSign().equalsIgnoreCase(k.getName()) && TimeUtil.isPeriodActive(l.getStartedAt(), l.getEndedAt())).findFirst();

                                if (oHoroscope.isPresent()) {
                                    logger.debug("we have already daily {} content for {} on DB", k.getName(), LocalDateTime.now());
                                    return;
                                }

                                var result = client.daily(k.getName());
                                HoroscopeDailyDto horoscopeDailyDto = new HoroscopeDailyDto(k.getName(),
                                        result, Client.PROVIDER_NAME, period.getFirst(), period.getSecond(), LocalDateTime.now());

                                horoscopeDailyService.saveHoroscope(horoscopeDailyDto);

                            } catch (Exception e) {
                                logger.error("error@acquireContent daily error {}", e.getMessage());
                            }
                        });

                    } else {
                        logger.warn("{} is not enabled", clientName);
                    }
                }
        );

    }

    @Scheduled(cron = "* */1000 * * * *")
    public void acquireWeeklyContent() {

        var listOfClients = applicationContext.getBeanNamesForType(IProviderClient.class);

        var signs = signService.findAllSigns();

        var period = TimeUtil.getWeeklyStartEndDate(LocalDateTime.now());

        List<HoroscopeWeeklyDto> latestHoroscopes = horoscopeWeeklyService.findLatestHoroscopeForAllSigns();


        Observable.from(listOfClients).subscribeOn(Schedulers.io()).subscribe(clientName -> {
                    IProviderClient client = (IProviderClient) applicationContext.getBean(clientName);

                    if (client.isActive()) {

                        Observable.from(signs).subscribeOn(Schedulers.io()).subscribe(k -> {
                            try {

                                var oHoroscope = latestHoroscopes.stream().filter(l -> l.getSign().equalsIgnoreCase(k.getName()) && TimeUtil.isPeriodActive(l.getStartedAt(), l.getEndedAt())).findFirst();

                                if (oHoroscope.isPresent()) {
                                    logger.debug("we have already weekly {} content for {} on DB", k.getName(), LocalDateTime.now());
                                    return;
                                }

                                var result = client.weekly(k.getName());
                                HoroscopeWeeklyDto horoscopeWeeklyDto = new HoroscopeWeeklyDto(k.getName(),
                                        result, Client.PROVIDER_NAME, period.getFirst(), period.getSecond(), LocalDateTime.now());

                                horoscopeWeeklyService.saveHoroscope(horoscopeWeeklyDto);

                            } catch (Exception e) {
                                logger.error("error@acquireContent weekly error {}", e.getMessage());
                            }
                        });

                    } else {
                        logger.warn("{} is not enabled", clientName);
                    }
                }
        );

    }

    @Scheduled(cron = "* */1000 * * * *")
    public void acquireMonthlyContent() {

        var listOfClients = applicationContext.getBeanNamesForType(IProviderClient.class);

        var signs = signService.findAllSigns();

        var period = TimeUtil.getMonthlyStartEndDate(LocalDateTime.now());


        List<HoroscopeMonthlyDto> latestHoroscopes = horoscopeMonthlyService.findLatestHoroscopeForAllSigns();

        Observable.from(listOfClients).subscribeOn(Schedulers.io()).subscribe(clientName -> {
                    IProviderClient client = (IProviderClient) applicationContext.getBean(clientName);

                    if (client.isActive()) {

                        Observable.from(signs).subscribeOn(Schedulers.io()).subscribe(k -> {
                            try {

                                var oHoroscope = latestHoroscopes.stream().filter(l -> l.getSign().equalsIgnoreCase(k.getName()) && TimeUtil.isPeriodActive(l.getStartedAt(), l.getEndedAt())).findFirst();

                                if (oHoroscope.isPresent()) {
                                    logger.debug("we have already monthly {} content for {} on DB", k.getName(), LocalDateTime.now());
                                    return;
                                }

                                var result = client.monthly(k.getName());
                                HoroscopeMonthlyDto horoscopeMonthlyDto = new HoroscopeMonthlyDto(k.getName(),
                                        result, Client.PROVIDER_NAME, period.getFirst(), period.getSecond(), LocalDateTime.now());

                                horoscopeMonthlyService.saveHoroscope(horoscopeMonthlyDto);

                            } catch (Exception e) {
                                logger.error("error@acquireContent monthly error {}", e.getMessage());
                            }
                        });

                    } else {
                        logger.warn("{} is not enabled", clientName);
                    }
                }
        );

    }
}
