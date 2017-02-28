package com.mycompany.myapp.service.util;

import com.mycompany.myapp.service.dto.DateTimeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ibara on 2/24/2017.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DateTimeProcessor {

    private final Logger logger = LoggerFactory.getLogger(DateTimeProcessor.class);
    private static final String FORMAT = "E yyyy.MM.dd 'at' hh:mm:ss a zzz";

    public DateTimeDao getDateTime() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat(FORMAT);
        logger.debug("Current Date: {}", ft.format(dNow));
        return new DateTimeDao(ft.format(dNow));
    }
}
