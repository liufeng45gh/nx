//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import jodd.io.FileUtil;

public class LdapUtil {
    private static File errorFile = new File("d:\\error.txt");
    private static final String DOMAIN_NAME = "EXAMPLE";
    private static final String DOMAIN_SUFFIX = "COM";
    private static final String LDAP_SERVER_IP = "10.10.0.5";
    private static final String LDAP_PORTAL = "389";
    private static final String ROOT = "DC=EXAMPLE,DC=COM";
    private static final String LDAP_URL = "ldap://10.10.0.5:389/";

    public LdapUtil() {
    }

    public static boolean ldapAuth(String userName, String password) throws Exception {
        boolean isPass = false;
        String ldapUserName = "EXAMPLE\\" + userName;
        Hashtable<String, String> environment = new Hashtable();
        environment.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put("java.naming.provider.url", "ldap://10.10.0.5:389/");
        environment.put("java.naming.referral", "follow");
        environment.put("java.naming.security.principal", ldapUserName);
        environment.put("java.naming.security.credentials", password);
        InitialDirContext dirContext = null;

        try {
            dirContext = new InitialDirContext(environment);
            isPass = true;
        } catch (NamingException var10) {
            var10.printStackTrace();
        } finally {
            if(dirContext != null) {
                dirContext.close();
            }

        }

        return isPass;
    }

    public static DirContext getDirContext(String userName, String password) throws Exception {
        DirContext dc = null;
        String ldapUserName = "EXAMPLE\\" + userName;
        Hashtable<String, String> environment = new Hashtable();
        environment.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put("java.naming.provider.url", "ldap://10.10.0.5:389/");
        environment.put("java.naming.referral", "follow");
        environment.put("java.naming.security.principal", ldapUserName);
        environment.put("java.naming.security.credentials", password);

        try {
            dc = new InitialDirContext(environment);
        } catch (NamingException var6) {
            var6.printStackTrace();
        }

        return dc;
    }

    public static String getDN(String base, String scope, String filter, DirContext dc) {
        String dn = null;
        SearchControls sc = new SearchControls();
        if(scope.equals("base")) {
            sc.setSearchScope(0);
        } else if(scope.equals("one")) {
            sc.setSearchScope(1);
        } else {
            sc.setSearchScope(2);
        }

        NamingEnumeration ne = null;

        try {
            ne = dc.search(base, filter, sc);
            if(ne.hasMore()) {
                SearchResult sr = (SearchResult)ne.next();
                String name = sr.getName();
                if(base != null && !base.equals("")) {
                    LogUtil.info("entry: " + name + "," + base);
                } else {
                    LogUtil.info("entry: " + name);
                }

                dn = name + "," + base;
            }
        } catch (Exception var9) {
            System.err.println("Error: " + var9.getMessage());
            var9.printStackTrace();
        }

        return dn;
    }

