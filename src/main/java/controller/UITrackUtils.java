package controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UITrackUtils {

    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;

    public static String intTimeToString(int time){
        StringBuilder str = new StringBuilder();

        if (time >= SECONDS_IN_HOUR) {
            str.append(time / SECONDS_IN_HOUR).append(':');
            time %= SECONDS_IN_HOUR;
        }
        if (time >= SECONDS_IN_MINUTE) {
            str.append(time / SECONDS_IN_MINUTE).append(':');
            time %= SECONDS_IN_MINUTE;
        }
        if (time < 10) {
            str.append(0).append(time);
        } else {
            str.append(time);
        }

        return str.toString();
    }

    public static int stringTimeToInt(String time){
        String[] splitTime = time.split(":");

        if (splitTime.length == 3) {
            return (Integer.parseInt(splitTime[0]) * SECONDS_IN_HOUR) + (Integer.parseInt(splitTime[1]) * SECONDS_IN_MINUTE) + Integer.parseInt(splitTime[2]);
        } else if (splitTime.length == 2) {
            return (Integer.parseInt(splitTime[0]) * SECONDS_IN_MINUTE) + Integer.parseInt(splitTime[1]);
        } else if (splitTime.length == 1) {
            return Integer.parseInt(splitTime[0]);
        } else {
            // ??? исключение кидать ???
            return 0;
        }
    }

    public static String listToString(List<String> list, String separator) {
        if (list.isEmpty()) return "unknown";
        else {
            StringBuilder str = new StringBuilder();

            for (int i = 0; i < list.size() - 1; i++) {
                str.append(list.get(i)).append(separator);
            }
            str.append(list.get(list.size() - 1));

            return str.toString();
        }
    }

    public static List<String> stringToList(String string, String separator) {
        List<String> res = new ArrayList<>();

        if (string.contentEquals("unknown")) return res;
        else {
            String[] arr = string.split(separator);
            res.addAll(Arrays.asList(arr));
            return res;
        }
    }
}
