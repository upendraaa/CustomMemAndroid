package interview.upendra.com.custommemory;

public interface LocationTable {


    String TABLE_NAME = "location";
    String COLUMN_ID ="columnId";
    String PLACE ="place";
    String DATE = "date";
    String IS_LIKE = "isLike";
    String TITLE ="title";
    String IMAGE_URL ="url";
    String IMAGE = "image";
    String PRICE = "rate";
    String DESCRIPTION = "description";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME
                             + "("+COLUMN_ID +" INTEGER PRIMARY KEY, "
                             + PLACE + " VARCHAR(200) UNIQUE, "
            + IMAGE_URL + " VARCHAR(400), "
            +   PRICE +" DOUBLE, "
            +   DATE +" VARCHAR(100), "
            + IS_LIKE + " INTEGER, "+
            TITLE + " VARCHAR(100), " +
            "" +IMAGE + " BLOB, "+
            "" + DESCRIPTION + " TEXT"+")";
}