    public static void add(String newUserName, DirContext dc) {
        try {
            BasicAttributes attrs = new BasicAttributes();
            BasicAttribute objclassSet = new BasicAttribute("objectClass");
            objclassSet.add("sAMAccountName");
            objclassSet.add("employeeID");
            attrs.put(objclassSet);
            attrs.put("ou", newUserName);
            dc.createSubcontext("ou=" + newUserName + "," + "DC=EXAMPLE,DC=COM", attrs);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void delete(String dn, DirContext dc) {
        try {
            dc.destroySubcontext(dn);
        } catch (Exception var3) {
            LogUtil.error("Exception in delete():" + var3);
        }

    }

    public static boolean renameEntry(String oldDN, String newDN, DirContext dc) {
        try {
            dc.rename(oldDN, newDN);
            return true;
        } catch (NamingException var4) {
            System.err.println("Error: " + var4.getMessage());
            return false;
        }
    }

    public static boolean modifyInformation(String dn, String employeeID, DirContext dc, String[] employeeArray) throws IOException {
        try {
            String[] modifyAttr = new String[]{"telephoneNumber"};
            ModificationItem[] modifyItems = new ModificationItem[employeeArray.length - 1];

            for(int i = 0; i < modifyAttr.length; ++i) {
                String attrName = modifyAttr[i];
                Attribute attr = new BasicAttribute(attrName, employeeArray[i + 1]);
                modifyItems[i] = new ModificationItem(2, attr);
            }

            dc.modifyAttributes(dn, modifyItems);
            return true;
        } catch (NamingException var9) {
            var9.printStackTrace();
            System.err.println("Error: " + var9.getMessage());
            FileUtil.appendString(errorFile, "Error:" + var9.getMessage() + "\n");
            return false;
        }
    }

    public void searchInformation(String base, String scope, String filter, DirContext dc) {
        SearchControls sc = new SearchControls();
        if(scope.equals("base")) {
            sc.setSearchScope(0);
        } else if(scope.equals("one")) {
            sc.setSearchScope(1);
        } else {
            sc.setSearchScope(2);
        }

        NamingEnumeration ne = null;

        try {
            ne = dc.search(base, filter, sc);

            while(ne.hasMore()) {
                SearchResult sr = (SearchResult)ne.next();
                String name = sr.getName();
                if(base != null && !base.equals("")) {
                    LogUtil.info("entry: " + name + "," + base);
                } else {
                    LogUtil.info("entry: " + name);
                }

                Attributes at = sr.getAttributes();
                NamingEnumeration ane = at.getAll();

                while(ane.hasMore()) {
                    Attribute attr = (Attribute)ane.next();
                    String attrType = attr.getID();
                    NamingEnumeration values = attr.getAll();

                    while(values.hasMore()) {
                        Object oneVal = values.nextElement();
                        if(oneVal instanceof String) {
                            LogUtil.info(attrType + ": " + (String)oneVal);
                        } else {
                            LogUtil.info(attrType + ": " + new String((byte[])oneVal));
                        }
                    }
                }
            }
        } catch (Exception var15) {
            System.err.println("Error: " + var15.getMessage());
            var15.printStackTrace();
        }

    }

    public void Ldapbyuserinfo(String userName, DirContext dc) {
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(2);
        String searchFilter = "sAMAccountName=" + userName;
        String searchBase = "DC=EXAMPLE,DC=COM";
        int totalResults = 0;
        String[] returnedAtts = new String[]{"url", "whenChanged", "employeeID", "name", "userPrincipalName", "physicalDeliveryOfficeName", "departmentNumber", "telephoneNumber", "homePhone", "mobile", "department", "sAMAccountName", "whenChanged", "mail"};
        searchCtls.setReturningAttributes(returnedAtts);

        try {
            NamingEnumeration<?> answer = dc.search(searchBase, searchFilter, searchCtls);
            if(answer != null && !answer.equals((Object)null)) {
                LogUtil.info("answer not null");
            } else {
                LogUtil.info("answer is null");
            }

            while(true) {
                Attributes Attrs;
                do {
                    if(!answer.hasMoreElements()) {
                        return;
                    }

                    SearchResult sr = (SearchResult)answer.next();
                    Attrs = sr.getAttributes();
                } while(Attrs == null);

                try {
                    NamingEnumeration ne = Attrs.getAll();

                    while(ne.hasMore()) {
                        Attribute Attr = (Attribute)ne.next();

                        for(NamingEnumeration e = Attr.getAll(); e.hasMore(); ++totalResults) {
                            String var14 = e.next().toString();
                        }
                    }
                } catch (NamingException var15) {
                    System.err.println("Throw Exception : " + var15);
                }
            }
        } catch (Exception var16) {
            var16.printStackTrace();
            System.err.println("Throw Exception : " + var16);
        }
    }

    public static void close(DirContext dc) {
        if(dc != null) {
            try {
                dc.close();
            } catch (NamingException var2) {
                LogUtil.error("NamingException in close():" + var2);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        String adAdmin = "OP031793";
        String adAdminPassword = "xxxxx";
        DirContext dc = getDirContext(adAdmin, adAdminPassword);
        if(dc != null) {
            FileInputStream fileInputStream = StreamUtils.getFileInputStream("d:\\address.txt");
            String strFile = StreamUtils.InputStreamTOString(fileInputStream);
            String[] lineContextArray = strFile.split("\r\n");

            for(int i = 0; i < lineContextArray.length; ++i) {
                if(lineContextArray[i] != null) {
                    String lineContext = lineContextArray[i];
                    String[] employeeArray = lineContext.split(",");
                    String employeeID = employeeArray[0];
                    String dn = getDN("DC=EXAMPLE,DC=COM", "", "sAMAccountName=" + employeeID, dc);
                    if(dn == null) {
                        FileUtil.appendString(errorFile, "Not find user:" + employeeID + "\n");
                    } else {
                        modifyInformation(dn, employeeID, dc, employeeArray);
                    }
                }
            }

            close(dc);
        }
    }
}
