public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;
}

public Profile (String fname, String lname, Date dob){
    this.fname=fname;
    this.lname=lname;
    this.dob=dob;
}

public int compareTo(Profile other){
    return 0;
}