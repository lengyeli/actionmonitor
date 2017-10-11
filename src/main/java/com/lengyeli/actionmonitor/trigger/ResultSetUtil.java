package com.lengyeli.actionmonitor.trigger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for representing resultset
 * Created by ilengyel on 2017. 10. 10..
 */
public class ResultSetUtil {

    public static String createChangeResultMessage(ResultSet resultSet) throws SQLException {
        StringBuilder sb = new StringBuilder();

        sb.append("timeStamp=")
                .append(getFormattedTimeStamp())
                .append(" :: ").append("a row with ID=")
                .append(resultSet.getObject(1));

        return sb.toString();
    }

    private static String getFormattedTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
