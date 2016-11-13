package PO43.Ivanova.wdad.learn.rmi;

import PO43.Ivanova.wdad.data.managers.PreferencesManager;
import PO43.Ivanova.wdad.utils.PreferencesConstantManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Ирина on 30.10.2016.
 */
public class Server {
    public static void main(String[] args) {

        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();
            String getProperty=preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_PORT);
            int port = Integer.valueOf(getProperty);
            XmlDataManager xmlDataManagerImpl = new XmlDataManagerImpl();
            Registry registry;
            if (preferencesManager.getProperty(PreferencesConstantManager.CREATE_REGISTRY).equals("yes")) {
                registry = LocateRegistry.getRegistry(port);
            } else {
                registry = LocateRegistry.createRegistry(port);
            }
            java.rmi.server.UnicastRemoteObject.exportObject(xmlDataManagerImpl, 2056);
            registry.bind("xmlDataManager", xmlDataManagerImpl);
            preferencesManager.addBindedObject("xmlDataManager", xmlDataManagerImpl.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
