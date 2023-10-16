package rubank;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDOB() {
        return dob;
    }

    @Override
    public int compareTo(Profile obj) {
        int lastNCompare = this.lname.compareToIgnoreCase(obj.lname);
        if (lastNCompare != 0) {
            return lastNCompare;
        }
        int firstNCompare = this.fname.compareToIgnoreCase(obj.fname);
        if (firstNCompare != 0) {
            return firstNCompare;
        }
        return this.dob.compareTo(obj.dob);
    }
}