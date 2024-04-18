

// This is a simple Java program.
// FileName : "HelloWorld.java".
import java.io.FileReader; 
import com.opencsv.CSVReader;
import java.io.IOException;
import com.opencsv.exceptions.CsvValidationException;
import java.util.List; 
import com.opencsv.*; 
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ObjectUtils.Null;

class Cell{
    //Columns 
    private String oem;
    private String model;
    private Integer launch_ann;
    private String l_status;
    private String dimensions;
    private Float weight;
    private String body_sim;
    private String dis_type;
    private Float dis_size;
    private String res;
    private String sensors;
    private String os;


    //Getters and Setters
    public String getoem(){
        return oem;
    }

    public String getmodel(){
        return model;
    }

    public Integer getlaunchan(){
        return launch_ann;
    }
    public String getl_stat(){
        return l_status;
    }
    public String getdim(){
        return dimensions;
    }
    public Float getweight(){
        return weight;
    }
    public String getbodysim(){
        return body_sim;
    }
    public String getdistype(){
        return dis_type;
    }
    public Float getdissize(){
        return dis_size;
    }
    public String getres(){
        return res;
    }
    public String getsensors(){
        return sensors;
    }
    public String getos(){
        return os;
    }

    // Constructor 
   /* public Cell(String oem, String model, int launch_ann, String l_status, String dimensions, float weight, String body_sim, String dis_type, float dis_size,String sensors, String platform, String os){

    }*/
    // Setters 


    public void setoem(String inp_oem){
        if (inp_oem.isEmpty()|| inp_oem == null){
            this.oem = null;
        }
        else{
        this.oem = inp_oem;}
    }

    public void setmodel(String inp_model){
        if (inp_model.isEmpty()|| inp_model == null){
            this.model= null;
        }
        else{
        this.model = inp_model;}  
    }

    public void setlaunchan(String inp_ann) {
        Integer year;
        if (inp_ann == null || inp_ann.isEmpty()) {
            year = null;
        } else if (hasYear(inp_ann)) {
            // Extract the numeric part from the input string
            String numericPart = extractNumericPart(inp_ann);
            year = Integer.parseInt(numericPart);
        } else {
            year = null;
        }
        this.launch_ann = year;
    }
 
    public void setl_stat(String inp_st) {
        if (inp_st == null || inp_st.isEmpty()) {
            this.l_status = null;
        } else if (inp_st.equals("Discontinued") || inp_st.equals("Cancelled")) {
            this.l_status = inp_st;
        } else {
            Pattern yearPattern = Pattern.compile("\\d{4}");
            Matcher matcher = yearPattern.matcher(inp_st);
            
            if (matcher.find()) {
                String y_launch = matcher.group();
                this.l_status = y_launch;
            } else {
                this.l_status = null; // Handle the case where no year is found
            }
        }
    }
    
    public void setdim(String inp_dim){
        if (inp_dim.isEmpty() || inp_dim == null){
            this.dimensions = null;
        }
        else{
        this.dimensions = inp_dim;
    }

    }


    public void setweight(String inp_w){
        Pattern mass = Pattern.compile("(\\d+(\\.\\d+)?)\\s*g");
        Matcher ma = mass.matcher(inp_w);

        if (ma.find()){
            String newmass = ma.group(1);
            this.weight = Float.parseFloat(newmass);
        }
        else{
            this.weight = null;
        }

    }
  
    public void setbodysim(String inp_sim){
        if (inp_sim.isEmpty() || inp_sim == null || inp_sim == "No" || inp_sim == "Yes"){
            this.body_sim = null;
        }
        else{
        this.body_sim = inp_sim;
    }
}

    public void setdistype( String d_type){
        if (d_type.isEmpty() || d_type == null ){
            this.dis_type = null;
        }
        else{
        this.dis_type = d_type;
    }
    }

    public void setdissize(String d_size){
        Pattern size = Pattern.compile("\\d+(\\.\\d+)?\\s*inches");
        Matcher s = size.matcher(d_size);

        if (s.find()){
            String newsize = s.group(1);
            this.dis_size = Float.parseFloat(newsize);
        }
        else{
            this.dis_size = null;
        }

    }

    public void setres(String inp_res){
        if (inp_res.isEmpty() || inp_res == null ){
            this.res = null;
        }
        else{
        this.res = inp_res;
    }
    }
 
    public void setsensors(String inp_sen){
       String hasletters = "[^a-zA-Z]*";

        if (inp_sen.isEmpty() || inp_sen == null || Pattern.matches(hasletters, inp_sen) == false){
            this.sensors = null;
        }
        else{
        this.sensors = inp_sen;
    }
    }
 
    public void setos(String inp_os){
       String hasletters = "[^a-zA-Z]*";

        if (inp_os.isEmpty() || inp_os == null || Pattern.matches(hasletters, inp_os) == false){
            this.os = null;
        }
        else{
        this.os = inp_os;
    }
    }
    

    //Transform functions
    //checks the string if the year is there
    boolean hasYear(String i){
        Pattern year = Pattern.compile("\\d{4}");
        Matcher m = year.matcher(i);

        return m.find();
    }
    public String extractNumericPart(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        StringBuilder numericPart = new StringBuilder();
        boolean foundDigit = false;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                numericPart.append(c);
                foundDigit = true;
            } else if (foundDigit && c == ' ') {
                // Stop extracting if a space is encountered after finding a digit
                break;
            }
        }
        return numericPart.toString();
    }
}



 class Project {
    // Your program begins with a call to main().
    // Prints "Hello, World" to the terminal window.
    public static void main(String args[])
    {
     
        //Hashmap start
        Map<Integer, Cell> Rows = new HashMap<>();
       try (CSVReader reader = new CSVReader(new FileReader("cells.csv"))) {
        //Counter for rows
        int rowNum = 0;
        //Isolate the headers
        String[] headers = reader.readNext();
        //Lines afterward
        String[] nextline;
        //Continue reading
        while ((nextline= reader.readNext()) != null) { 
            //Make Row object for each row in the csv
            Cell Row = new Cell();
            //Add each column to the respective attributes
            for(int i = 0; i < headers.length; i++){
                switch(i){
                    case 0:
                    Row.setoem(nextline[i]);
                    break;
                    case 1:
                    Row.setmodel(nextline[i]);
                    break;
                    case 2:
                    Row.setlaunchan(nextline[i]);
                    break;
                    case 3:
                    Row.setl_stat(nextline[i]);
                    break;
                    case 4:
                    Row.setdim(nextline[i]);
                    break;
                    case 5:
                    Row.setweight(nextline[i]);
                    break;
                    case 6:
                    Row.setbodysim(nextline[i]);
                    case 7:
                    break;
                    case 8:
                    Row.setdistype(nextline[i]);
                    break;
                    case 9:
                    Row.setdissize(nextline[i]);
                    break;
                    case 10:
                    Row.setres(nextline[i]);
                    case 11:
                    Row.setsensors(nextline[i]);
                    break;
                    case 12:
                    Row.setos(nextline[i]);
                    break;
                }
            }
            Rows.put(rowNum, Row);
            rowNum++;
            
         /*   for (String cell : nextline) { 
                System.out.print(cell + " "); 
            } 
            System.out.println(); */
        }
    } catch (IOException e) {
        // Handle the IOException
        e.printStackTrace();
    }
    catch (CsvValidationException e) {
        e.printStackTrace();
    }
    
}


}