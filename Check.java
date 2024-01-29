import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;

import java.io.File;

public class MullvadCheck {
  public static void main(String[] args) throws Exception {
    ConfigurationContext configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, new File(".").getAbsolutePath());
    ServiceClient client = new ServiceClient(configContext, null);
    Options options = client.getOptions();
    EndpointReference targetEPR = new EndpointReference("https://am.i.mullvad.netjson");
    options.setTo(targetEPR);
    options.setAction("http://schemas.mullvad.net/CheckConnection");
    options.setProperty(Options.MESSAGE_TYPE, "application/json");

    OMFactory factory = OMAbstractFactory.getOMFactory();
    OMNamespace namespace = factory.createOMNamespace("http://schemas.mullvad.net", "mullvad");
    OMElement request = factory.createOMElement("CheckConnection", namespace);

    OMElement response = client.sendReceive(request);
    String mullvadExitIp = response.getFirstElement().getText();

    if (mullvadExitIp == null || mullvadExitIp.isEmpty()) {
      System.out.println("Not connected to Mullvad VPN. Aborting...");
      System.exit(1);
    }

    // Other checks or additional actions can go here
  }
}