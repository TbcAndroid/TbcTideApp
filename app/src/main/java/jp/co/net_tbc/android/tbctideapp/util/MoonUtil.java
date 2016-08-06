package jp.co.net_tbc.android.tbctideapp.util;

/**
 * Created by Kenji Nagai on 2016/08/07.
 * https://gist.githubusercontent.com/daichan4649/6350529/raw/d48f5ffe28ad8cdeb4a0ff66a44cf93d68ca3a2e/MoonUtil.java
 */
public class MoonUtil {
    /**
     * 月齢取得
     *
     * @param year  年(1, 2, ..)
     * @param month 月(1, 2, ..)
     * @param day   日(1, 2, ..)
     * @return 月齢
     * @see <a href="http://ja.wikipedia.org/wiki/%E6%9C%88%E9%BD%A2">月齢</a>
     */
    public static int calculateAgeOfTheMoon(int year, int month, int day) {
        return (((year - 11) % 19) * 11 + c(month) + day) % 30;
    }

    private static int c(int month) {
        switch (month) {
            case 1:
                return 0;
            case 2:
                return 2;
            case 3:
                return 0;
            case 4:
                return 2;
            case 5:
                return 2;
            case 6:
                return 4;
            case 7:
                return 5;
            case 8:
                return 6;
            case 9:
                return 7;
            case 10:
                return 8;
            case 11:
                return 9;
            case 12:
                return 10;
        }
        throw new RuntimeException(String.format("invalid value: %d", month));
    }

    /**
     * 月相種別取得
     *
     * @param ageOfTheMoon 月齢
     * @return 月相種別
     * @see <a href="http://ja.wikipedia.org/wiki/%E6%9C%88%E7%9B%B8">月相</a>
     */
    public static LunarPhaseType getLunarPhaseType(int ageOfTheMoon) {
        switch (ageOfTheMoon) {
            case 0:
                // 新月(朔)
                return LunarPhaseType.NEW_MOON;
            case 7:
                // 上弦
                return LunarPhaseType.HALF_MOON_UP;
            case 14:
                // 満月(望)
                return LunarPhaseType.FULL_MOON;
            case 21:
                // 下弦
                return LunarPhaseType.HALF_MOON_DOWN;
            default:
                return LunarPhaseType.NONE;
        }
    }

    /**
     * 月相種別
     */
    public enum LunarPhaseType {
        NONE(""),
        NEW_MOON("新月"),
        HALF_MOON_UP("上弦"),
        HALF_MOON_DOWN("下弦"),
        FULL_MOON("満月");

        private String text;

        LunarPhaseType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
