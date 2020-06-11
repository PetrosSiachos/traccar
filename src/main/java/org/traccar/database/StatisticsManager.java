/*
 * Copyright 2016 - 2019 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.config.Config;
import org.traccar.config.Keys;
import org.traccar.helper.DateUtil;
import org.traccar.model.Statistics;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsManager.class);

    private static final int SPLIT_MODE = Calendar.DAY_OF_MONTH;

    private Config config;
    private DataManager dataManager;
    private Client client;

    private AtomicInteger lastUpdate = new AtomicInteger(Calendar.getInstance().get(SPLIT_MODE));

    private Set<Long> users = new HashSet<>();
    private Set<Long> devices = new HashSet<>();

    private int requests;
    private int messagesReceived;
    private int messagesStored;
    private int mailSent;
    private int smsSent;
    private int geocoderRequests;
    private int geolocationRequests;

    @Inject
    public StatisticsManager(Config config, DataManager dataManager, Client client) {
        this.config = config;
        this.dataManager = dataManager;
        this.client = client;
    }


	@SuppressWarnings("null")
	public String[][] checkSplit() {
        int currentUpdate = Calendar.getInstance().get(SPLIT_MODE);
        String[][] usersStats = null;
        String url = config.getString(Keys.SERVER_STATISTICS);
        if (lastUpdate.getAndSet(currentUpdate) != currentUpdate) {
            Statistics statistics = new Statistics();
            statistics.setCaptureTime(new Date());
            statistics.setActiveUsers(users.size());
            statistics.setActiveDevices(devices.size());
            statistics.setRequests(requests);
            statistics.setMessagesReceived(messagesReceived);
            statistics.setMessagesStored(messagesStored);
            statistics.setMailSent(mailSent);
            statistics.setSmsSent(smsSent);
            statistics.setGeocoderRequests(geocoderRequests);
            statistics.setGeolocationRequests(geolocationRequests);

            try {
                dataManager.addObject(statistics);
            } catch (SQLException e) {
                LOGGER.warn("Error saving statistics", e);
            }



            if (url != null) {
                String time = DateUtil.formatDate(statistics.getCaptureTime());

                Form form = new Form();
                form.param("version", getClass().getPackage().getImplementationVersion());
                form.param("captureTime", time);
                form.param("activeUsers", String.valueOf(statistics.getActiveUsers()));
                form.param("activeDevices", String.valueOf(statistics.getActiveDevices()));
                form.param("requests", String.valueOf(statistics.getRequests()));
                form.param("messagesReceived", String.valueOf(statistics.getMessagesReceived()));
                form.param("messagesStored", String.valueOf(statistics.getMessagesStored()));
                form.param("mailSent", String.valueOf(statistics.getMailSent()));
                form.param("smsSent", String.valueOf(statistics.getSmsSent()));
                form.param("geocoderRequests", String.valueOf(statistics.getGeocoderRequests()));
                form.param("geolocationRequests", String.valueOf(statistics.getGeolocationRequests()));

                client.target(url).request().async().post(Entity.form(form));
                usersStats[0][0]= "version";
                usersStats[0][1]= getClass().getPackage().getImplementationVersion();
                usersStats[1][0]= "captureTime";
                usersStats[1][1]= time;
                usersStats[2][0]= "activeUsers";
                usersStats[2][1]= String.valueOf(statistics.getActiveUsers());
                usersStats[3][0]= "activeDevices";
                usersStats[3][1]= String.valueOf(statistics.getActiveDevices());
                usersStats[4][0]= "requests";
                usersStats[4][1]= String.valueOf(statistics.getRequests());
                usersStats[5][0]= "messagesReceived";
                usersStats[5][1]= String.valueOf(statistics.getMessagesReceived());
                usersStats[6][0]= "messagesStored";
                usersStats[6][1]= String.valueOf(statistics.getMessagesStored());
                usersStats[7][0]= "mailSent";
                usersStats[7][1]= String.valueOf(statistics.getMailSent());
                usersStats[8][0]= "smsSent";
                usersStats[8][1]= String.valueOf(statistics.getSmsSent());
                usersStats[9][0]= "geocoderRequests";
                usersStats[9][1]= String.valueOf(statistics.getGeocoderRequests());
                usersStats[10][0]= "geolocationRequests";
                usersStats[10][1]= String.valueOf(statistics.getGeolocationRequests());
            }else {
            	usersStats[0][0]= "version";
                usersStats[0][1]= "no data";
                usersStats[1][0]= "captureTime";
                usersStats[1][1]= "no data";
                usersStats[2][0]= "activeUsers";
                usersStats[2][1]= "no data";
                usersStats[3][0]= "activeDevices";
                usersStats[3][1]= "no data";
                usersStats[4][0]= "requests";
                usersStats[4][1]= "no data";
                usersStats[5][0]= "messagesReceived";
                usersStats[5][1]= "no data";
                usersStats[6][0]= "messagesStored";
                usersStats[6][1]= "no data";
                usersStats[7][0]= "mailSent";
                usersStats[7][1]= "no data";
                usersStats[8][0]= "smsSent";
                usersStats[8][1]= "no data";
                usersStats[9][0]= "geocoderRequests";
                usersStats[9][1]= "no data";
                usersStats[10][0]= "geolocationRequests";
                usersStats[10][1]= "no data";
            }

            users.clear();
            devices.clear();
            requests = 0;
            messagesReceived = 0;
            messagesStored = 0;
            mailSent = 0;
            smsSent = 0;
            geocoderRequests = 0;
            geolocationRequests = 0;

        }
		return usersStats;

	}

    public synchronized void registerRequest(long userId) {
        checkSplit();
        requests += 1;
        if (userId != 0) {
            users.add(userId);
        }
    }

    public synchronized void registerMessageReceived() {
        checkSplit();
        messagesReceived += 1;
    }

    public synchronized void registerMessageStored(long deviceId) {
        checkSplit();
        messagesStored += 1;
        if (deviceId != 0) {
            devices.add(deviceId);
        }
    }

    public synchronized void registerMail() {
        checkSplit();
        mailSent += 1;
    }

    public synchronized void registerSms() {
        checkSplit();
        smsSent += 1;
    }

    public synchronized void registerGeocoderRequest() {
        checkSplit();
        geocoderRequests += 1;
    }

    public synchronized void registerGeolocationRequest() {
        checkSplit();
        geolocationRequests += 1;
    }

}
