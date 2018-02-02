package pro.chukai.web.dto.out;

import java.time.YearMonth;

/**
 * 文章数量归档
 *
 * @author hasee
 */
public class Archive implements Comparable<Archive> {

    //年月
    private YearMonth yearMonth;
    //篇数
    private int count;


    public Archive(YearMonth yearMonth, int count) {
        this.yearMonth = yearMonth;
        this.count = count;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Archive() {
    }

    @Override
    public int compareTo(Archive o) {
        return this.yearMonth.compareTo(o.yearMonth);
    }
}
