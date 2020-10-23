public class UserRequirement {
    /*get answer from front-end
    represent answer into laptop configuration*/

    private int price;
    private int processor;
    private int graphics;
    private int hard_drive;
    private int memory;
    private int[] config = {processor,graphics,hard_drive,memory};

    public UserRequirement(){
        this.price = 0;
        this.processor = 0;
        this.graphics = 0;
        this.hard_drive = 0;
        this.memory = 0;
    }

    int[] getConfig(){    //return the user's requirement after survey
        return this.config;
    }
    int getPrice(){
        return this.price;
    }

    void calConfig(String ans){
        int[] newconfig = new int[4];
        int low = 0;
        int mid = 1;
        int high = 2;
        switch (ans) {
            case "moba" -> {
                newconfig[0] = mid;
                newconfig[1] = low;
                newconfig[2] = mid;
                newconfig[3] = mid;
            }
            case "mmorpg", "code" -> {
                newconfig[0] = high;
                newconfig[1] = high;
                newconfig[2] = high;
                newconfig[3] = high;
            }
            case "smallgame" -> {
                newconfig[0] = low;
                newconfig[1] = low;
                newconfig[2] = mid;
                newconfig[3] = low;
            }
            case "design" -> {
                newconfig[0] = mid;
                newconfig[1] = high;
                newconfig[2] = high;
                newconfig[3] = mid;
            }
            case "office" -> {
                newconfig[0] = low;
                newconfig[1] = low;
                newconfig[2] = mid;
                newconfig[3] = mid;
            }
            case "video" -> {
                newconfig[0] = low;
                newconfig[1] = low;
                newconfig[2] = low;
                newconfig[3] = mid;
            }
            default -> {
                newconfig[0] = low;
                newconfig[1] = low;
                newconfig[2] = low;
                newconfig[3] = low;
            }
        }
        setConfig(newconfig);
    }
    void setConfig(int[] newconfig){
        for(int i = 0; i < 4; i++){
            if(newconfig[i]>=this.config[i]  && newconfig[i]>=0){
                if(newconfig[i]<3){
                    this.config[i] = newconfig[i];
                }
                else{
                    this.config[i] = 2;
                }
            }
        }
    }
    void setPrice(int price){
        if(price>0){
            this.price = price;
        }
    }


}
