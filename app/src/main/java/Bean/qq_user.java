package Bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/9/1.
 */
@Table(name = "qq_user")
public class qq_user {


    @Column(name = "id", isId = true)
    private int id;



    @Column(name = "name")
    private String name;

    @Column(name = "province")
    private String province;

    @Column(name = "gender")
    private String gender;

    @Column(name = "city")
    private String city;
    @Column(name = "bitmap")
    private String bitmap;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "day")
    private String day;
    @Column(name = "study")
    private String study;
    @Column(name = "work")
    private String work;

    @Column(name = "open")
    private String open;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }


    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
