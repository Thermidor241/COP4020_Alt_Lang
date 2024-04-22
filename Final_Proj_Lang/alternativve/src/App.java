

// This is a simple Java program.
// FileName : "HelloWorld.java".
import java.io.FileReader; 
import com.opencsv.CSVReader;
import java.io.IOException;
import com.opencsv.exceptions.CsvValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
        if (inp_dim.isEmpty() || inp_dim == null || inp_dim == "-"){
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

    public void setdissize(String d_size) {
        Pattern size = Pattern.compile("\\d+(\\.\\d+)?\\s*inches");
        Matcher s = size.matcher(d_size);
    
        if (s.find()) {
            String newsize = s.group(1);
            if (newsize != null) {
                this.dis_size = Float.parseFloat(newsize);
            } else {
                // If decimal part is not present, use the whole matched string as size
                this.dis_size = Float.parseFloat(s.group());
            }
        } else {
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
        String justnumbers = ".*\\d+.*";
        if (inp_sen == null || inp_sen.isEmpty() || inp_sen.matches(justnumbers)){
            this.sensors = null;
        }
        else{
        this.sensors = inp_sen;
    }
    }
 
    public void setos(String inp_os) {
        String justnumbers = ".*\\d+.*";
        if (inp_os == null || inp_os.isEmpty() || inp_os.matches(justnumbers)) {
            this.os = null;
        } else {
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

    // 7 Methods to answer questions

   public static void calculatehighestav(Map<Integer, Cell> map){
    Map <String,Float> totalweightmap = new HashMap<>();
    Map <String,Integer> countmap = new HashMap<>();
        for (Cell row: map.values()){
            if (row.getweight() != null){
                String oem = row.getoem();
                Float weight = row.getweight();

                totalweightmap.put(oem,totalweightmap.getOrDefault(oem, 0.0f) + weight);
                countmap.put(oem,countmap.getOrDefault(oem, 0) + 1);

            }

        }
        String answerto1 = "";
        Float  highest = 0.0f;

        //Find oem with highest average

        for (String oem : totalweightmap.keySet()){
            Float totalweight = totalweightmap.get(oem);
            Integer count = countmap.get(oem);

            Float average = totalweight / count;
            if (average > highest){
                highest = average;
                answerto1 = oem;
            }

        }
        System.out.println("The brand with the highest average weight is " + answerto1);


    }

    public static void anstonumtwo(Map<Integer,Cell> map){

        System.out.println("The brand and models of phones released in different years than announced are : ");
        for (Cell row: map.values()){
            if (row.getl_stat() != null && !row.getl_stat().equals("Discontinued") && !row.getl_stat().equals("Cancelled")){

                 if (row.differentyear()){

                    System.out.println( row.getoem() + row.getmodel());

                }

            }
        }
        
    }
// If its released in a different year than announced
     public Boolean differentyear(){
        Integer yearb = Integer.parseInt(this.getl_stat());

        return !this.getlaunchan().equals(yearb);
    }

    //Answer to third question
    public static void answerto3(Map<Integer,Cell> map){
            Integer count = 0;
        for (Cell row: map.values()){
            if (row.getsensors() != null){
                if (row.hasmultiplesensor(row.getsensors())) {
                    count++;
                }
            }
        }
        System.out.println("The number of phones with one sensor is " + count);
    }
    //If there are multiple sensors
    public Boolean hasmultiplesensor(String sensors) {
        // Check if the sensors string is not null and contains a comma
        if (sensors != null && sensors.contains(",")) {
            // Split the string by comma and check if there are more than one sensor
            String[] sensorArray = sensors.split(",");
            return sensorArray.length > 1;
        } else {
            return false; // Return false if the sensors string is null or doesn't contain a comma
        }
    }

    public static void answerto4(Map<Integer,Cell> map){
        Map <String,Integer> countmil = new HashMap<>();

        String highestyear = "";
        Integer highcount = 0;
        for (Cell row: map.values()){
            if (row.getl_stat() != null && !row.getl_stat().equals("Discontinued") && !row.getl_stat().equals("Cancelled")){
            String l_status = row.getl_stat();
            if (row.launchmill(l_status)){
                countmil.put(l_status,countmil.getOrDefault(l_status, 0) + 1);
            }

        }

        //Find the year with the most phones
        for (String year : countmil.keySet()){
            Integer count = countmil.get(year);

            if (count > highcount){
                highestyear = year;
                highcount = count;
            }
        }





    }
    System.out.println("The year after 1999 with the most phones launched is " + highestyear);
}
//If the phone launched after 1999
    Boolean launchmill(String inp){
    
        return (Integer.parseInt(inp) > 1999);
   
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
                    Row.setdistype(nextline[i]);
                    break;
                    case 8:
                    Row.setdissize(nextline[i]);
                    break;
                    case 9:
                    Row.setres(nextline[i]);
                    break;
                    case 10:
                    Row.setsensors(nextline[i]);
                    case 11:
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
             // Output the HashMap
            /* for (Map.Entry<Integer, Cell> entry : Rows.entrySet()) {
                System.out.println("Row " + entry.getKey() + ":");
                Cell cell = entry.getValue();
                System.out.println("OEM: " + cell.getoem());
                System.out.println("Model: " + cell.getmodel());
                System.out.println("Launch Year: " + cell.getlaunchan());
                System.out.println("Launch Status: " + cell.getl_stat());
                System.out.println("Dimensions: " + cell.getdim());
                System.out.println("Weight: " + cell.getweight());
                System.out.println("Body sim: " + cell.getbodysim());
                System.out.println("Dis type: " + cell.getdistype());
                System.out.println("Dis size: " + cell.getdissize());
                System.out.println("Resolution : " + cell.getres());
                System.out.println("Sensors:" + cell.getsensors());
                System.out.println("Os: " + cell.getos());

                // Add similar lines for other attributes
                System.out.println(); // Add an empty line for separation
            }   */
// Answering the 4 questions
            Cell.calculatehighestav(Rows);
            Cell.anstonumtwo(Rows);
            Cell.answerto3(Rows);
            Cell.answerto4(Rows);
    } catch (IOException e) {
        // Handle the IOException
        e.printStackTrace();
    }
    catch (CsvValidationException e) {
        e.printStackTrace();
    }
    
}


}