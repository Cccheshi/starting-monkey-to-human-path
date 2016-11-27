package PO43.Ivanova.wdad.data.managers;

import PO43.Ivanova.wdad.utils.PreferencesConstantManager;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class PreferencesManager  {
    private static PreferencesManager instance;
    private String path = "C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/resources/configuration/appconfig.xml";
    Document document;
    XPath xPath;

    PreferencesManager() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        this.xPath=XPathFactory.newInstance().newXPath();
    }

    public static PreferencesManager getInstance() throws IOException, SAXException, ParserConfigurationException {
        if (instance == null)
            instance = new PreferencesManager();
        return instance;
    }
    private void writeDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }
    //XPATH
    public void setProperty(String key, String value) throws XPathExpressionException, TransformerException {
        String xPathKey = '/' + key.replace('.', '/');
        Node node=(Node)xPath.evaluate(xPathKey,document, XPathConstants.NODE);
        node.setTextContent(value);
       writeDoc();
    }
    public String getProperty(String key) throws XPathExpressionException {
        String xPathKey = '/' + key.replace('.', '/');
        Node node=(Node)xPath.evaluate(xPathKey,document,XPathConstants.NODE);
        return node.getTextContent();
    }
    public void setProperties(Properties prop) throws XPathExpressionException, TransformerException {
        for (String property:prop.stringPropertyNames()) {
            setProperty(property, prop.getProperty(property));
        }
        writeDoc();
    }
    private String findPath(Node child){
        Node parent=child.getParentNode();
            if (parent.getNodeName().equals("#document")){
                return child.getNodeName();
            }
        return findPath(parent)+"."+child.getNodeName();
    }
    public Properties getProperties() throws XPathExpressionException {
        Properties properties = new Properties();
        String expression= "//*[not(*)]";
        String key;
        String value;
        String path;
        NodeList nodeList=(NodeList) xPath.evaluate(expression,document,XPathConstants.NODESET);
       for (int i=0;i<nodeList.getLength();i++){
           path=findPath(nodeList.item(i));
           key=path;
           value=getProperty(key);
           properties.put(key,value);
       }
        return properties;
    }
    public void addBindedObject(String name, String className) throws TransformerException {
        String bindedObject="bindedobject";
        Element bindedObjectE=document.createElement(bindedObject);
        Node registry=document.getElementsByTagName("registry").item(0);
        registry.appendChild(bindedObjectE);
        bindedObjectE.setAttribute("classname", className);
        bindedObjectE.setAttribute("name", name);
        writeDoc();
    }
    public void removeBindedObject(String name) throws TransformerException {
        String TagBindedObject="bindedobject";
        NodeList bindedObjectNL=document.getElementsByTagName(TagBindedObject);
        Node bindedObject;
        NamedNodeMap attributes;
        String nodeValue;
        String tagName="name";
        for (int i=0;i<bindedObjectNL.getLength();i++){
            bindedObject=bindedObjectNL.item(i);
            attributes= bindedObject.getAttributes();
            nodeValue=attributes.getNamedItem(tagName).getNodeValue();
            if (nodeValue.equals(name)){
                bindedObject.getParentNode().removeChild(bindedObject);
            }
        }
        writeDoc();
    }
    @Deprecated
    public boolean isCreateRegistry() {
        String tegCreateRegistry = "createregistry";
        if (document.getElementsByTagName(tegCreateRegistry).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
    @Deprecated
    public void setCreateRegistry(boolean createRegistry) throws TransformerException {
        String textContent;
        String tegCreateRegistry = "createregistry";
        if (createRegistry) {
            textContent = "yes";
        } else textContent = "no";
        document.getElementsByTagName(tegCreateRegistry).item(0).setTextContent(textContent);
        writeDoc();
    }
    @Deprecated
    public String getRegistryAddress() {
        String tegRegistryAddress = "registryaddress";
        return document.getElementsByTagName(tegRegistryAddress).item(0).getTextContent();
    }
    @Deprecated
    public void setRegistryAddress(String newTextContent) throws TransformerException {
        String tegRegistryAddress = "registryaddress";
        document.getElementsByTagName(tegRegistryAddress).item(0).setTextContent(newTextContent);
        writeDoc();
    }
    @Deprecated
    public String getRegistryPort() {
        String tegRegistryPort = "registryport";
        return document.getElementsByTagName(tegRegistryPort).item(0).getTextContent();
    }
    @Deprecated
    public void setRegistryPort(String newRegistryPort) throws TransformerException {
        String tegRegistryPort = "registryport";
        document.getElementsByTagName(tegRegistryPort).item(0).setTextContent(newRegistryPort);
        writeDoc();
    }

    public String getPolicyPath() {
        String tegPolicyPath = "policypath";
        return document.getElementsByTagName(tegPolicyPath).item(0).getTextContent();
    }
    @Deprecated
    public void setPolicyPath(String newPolicyPath) {
        String tegPolicyPath = "policypath";
        document.getElementsByTagName(tegPolicyPath).item(0).setTextContent(newPolicyPath);
    }
    @Deprecated
    public boolean isUseCodeBaseOnly() {
        String tegUseCodebaseOnly = "usecodebaseonly";
        if (document.getElementsByTagName(tegUseCodebaseOnly).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
    @Deprecated
    public void setUseCodeBaseOnly(boolean newUseCodeBaseOnly) throws TransformerException {
        String tegUseCodebaseOnly = "usecodebaseonly";
        String textContent;
        if (newUseCodeBaseOnly){
            textContent="yes";
        } else textContent="no";
        document.getElementsByTagName(tegUseCodebaseOnly).item(0).setTextContent(textContent);
        writeDoc();
    }
    @Deprecated
    public String getClassProvider(){
        String tegClassProvider="classprovider";
        return document.getElementsByTagName(tegClassProvider).item(0).getTextContent();
    }
    @Deprecated
    public void setClassProvider(String newClassProvider) throws TransformerException {
        String tegClassProvider="classprovider";
        document.getElementsByTagName(tegClassProvider).item(0).setTextContent(newClassProvider);
        writeDoc();
    }

}
