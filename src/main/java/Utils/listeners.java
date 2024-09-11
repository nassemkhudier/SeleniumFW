package Utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class listeners implements ITestListener {

    @AfterClass
    public void onTestSuccess(ITestResult result) {
        System.out.println("===============================================");
        System.out.println("Test Case Passed: " + result.getMethod().getMethodName());
        System.out.println("Test Case Description: " + result.getMethod().getDescription());
        if (result.getStatus() == 1)
            System.out.println("Test Case Status: Success");
        else
            System.out.println("Test Case Status: Failed");
        System.out.println("===============================================");
    }

    @AfterClass
    public void onTestFailure(ITestResult result) {
        System.out.println("===============================================");
        System.out.println("Test Case Failed: " + result.getMethod().getMethodName());
        System.out.println("Test Case Description: " + result.getMethod().getDescription());
        if (result.getStatus() == 1)
            System.out.println("Test Case Status: Success");
        else
            System.out.println("Test Case Status: Failed");
        System.out.println("===============================================");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("===============================================");
        System.out.println("Test Case Failed: " + result.getMethod().getMethodName());
        System.out.println("Test Case Description: " + result.getMethod().getDescription());
        if (result.getStatus() == 1)
            System.out.println("sucsess");
        else
            System.out.println("failed");
        System.out.println("===============================================");
    }


}
