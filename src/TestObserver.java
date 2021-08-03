import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class TestObserver extends TestWatcher {

    private static final ArrayList<String> testResults = new ArrayList<>();

    protected void failed(Throwable e, Description description) {
        String message = e.getMessage()
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
        testResults.add(generateFailedMessage(description.getMethodName(), message));
    }

    protected void succeeded(Description description) {
        testResults.add(generateSucceededMessage(description.getMethodName()));
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String generateSucceededMessage(String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><td><center>");
        sb.append(methodName);
        sb.append("</center></td>");
        sb.append("<td style=\"background-color:#7ffe00\">");
        sb.append("<center>Passed.</center>");
        sb.append("</td>");
        sb.append("<td>");
        sb.append("<center>Great Work!</center>");
        sb.append("</td>");
        return sb.toString();
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String generateFailedMessage(String methodName, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><td><center>");
        sb.append(methodName);
        sb.append("</center></td>");
        sb.append("<td style=\"background-color:#ff0100\">");
        sb.append("<center>Failed.</center>");
        sb.append("</td>");
        sb.append("<td><center>");
        sb.append(message);
        sb.append("</center></td>");
        return sb.toString();
    }

    public static void printResults() {
        try {
            String className = MethodHandles.lookup().lookupClass().toString();
            className = className.substring(className.indexOf(" ") + 1, className.indexOf("Test"));
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("grade.html", true));
            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<style>");
            printWriter.println("table, th, td {");
            printWriter.println("border: 1px solid black;");
            printWriter.println("border-collapse: collapse;");
            printWriter.println("}");
            printWriter.println("</style>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("<h1>" + className + " Tests</h1>");
            printWriter.println("<table style=\"width:100%\">");
            printWriter.println("<tr>");
            printWriter.println("<th>Test Name</th>");
            printWriter.println("<th>Status</th>");
            printWriter.println("<th>Message</th>");
            printWriter.println("</tr>");
            printWriter.println();
            for (String line : testResults) {
                printWriter.println(line);
            }
            printWriter.println("</body>");
            printWriter.println("</html>");
            printWriter.println();
            printWriter.flush();
            printWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
