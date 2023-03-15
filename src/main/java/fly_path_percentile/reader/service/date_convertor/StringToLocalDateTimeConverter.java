package fly_path_percentile.reader.service.date_convertor;

import java.time.LocalDateTime;

import static fly_path_percentile.reader.util.DateUtil.dateParse;
import static fly_path_percentile.reader.util.DateUtil.showCityDateInUTC;

public class StringToLocalDateTimeConvertor implements DateConvertor<LocalDateTime> {

    @Override
    public LocalDateTime convertToLocalDateTime(String date, String time) {
        StringBuilder sb = new StringBuilder(date);
        sb.append(" ");
        sb.append(time);
        return dateParse(String.valueOf(sb));
    }

    @Override
    public LocalDateTime getUTCTime(LocalDateTime date) {
//        Asia/Vladivostok
//        Asia/Tel_Aviv
//        Date today = new Date();
//        TimeZone userTZ = TimeZone.getTimeZone(timeZone);
//        DATE_FORMAT.setTimeZone(userTZ);
//        String tmpDate = DATE_FORMAT.format(today);
        return null;
    }

    public static void main(String[] args) {
        StringToLocalDateTimeConvertor stringToLocalDateTimeConvertor = new StringToLocalDateTimeConvertor();
        LocalDateTime departed = stringToLocalDateTimeConvertor.convertToLocalDateTime("12.05.18", "17:20");
        LocalDateTime arrived = stringToLocalDateTimeConvertor.convertToLocalDateTime("12.05.18", "15:25");
        System.out.println(departed);
        String vladivostok = "Asia/Vladivostok";
        String telAviv = "Asia/Tel_Aviv";
        showCityDateInUTC(vladivostok, departed);
        showCityDateInUTC(telAviv, arrived);
        System.out.println("showInUTCTimeZone in: "
                .concat(vladivostok)
                .concat(" | ")
                .concat(String.valueOf(showCityDateInUTC(vladivostok, departed))));
        System.out.println("showInUTCTimeZone in: "
                .concat(telAviv)
                .concat(" | ")
                .concat(String.valueOf(showCityDateInUTC(telAviv, arrived))));
        //departureDate=12.05.18, departureTime=6:10, arrivalDate=12.05.18, arrivalTime=15:25
    }
}
