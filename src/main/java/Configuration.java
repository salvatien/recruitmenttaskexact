public class Configuration {
    int totalAmountGameOverBoxes = 3;
    int totalAmountExtraLifeBoxes = 1;
    int totalAmountCashBoxes = 8;
    int totalAmountBoxes = 12;
    double probabilityOfGettingExtraLifeBeforeGameOver
            = totalAmountExtraLifeBoxes / (double)(totalAmountExtraLifeBoxes + totalAmountGameOverBoxes);
    double averageWinOnCashBox = (1*100 + 2*20 + 5*5)/(double)totalAmountCashBoxes;
    double averageWinOnAdditionalReward = (20+10+5)/3.0;
    double probabilityOfGettingSecondChance = 0.25;
    boolean multipleSecondChances = false;
}
