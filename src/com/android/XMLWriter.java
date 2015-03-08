package com.android;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLWriter {

	
	public void createXmlTree(Document doc) throws Exception {
		//This method creates an element node
/*		Element root = doc.createElement("Company");
		//adding a node after the last child node of the specified node.
		doc.appendChild(root);

		Element child = doc.createElement("Location");
		root.appendChild(child);

		Element child1 = doc.createElement("Companyname");
		child.appendChild(child1);

		Text text = doc.createTextNode("Roseindia .Net");
		child1.appendChild(text);

		Comment comment = doc.createComment("Employee in roseindia");
		child.appendChild(comment);

		Element element = doc.createElement("Employee");
		child.appendChild(element);

		Text text1 = doc.createTextNode("Vineet Bansal");
		element.appendChild(text1);

		Element chilE = doc.createElement("Id");
		chilE.setAttribute("name", "Vineet");
		root.appendChild(chilE);


		Text text12 = doc.createTextNode("status");
		chilE.appendChild(text12);

		//TransformerFactory instance is used to create Transformer objects.
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = sw.toString();

		//asd
		File file = new File("c:/newxml.xml");
		BufferedWriter bw = new BufferedWriter
		(new OutputStreamWriter(new FileOutputStream(file)));
		bw.write(xmlString);
		bw.flush();
		bw.close();
*/
		}

}


/*public static void main(String args[]) throws IOException {

try
{
DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
//creating a new instance of a DOM to build a DOM tree.
Document doc = docBuilder.newDocument();
new XmlServlet().createXmlTree(doc);

System.out.println("<b>Xml File Created Successfully</b>");
}
catch(Exception e)
{
System.out.println(e);
}


}
*/
