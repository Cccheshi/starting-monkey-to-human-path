package PO43.Ivanova.wdad.data.managers;

import PO43.Ivanova.wdad.utils.PreferencesConstantManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

/**
 * Created by Ирина on 09.10.2016.
 */
public class TestPreferencesManager implements PreferencesConstantManager{
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, XPathExpressionException {
        PreferencesManager preferencesManager=PreferencesManager.getInstance();
        /*final String APP_CONFIG = "appconfig";
        final String RMI = "appconfig.rmi";
        final String SERVER = "appconfig.rmi.server";
        final String REGISTRY = "appconfig.rmi.server.registry";
        final String CREATE_REGISTRY = "appconfig.rmi.server.registry.createregistry";
         final String REGISTRY_ADDRESS = "appconfig.rmi.server.registry.registryaddress";
       final String REGISTRY_PORT = "appconfig.rmi.server.registry.registryport";
        final String BINDED_OBJECT = "appconfig.rmi.server.bindedobject";
         final String CLIENT = "appconfig.rmi.client";
        final String POLIVY_PATH = "appconfig.rmi.client.policypath";
        final String USE_CODE_BASE_ONLY = "appconfig.rmi.client.usecodebaseonly";
        final String CLASS_PROVIDER = "appconfig.rmi.classprovider";*/
        System.out.println("ДО ИЗМЕНЕНИЙ");
        System.out.println("get property "+preferencesManager.getProperty(PreferencesConstantManager.CREATE_REGISTRY));
        Properties properties =preferencesManager.getProperties();
            properties.list(System.out);
        preferencesManager.setProperty(PreferencesConstantManager.CREATE_REGISTRY,"newValue");
        Properties properties1=new Properties();
        properties1.put(PreferencesConstantManager.POLICY_PATH, "New");
        preferencesManager.setProperties(properties1);
        /*if (preferencesManager.isCreateRegistry()) {
            System.out.println("Create Registry: yes");
        } else System.out.println("Create Registry: no");
        System.out.println("Registry Address: "+preferencesManager.getRegistryAddress());
        System.out.println("Registry port:"+preferencesManager.getRegistryPort());
        System.out.println("Policy Path:"+preferencesManager.getPolicyPath());
        if (preferencesManager.isUseCodeBaseOnly()){
            System.out.println("Use Code Base Only: yes");
        }else System.out.println("Use Code Base Only: no");
        System.out.println("Class Provider: "+preferencesManager.getClassProvider());*/
        System.out.println("ПОСЛЕ ИЗМЕННЕНИЙ");
        System.out.println("get property "+preferencesManager.getProperty(PreferencesConstantManager.CREATE_REGISTRY));
        System.out.println("get properties+"+preferencesManager.getProperty(PreferencesConstantManager.POLICY_PATH));
        preferencesManager.addBindedObject("newName","NewClass");
        //preferencesManager.addBindedObject("NewName2","NewClass2");
        //preferencesManager.removeBindedObject("newName");
        //preferencesManager.removeBindedObject("NewName2");
        properties =preferencesManager.getProperties();
        properties.list(System.out);
        /*preferencesManager.setCreateRegistry(false);
        if (preferencesManager.isCreateRegistry()) {
            System.out.println("Create Registry: yes");
        } else System.out.println("Create Registry: no");
        preferencesManager.setRegistryAddress("126.128.0.127");
        System.out.println("Registry Address: "+preferencesManager.getRegistryAddress());
        preferencesManager.setRegistryPort("2005");
        System.out.println("Registry port:"+preferencesManager.getRegistryPort());
        preferencesManager.setPolicyPath("policy");
        System.out.println("Policy Path:"+preferencesManager.getPolicyPath());
        preferencesManager.setUseCodeBaseOnly(false);
        if (preferencesManager.isUseCodeBaseOnly()){
            System.out.println("Use Code Base Only: yes");
        }else System.out.println("Use Code Base Only: no");
        preferencesManager.setClassProvider("http://newProvider.com");
        System.out.println("Class Provider: "+preferencesManager.getClassProvider());*/
    }
}
