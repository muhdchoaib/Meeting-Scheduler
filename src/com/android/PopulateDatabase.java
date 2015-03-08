package com.android;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import android.content.Context;
import android.os.Environment;

public class PopulateDatabase {
	
	private UserDataSource userDC;
	private GroupDataSource groupDC;
	private GroupUserRelationDataSource gurDC;
		
	public PopulateDatabase(Context context)
	{
		
		userDC = new UserDataSource(context);
		groupDC = new GroupDataSource(context);
		gurDC = new GroupUserRelationDataSource(context);
//		gurDC.open();

	}
	
	public void populateBasicData()
	{
		populateUsers();
		populateGroups();
		populateGURs();
	
		
	}
	
	
	public void populateUsers()
	{
		userDC.open();

		ArrayList<User> users = readUsers();
		for(User user : users)
		{
			User tempUser = userDC.createExternalUser(user);
			
		}
		
		userDC.close();

	}
	public void populateGroups()
	{

		groupDC.open();
	
		ArrayList<Group> groups = readGroups();

		for(Group group : groups)
		{
			Group tempGroup = groupDC.createExternalGroup(group);
			
		}
		
		groupDC.close();

	}
	public void populateGURs()
	{
		gurDC.open();
		ArrayList<GroupUserRelation> gurs = readGURs();

		for(GroupUserRelation gur : gurs)
		{
			GroupUserRelation tempGUR = gurDC.createExternalGUR(gur);
			
		}
		
		gurDC.close();

	}
	

	
	public ArrayList<User> readUsers()
	{
		ArrayList<User> userList = new ArrayList<User>();
		
		File sdcard = Environment.getExternalStorageDirectory();

		//Get the text file
		File file = new File(sdcard,"/sdcard/meeting/Users.xml");

	    try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (file);

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + 
                 doc.getDocumentElement().getNodeName());


            NodeList listOfUsers = doc.getElementsByTagName("User");
            int totalUsers = listOfUsers.getLength();
            System.out.println("Total no of users : " + totalUsers);

            for(int s=0; s<listOfUsers.getLength() ; s++)
            {
            	
            	User user = new User();


                Node userNode = listOfUsers.item(s);
                if(userNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)userNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("userID");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    String userID = ((Node)textFNList.item(0)).getNodeValue().trim().toString();

                    user.setUseriD(Long.parseLong(userID));
  //                  System.out.println("User ID : " + userID   );

                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("name");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textNameList = lastNameElement.getChildNodes();
                    
                    String name = ((Node)textNameList.item(0)).getNodeValue().trim().toString();
                    user.setName(name); 
//                    System.out.println("Name : " + 
//                           ((Node)textNameList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ageList = firstPersonElement.getElementsByTagName("address");
                    Element ageElement = (Element)ageList.item(0);

                    NodeList textAddressList = ageElement.getChildNodes();
                    String address = ((Node)textAddressList.item(0)).getNodeValue().trim().toString();
                    
                    user.setAddress(address);

//                    System.out.println("Address : " + 
//                           ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------


                }//end of if clause

                userList.add(user);	
            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
		
		
		return userList;
		
	}
	
	public ArrayList<Group> readGroups()
	{
		ArrayList<Group> groupList = new ArrayList<Group>();
		
		File sdcard = Environment.getExternalStorageDirectory();

		//Get the text file
		File file = new File(sdcard,"/sdcard/meeting/Groups.xml");

	    try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (file);

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + 
                 doc.getDocumentElement().getNodeName());


            NodeList listOfGroups = doc.getElementsByTagName("Group");
            int totalGroups = listOfGroups.getLength();
            System.out.println("Total no of Groups : " + totalGroups);

            for(int s=0; s<listOfGroups.getLength() ; s++)
            {
            	
            	Group group = new Group();


                Node GroupNode = listOfGroups.item(s);
                if(GroupNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)GroupNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("groupID");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    String GroupID = ((Node)textFNList.item(0)).getNodeValue().trim().toString();

                    group.setGroupID(Long.parseLong(GroupID));
  //                  System.out.println("Group ID : " + GroupID   );

                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("name");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textNameList = lastNameElement.getChildNodes();
                    
                    String name = ((Node)textNameList.item(0)).getNodeValue().trim().toString();
                    group.setName(name); 
//                    System.out.println("Name : " + 
//                           ((Node)textNameList.item(0)).getNodeValue().trim());

  

                }//end of if clause

                groupList.add(group);	
            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
		
		
		return groupList;
		
	}
	
	public ArrayList<GroupUserRelation> readGURs()
	{
		ArrayList<GroupUserRelation> gurList = new ArrayList<GroupUserRelation>();
		
		File sdcard = Environment.getExternalStorageDirectory();

		//Get the text file
		File file = new File(sdcard,"/sdcard/meeting/GUR.xml");

	    try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (file);

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + 
                 doc.getDocumentElement().getNodeName());


            NodeList listOfGroups = doc.getElementsByTagName("GUR");
            int totalGroups = listOfGroups.getLength();
            System.out.println("Total no of Groups : " + totalGroups);

            for(int s=0; s<listOfGroups.getLength() ; s++)
            {
            	
            	GroupUserRelation gur = new GroupUserRelation();


                Node GroupNode = listOfGroups.item(s);
                if(GroupNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)GroupNode;
                    
                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("userID");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    String userID = ((Node)textFNList.item(0)).getNodeValue().trim().toString();

                    gur.setUserID(Long.parseLong(userID));
  //                  System.out.println("User ID : " + userID   );

                    //-------
                    NodeList groupIDList = firstPersonElement.getElementsByTagName("groupID");
                    Element groupIDElement = (Element)groupIDList.item(0);

                    NodeList groupID = groupIDElement.getChildNodes();
                    String GroupID = ((Node)groupID.item(0)).getNodeValue().trim().toString();

                    gur.setGroupID(Long.parseLong(GroupID));
  //                  System.out.println("Group ID : " + GroupID   );

    
  

                }//end of if clause

                gurList.add(gur);	
            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
		
		
		return gurList;
		
	}
	
	

}



  
