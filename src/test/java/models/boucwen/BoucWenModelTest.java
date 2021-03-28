package models.boucwen;

import data.Data;
import data.InOut;
import exceptions.WrongParametersSetsException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BoucWenModelTest {

    static Data data;

    //"/src/test/resources/dane.txt"
    @BeforeClass
    public static void load() {
        data = new Data(InOut.load(new File(BoucWenModel.class.getClassLoader().getResource("dane.txt").getPath())));
    }

    @Test
    public void testCalculate() {
        try {
            Assert.assertNotNull(new BoucWenModel().calculate(new BoucWenParameterSet()
                            .setAc(1.0)
                            .setAt(1.0)
                            .setAlfac(1.0)
                            .setAlfat(1.0)
                            .setBetac(1.0)
                            .setBetat(1.0)
                            .setGammac(1.0)
                            .setGammat(1.0)
                            .setKsic(1.0)
                            .setKsit(1.0)
                            .setNc(1.0)
                            .setNt(1.0)
                            .setWc(1.0)
                            .setWt(1.0)
                    , data));
        } catch (WrongParametersSetsException e) {
            e.printStackTrace();
        }
    }
}