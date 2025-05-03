import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics2D;


public class Level {

    protected List<Platform> platforms;
    protected List<Fuel> fuels;
    protected List<Rocket> rocketPieces = new ArrayList<>();
    protected int rocketPiece = 1;
    protected Rocket bottomRocketPiece;


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
        this.bottomRocketPiece = r;
    }

    private void addMiddleRocketPiece(int pos_x,int pos_y){
        Rocket r = new MiddleRocketPiece(pos_x,pos_y);

        rocketPieces.add(r);
    }

    private void addTopRocketPiece(int pos_x,int pos_y){
        Rocket r = new TopRocketPiece(pos_x,pos_y);
        rocketPieces.add(r);
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

    ArrayList<String> readFromFile(File levelFile) {
        ArrayList<String> lines = new ArrayList<>();

        FileReader reader;
        try {
            reader = new FileReader(levelFile, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading level file", e);
        }

        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine())
            lines.add(scanner.nextLine());
        scanner.close();
        return lines;
    }

    public void convertLevelToText(ArrayList<String> levelLines){
        scale_x= Main.frameWidth/levelLines.get(0).length();
        scale_y=Main.frameHeight/levelLines.size();
        for(int i =0; i < levelLines.size();i ++)
            processLine(levelLines.get(i),i);
    }

    public Level(){
        this.platforms = new ArrayList<>();
        this.fuels = new ArrayList<>();
    }

    public Level (int num) {
        this.curLevel = num;
        this.platforms = new ArrayList<>();
        this.fuels = new ArrayList<>();

        File levelFile = new File(getLevelFile(num));
        ArrayList<String> lines =readFromFile(levelFile);
        convertLevelToText(lines);
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

    public List<Platform> getPlatforms(){
        return this.platforms;
    }

    public List<Rocket> getRocketPieces(){
        return this.rocketPieces;
    }


}
