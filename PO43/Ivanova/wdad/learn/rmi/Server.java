package PO43.Ivanova.wdad.learn.rmi;

import PO43.Ivanova.wdad.data.managers.PreferencesManager;
import PO43.Ivanova.wdad.utils.PreferencesConstantManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();
            String getProperty=preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_PORT);
            int port = Integer.valueOf(getProperty);
            XmlDataManager xmlDataManager = new XmlDataManagerImpl();
            Registry registry;
            if (preferencesManager.getProperty(PreferencesConstantManager.CREATE_REGISTRY).equals("yes")) {
                registry = LocateRegistry.getRegistry(port);
            } else {
                registry = LocateRegistry.createRegistry(port);
            }
            java.rmi.server.UnicastRemoteObject.exportObject(xmlDataManager, 49100);
            registry.bind("xmlDataManager", xmlDataManager);
            preferencesManager.addBindedObject("xmlDataManager", xmlDataManager.getClass().getName());
            System.out.println("Cервер запущен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
