package lv.rtustudents.projektesanasprojekts.utils;

public class Validator {

    public static boolean validateStatus(String status) {
        if (status == null) {
            return false;
        }

        if (Constants.STATUS_COMPLETE.equals(status) || Constants.STATUS_FAILED.equals(status) || Constants.STATUS_WAITING.equals(status)) {
            return true;
        } else {
            return false;
        }
    }
}
