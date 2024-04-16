

// This is a simple Java program.
// FileName : "HelloWorld.java".
import java.io.FileReader; 
import java.util.List; 
import com.opencsv.*; 

class Cell{
    //Columns 
    private String oem;
    private String model;
    private int launch_ann;
    private String l_status;
    private String dimensions;
    private float weight;
    private String body_sim;
    private String dis_type;
    private float dis_size;
    private String sensors;
    private String os;


    //Getters and Setters
    public String getoem(){
        return oem;
    }

    public String getmodel(){
        return model;
    }

    public int getlaunchan(){
        return launch_ann;
    }
    public String getl_stat(){
        return l_status;
    }
    public String getdim(){
        return dimensions;
    }
    public float getweight(){
        return weight;
    }
    public String getbodysim(){
        return body_sim;
    }
    public String getdistype(){
        return dis_type;
    }
    public float getdissize(){
        return dis_size;
    }
    public String getsensors(){
        return sensors;
    }
    public String getos(){
        return os;
    }

    // Setters 

/* 
    public Void setoem(){
        return oem;
    }

    public Void setmodel(){
        return model;
    }

    public Void setlaunchan(){
        return launch_ann;
    }
    public Void setl_stat(){
        return l_status;
    }
    public Void setdim(){
        return dimensions;
    }
    public Void setweight(){
        return weight;
    }
    public Void setbodysim(){
        return body_sim;
    }
    public Void setdistype(){
        return dis_type;
    }
    public Void setdissize(){
        return dis_size;
    }
    public Void setsensors(){
        return sensors;
    }
    public Void setos(){
        return os;
    }
    */
}



 class Project {
    // Your program begins with a call to main().
    // Prints "Hello, World" to the terminal window.
    public static void main(String args[])
    {
        System.out.println("Hello, World");
    }
}
