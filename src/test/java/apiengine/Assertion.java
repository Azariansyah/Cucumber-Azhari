package apiengine;

import org.testng.Assert;

import com.apiautomation.model.ResponseItem;

public class Assertion {
    public void assertItem(ResponseItem responseItem, String expectedId, String expectedName) {
        Assert.assertEquals(responseItem.id, expectedId);
        Assert.assertEquals(responseItem.name, expectedName);
    }

    public void assertItemData(ResponseItem responseItem, int expectedYear, double expectedPrice, String expectedCPUModel, String expectedHardDiskSize) {
        Assert.assertEquals(responseItem.data.year, expectedYear);
        Assert.assertEquals(responseItem.data.price, expectedPrice, 0.001); // Delta untuk perbandingan double
        Assert.assertEquals(responseItem.data.CPUModel, expectedCPUModel);
        Assert.assertEquals(responseItem.data.hardDiskSize, expectedHardDiskSize);
    }
}