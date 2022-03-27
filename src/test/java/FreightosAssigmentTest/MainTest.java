package FreightosAssigmentTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import FreightosAssigment.*;

/*
 * Test The Main Class Of The snackMachine
 * */
public class MainTest {

    SnackMachine snackMachine = new SnackMachine();
    /*
    test if the snack is available or soldout
     */
    @Test
    public void soldOutTest() throws FileNotFoundException {
        snackMachine.getUserInput().Scanner(new Scanner(new File("SnackAvailableTest.txt")));
        //Takis count =2 index=4 price=1.60 
        Snack chosendSnack = snackMachine.chosenItem(4);
        assertTrue(chosendSnack.getSnackCount()==1);
        assertTrue(snackMachine.isDone(4, chosendSnack.getSnackPrice()));
      

        chosendSnack = snackMachine.chosenItem(4);
        assertFalse(snackMachine.isDone(4, chosendSnack.getSnackPrice()));
        assertTrue(chosendSnack.getSnackCount() == 1);

        chosendSnack = snackMachine.chosenItem(4);
        assertTrue(snackMachine.isDone(4, chosendSnack.getSnackPrice()));
        assertTrue(chosendSnack.getSnackCount() == 0);
        assertNull(snackMachine.chosenItem(4));
    }
    /**
     * This test will test most payWithCoin/notes scenarios,it will consider coin_payment_test
     * file as an input to scanner,so instead of require console input
     * from user,it will read them form the file.
     *
     * @throws FileNotFoundException
     */
    @Test
    public void payWithCoinTest() throws FileNotFoundException {
        snackMachine.getUserInput().Scanner(new Scanner(new File("CoinTest.txt")));

        //case1 :successful payment with money no change returned,all entered money are accepted.
        //each time the test validate moneyStore,change returned,snack updated quantity.
        //twix 2.20
        Snack selectedSnack = snackMachine.chosenItem(0);
        assertTrue(snackMachine.isDone(0, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{ 0.10=3, 0.20=4,0.50=8,1.0=2,20.0=2, 50.0=3}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 3);


    }
    
    
    
    
    
}
