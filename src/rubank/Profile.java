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
@Override
public int compareTo(Profile obj){
    int lastNCompare = this.lname.compareTo(obj.lname)
    if(lastNCompare!=0){
        return lastNCompare;
    }
    int firstNCompare = this.fname.compareTo(obj.fname);
    if(firstNCompare!=0){
        return firstNCompare;
    }
    return this.dob.compareTo(other.dob);
}
