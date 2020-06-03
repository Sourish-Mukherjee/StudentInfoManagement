package information;

public class InternalMarks {

    private String name,
            usn;
    private int iat1,
            iat2,
            iat3,
            total;
    private float avg;

    public InternalMarks(String name, String usn, int iat1, int iat2, int iat3, int total, float avg) {
        this.name = name;
        this.usn = usn;
        this.iat1 = iat1;
        this.iat2 = iat2;
        this.iat3 = iat3;
        this.total = total;
        this.avg = avg;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return usn;
    }

    public int getIat1() {
        return iat1;
    }

    public int getIat2() {
        return iat2;
    }

    public int getIat3() {
        return iat3;
    }

    public int getTotal() {
        return total;
    }

    public float getAvg() {
        return avg;
    }

}
