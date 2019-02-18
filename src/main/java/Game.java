
public class Game {

    private Configuration config;

    Game(Configuration config) {
        this.config = config;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration value) {
        config = value;
    }

    double calculateEstimatedWin(boolean isSecondChanceUsed) {
        double res1 = config.probabilityOfGettingExtraLifeBeforeGameOver * this.estimatedWinWithExtraLife();

        double res2 = (1-config.probabilityOfGettingExtraLifeBeforeGameOver) * this.estimatedWinWithoutExtraLife();

        double resultBeforeAdditionalReward = config.probabilityOfGettingExtraLifeBeforeGameOver * this.estimatedWinWithExtraLife()
                + (1-config.probabilityOfGettingExtraLifeBeforeGameOver) * this.estimatedWinWithoutExtraLife();
        double resultOfAdditionalReward = this.estimatedWinFromAdditionalReward(isSecondChanceUsed, resultBeforeAdditionalReward);
        return resultBeforeAdditionalReward + resultOfAdditionalReward;
    }

    private double estimatedWinFromAdditionalReward(boolean isSecondChanceUsed, double resultBeforeAdditionalReward) {
        if(isSecondChanceUsed || config.multipleSecondChances)
            return config.averageWinOnAdditionalReward;
        else
            return config.averageWinOnAdditionalReward
                    + config.probabilityOfGettingSecondChance * resultBeforeAdditionalReward;
    }

    private double estimatedWinWithExtraLife() {
        double estimatedWin = 0;
        int n = config.totalAmountBoxes - config.totalAmountExtraLifeBoxes;
        //chance of not losing after opening i-th box
        for(int i=1; i<n+1-config.totalAmountGameOverBoxes; i++) {
            estimatedWin+=config.averageWinOnCashBox
                    * this.probabilityOfNotLosing(i, n-1, config.totalAmountGameOverBoxes-1);
        }
        return estimatedWin;
    }

    private double estimatedWinWithoutExtraLife() {
        double estimatedWin = 0;
        int n = config.totalAmountBoxes - config.totalAmountExtraLifeBoxes;
        //chance of not losing after opening i-th box
        for(int i=1; i<n+1-config.totalAmountGameOverBoxes; i++) {
            estimatedWin+=config.averageWinOnCashBox
                    * this.probabilityOfNotLosing(i, n, config.totalAmountGameOverBoxes);
        }
        return estimatedWin;
    }

    // based on Hypergeometric distribution - checks the probability of not losing in number of consecutive dependant trials
    private double probabilityOfNotLosing(int numberOfTrials, int numberOfBoxes, int numberOfGameOverBoxes) {
        return binomialCoefficient(numberOfGameOverBoxes, 0)
                * binomialCoefficient(numberOfBoxes - numberOfGameOverBoxes, numberOfTrials)
                / (double)binomialCoefficient(numberOfBoxes, numberOfTrials);
    }

    private static long binomialCoefficient(int n, int k)
    {
        if(k>n)
            return 0;
        if(k==0 || k == n)
            return 1;
        long  result = 1;
        for(int i = 1; i < k+1; i++)
        {
            result = result * ( n - i + 1 ) / i;
        }
        return result;
    }
}
