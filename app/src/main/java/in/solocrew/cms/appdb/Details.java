package in.solocrew.cms.appdb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Details {
    int id,shop_id,no_of_boxes,no_of_empty_box_returned,no_of_box_yet_to_return;
    double gross_weight,weight_of_empty_box, net_weight,rate,total;
    String shop_name,date;

    public Details() {
    }

    public Details(int shop_id, int no_of_boxes, int no_of_empty_box_returned,
                   int no_of_box_yet_to_return, double gross_weight, double weight_of_empty_box,
                   double rate) {
        this.id = id;
        this.shop_id = shop_id;
        this.no_of_boxes = no_of_boxes;
        this.no_of_empty_box_returned = no_of_empty_box_returned;
        this.no_of_box_yet_to_return = no_of_box_yet_to_return;
        this.gross_weight = gross_weight;
        this.weight_of_empty_box = weight_of_empty_box;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public String getDateFormat() {
        try {
            Date _date=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
            return new SimpleDateFormat("dd MMM yyyy HH:mm:ss a").format(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getDateOnly() {
        try {
            Date _date=new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return new SimpleDateFormat("dd MMM yyyy").format(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getNo_of_boxes() {
        return no_of_boxes;
    }

    public void setNo_of_boxes(int no_of_boxes) {
        this.no_of_boxes = no_of_boxes;
    }

    public int getNo_of_empty_box_returned() {
        return no_of_empty_box_returned;
    }

    public void setNo_of_empty_box_returned(int no_of_empty_box_returned) {
        this.no_of_empty_box_returned = no_of_empty_box_returned;
    }

    public int getNo_of_box_yet_to_return() {
        return no_of_box_yet_to_return;
    }

    public void setNo_of_box_yet_to_return(int no_of_box_yet_to_return) {
        this.no_of_box_yet_to_return = no_of_box_yet_to_return;
    }

    public double getGross_weight() {
        return gross_weight;
    }

    public void setGross_weight(double gross_weight) {
        this.gross_weight = gross_weight;
    }

    public double getWeight_of_empty_box() {
        return weight_of_empty_box;
    }

    public void setWeight_of_empty_box(double weight_of_empty_box) {
        this.weight_of_empty_box = weight_of_empty_box;
    }

    public double getNet_weight() {
        return gross_weight - weight_of_empty_box;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return rate * (gross_weight - weight_of_empty_box);
    }

    public void setData(int id, double toDouble, int toInt, double toDouble1,
                        int toInt1, int toInt2, double toDouble2) {
        this.shop_id = id;
        this.gross_weight = toDouble;
        this.no_of_boxes = toInt;
        this.weight_of_empty_box = toDouble1;
        this.no_of_empty_box_returned = toInt1;
        this.no_of_box_yet_to_return = toInt2;
        this.rate = toDouble2;
    }
}
