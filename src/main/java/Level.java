import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics2D;


public class Level {
    protected ArrayList<Platform> platforms;
    protected ArrayList<Fuel> fuels;
    protected ArrayList<Rocket> rocketPieces = new ArrayList<>();
    protected int rocketPiece = 1;
    protected Rocket bottomRocketPiece;

//    protected FileReader f;
    protected int curLevel;
    int scale_x,scale_y;

    public String getLevelFile(int num){
        String fileName = "";
        if (num == 1) {
            fileName = "levels/levelOne.txt";
        }
        else if (num == 2) {
            fileName ="levels/levelTwo.txt";
        }
        else if (num == 3) {
            fileName= "levels/levelThree.txt";
        }
        return fileName;
    }
    private void addFuel(int pos_x, int pos_y){
        Fuel fuel = new Fuel(pos_x - 40, pos_y - 40);
        fuels.add(fuel);
        addPlatform(pos_x,pos_y,40);

    }
    private void addPlatform(int pos_x,int pos_y,int height){
        Platform p = new Platform(pos_x,pos_y, 100, height);
        platforms.add(p);
    }
    private void addBottomRocketPiece(int pos_x,int pos_y){
        Rocket r = new BottomRocketPiece(pos_x,pos_y);
        rocketPieces.add(r);
        rocketPiece += 1;
    }
    private void addMiddleRocketPiece(int pos_x,int pos_y){
        Rocket r = new MiddleRocketPiece(pos_x,pos_y);
        rocketPieces.add(r);
        rocketPiece += 1;
    }
    private void addTopRocketPiece(int pos_x,int pos_y){
        Rocket r = new TopRocketPiece(pos_x,pos_y);
        rocketPieces.add(r);
        this.bottomRocketPiece = r;
        rocketPiece += 1;
    }

    
    private void processLine(String line,int row){
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            int pos_x = i*scale_x, pos_y= row*scale_y;
            if (c == '_')
                addPlatform(pos_x,pos_y,40);
            else if (c == 'f')
                addFuel(pos_x,pos_y);
            else if (c == '-')
                addPlatform(pos_x,pos_y,70);
            else if (c == 'T')
                addTopRocketPiece(pos_x,pos_y);
            else if (c == 'M')
                addMiddleRocketPiece(pos_x,pos_y);
            else if (c == 'B')
                addBottomRocketPiece(pos_x,pos_y);
        }
    }
    ArrayList<String> readFromFile(File levelFile) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        FileReader reader = new FileReader(levelFile);
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine())
            lines.add(scanner.nextLine());
        scanner.close();
        return lines;
    }
    public Level(int num) {
        this.curLevel = num;
        this.platforms = new ArrayList<Platform>();
        this.fuels = new ArrayList<Fuel>();
        try {
            File levelFile = new File(getLevelFile(num));
            ArrayList<String> lines =readFromFile(levelFile);

            //calculate scale based on shape of file and screen
            scale_x= Main.frameWidth/lines.get(0).length();
            scale_y=Main.frameHeight/lines.size();
            for(int i =0; i < lines.size();i ++)
                processLine(lines.get(i),i);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void drawLevel(Graphics2D g) {
        for (Platform p: platforms) {
            p.drawOn(g);
        }
        for (Fuel f: fuels) {
            f.drawOn(g);
        }
        for (Rocket r : rocketPieces) {
            r.drawOn(g);
        }


    }

    public Rocket getBottomRocketPiece(){
        return this.bottomRocketPiece;
    }


}
