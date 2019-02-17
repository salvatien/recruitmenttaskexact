public class ExactCalculation {

    public static void main(String[] args){
        Configuration config = new Configuration();
        Game game = new Game(config);
        double result = game.calculateEstimatedWin(false);
        System.out.println("estimated win: " + result);
    }
}
